package com.example.projeto.juridico.service

import com.example.projeto.juridico.model.ChatMensagem
import com.example.projeto.juridico.model.Role
import com.example.projeto.juridico.repository.ChatMensagemRepository
import com.example.projeto.juridico.repository.UsuarioRepository
import com.example.projeto.juridico.service.ia.AssistenteIA
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatService(
    private val chatMensagemRepository: ChatMensagemRepository,
    private val usuarioRepository: UsuarioRepository,
    //koog
    private val assistenteIa: AssistenteIA
) {
    fun processarChat(usuarioId: UUID, mensagem: String, sessaoId: UUID): String {

        //Objeto mensagem enviada
        val novaMensaagem = ChatMensagem(
            usuario = usuarioRepository.findById(usuarioId).get(),
            mensagem = mensagem,
            sessaoId = UUID.randomUUID(),
            remetente = Role.USER
        )
        chatMensagemRepository.save(novaMensaagem)

        // 2. Busca histórico da sessão para dar contexto ao Koog
        val historico = chatMensagemRepository.findBySessaoIdOrderByEnviadoEmAsc(sessaoId = novaMensaagem.sessaoId)

        //
        val resposta = assistenteIa.assistenteJuridicoIA(mensagem, novaMensaagem.sessaoId)

        val mensagemIA = ChatMensagem(
            usuario = usuarioRepository.findById(usuarioId).get(),
            mensagem = resposta,
            sessaoId = novaMensaagem.sessaoId,
            remetente = Role.ASSISTANT
        )
        chatMensagemRepository.save(mensagemIA
        )

        return resposta
    }

}