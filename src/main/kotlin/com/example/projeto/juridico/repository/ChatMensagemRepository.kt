package com.example.projeto.juridico.repository

import com.example.projeto.juridico.model.ChatMensagem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatMensagemRepository: JpaRepository<ChatMensagem, UUID> {
}