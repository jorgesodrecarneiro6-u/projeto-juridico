package com.example.projeto.juridico.service.ia

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor
import com.example.projeto.juridico.model.ChatMensagem
import com.example.projeto.juridico.model.Usuario
import com.example.projeto.juridico.repository.UsuarioRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AssistenteIA(
    private val usuarioRepository: UsuarioRepository
) {
    /*Lógica do assistente Jurídico IA: Responder a mensagem do usuário utilizando a class ChatMensagem
    nos campos mensagem e sessaoId(verificar o histórico da conversa).
    UTILIZANDO O FRAMEWORK KOOG
    */

    fun assistenteJuridicoIA(
        nomeUsuario: String, mensagem: String, sessaoId: UUID): String = runBlocking {


        val key = System.getenv("CHAVEGOOGLE") ?: error("Chave não encontrada")

        //criação do Assistente Jurídico
        val assistJuri = AIAgent(
            promptExecutor = simpleGoogleAIExecutor(key),
            llmModel = GoogleModels.Gemini2_5Flash,
            systemPrompt = """
                Você é um Assistente Jurídico/Administrativo.
                Trate a pessoa como Dr. ou Dra. $nomeUsuario (identifique o gênero pelo nome).
                Inicie a primeira conversa com: "Olá, $nomeUsuario, como posso ajudar hoje? Você busca auxílio Jurídico ou Administrativo?"
                No decorrer da conversa será somente Dr. ou Dra. $nomeUsuario. (identifique o gênero pelo nome).
    
                REGRAS:
                1. Pedido Jurídico: Use linguagem forense (CPC/CPP).
                2. Pedido Administrativo: Use linguagem executiva, clara e direta.
                3. Use sempre os dados do processo fornecidos para preencher nomes e números.               
                """.trimIndent()
        )
        val response = assistJuri.run(mensagem)

        return@runBlocking response
    }
}
