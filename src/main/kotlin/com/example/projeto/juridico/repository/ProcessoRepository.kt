package com.example.projeto.juridico.repository

import com.example.projeto.juridico.model.Processo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProcessoRepository: JpaRepository<Processo, UUID > {
}