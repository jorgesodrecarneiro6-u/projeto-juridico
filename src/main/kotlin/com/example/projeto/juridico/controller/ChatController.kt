package com.example.projeto.juridico.controller

import com.example.projeto.juridico.dto.ChatRequest
import com.example.projeto.juridico.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatController(private val chatService: ChatService) {

    @PostMapping("/enviar")
    fun enviarMensagem(@RequestBody request: ChatRequest): ResponseEntity<String> {
        val resposta = chatService.processarChat(
            usuarioId = request.usuarioId,
            mensagem = request.mensagem,
            sessaoId = request.sessaoId
        )
        return ResponseEntity.ok(resposta)
    }


}