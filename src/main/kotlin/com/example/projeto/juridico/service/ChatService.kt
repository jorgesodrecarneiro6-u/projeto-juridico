package com.example.projeto.juridico.service

import ai.koog.prompt.dsl.prompt
import com.example.projeto.juridico.model.ChatMensagem
import com.example.projeto.juridico.model.Role
import com.example.projeto.juridico.model.Usuario
import com.example.projeto.juridico.repository.ChatMensagemRepository
import com.example.projeto.juridico.repository.UsuarioRepository
import com.example.projeto.juridico.service.ia.AssistenteIA
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatService(
    private val chatMensagemRepository: ChatMensagemRepository, private val usuarioRepository: UsuarioRepository,
    //injeção do agenteIA
    private val assistenteIa: AssistenteIA
) {
    fun processarChat(
        usuarioId: UUID,
        mensagem: String,
        sessaoId: UUID?): String {

        //BUSCA O NOME DO USURÁRIO
        val usuario = usuarioRepository.findById(usuarioId).get()
        val nome = usuario.nome

        //Se não for nula, continua na mesma conversa do banco.
        //Se for nula, cria uma nova conversa.
        val sessaoFinal = sessaoId ?: UUID.randomUUID()

        //SALVA A MENSAGEM DO USUÁRIO AO ASSISTENDE NO DB.
        val novaMensagem = ChatMensagem(
            usuario = usuarioRepository.findById(usuarioId).get(),
            mensagem = mensagem,
            sessaoId = sessaoFinal,
            remetente = Role.USER
        )
        chatMensagemRepository.save(novaMensagem)


        //BUACA AS 10 ÚTIMAS SESSÕES DO HISTÓRICO NO DB
        // PARA EVITAR QUE O ASSISTENTE SE REPITA OU SE CONFUNDA E ACELERE A RESPOSTA.
        val historico = chatMensagemRepository.findBySessaoId(
            sessaoId = sessaoFinal, PageRequest.of(
                0, 10,
                Sort.by("enviadoEm").descending()
            )
        ).reversed()

        //RESPOSTA DO ASSISTENTE.
        val resposta = try {
            assistenteIa.assistenteJuridicoIA(nome, mensagem, sessaoFinal)
        } catch (e: Exception) {
            "Desculpe ${usuario.nome}, estou com instabilidade técnica agora. Pode tentar em instantes?"
        }

        //SALVANDO A RESPOSTA DO ASSISTENTE.
        val mensagemIA = ChatMensagem(
            usuario = usuarioRepository.findById(usuarioId).get(), mensagem = resposta,
            sessaoId = sessaoFinal, remetente = Role.ASSISTANT
        )
        chatMensagemRepository.save(mensagemIA)

        return resposta


    }

}