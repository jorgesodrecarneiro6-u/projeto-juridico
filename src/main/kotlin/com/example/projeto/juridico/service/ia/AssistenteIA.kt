package com.example.projeto.juridico.service.ia

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor
import com.example.projeto.juridico.model.ChatMensagem
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AssistenteIA {
    /*Lógica do assistente Jurídico IA: Responder a mensagem do usuário utilizando a class ChatMensagem
    nos campos mensagem e sessaoId(verificar o histórico da conversa)
    */

    fun assistenteJuridicoIA(mensagem: String, sessaoId: UUID): String = runBlocking {

        val key = System.getenv("CHAVEGOOGLE") ?: error("Chave não encontrada")

        //criação do Assistente Jurídico
        val assistJuri = AIAgent(
            promptExecutor = simpleGoogleAIExecutor(key),
            llmModel = GoogleModels.Gemini2_5Flash,
            systemPrompt ="Você é um Assistente Júridico focado em Leis Juídicas e documentos administrativos"
        )
        val response = assistJuri.run { mensagem }

        return@runBlocking response
    }
}
