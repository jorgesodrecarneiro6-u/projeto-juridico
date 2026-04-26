package com.example.projeto.juridico.repository

import com.example.projeto.juridico.model.ChatMensagem
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatMensagemRepository: JpaRepository<ChatMensagem, UUID> {

    fun findBySessaoIdOrderByEnviadoEmAsc(sessaoId: UUID): List<ChatMensagem>

    //serve para paginação.
    // Irá permitir que a busca feita no CharService seja fetchada de 10 em 10.
    // Que foi acrescentada na class
    fun findBySessaoId(sessaoId: UUID, pageable: Pageable): List<ChatMensagem>
}