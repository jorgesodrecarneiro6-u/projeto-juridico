package com.example.projeto.juridico.repository

import com.example.projeto.juridico.model.DocumentoJuridico
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DocumentoJuridicoRepository: JpaRepository<DocumentoJuridico, UUID> {
}