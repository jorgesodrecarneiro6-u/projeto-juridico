package com.example.projeto.juridico.dto

import java.util.UUID

data class ChatRequest(
    val usuarioId: UUID,
    val sessaoId: UUID,
    val mensagem: String
)
