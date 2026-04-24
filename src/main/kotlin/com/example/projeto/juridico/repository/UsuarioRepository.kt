package com.example.projeto.juridico.repository

import com.example.projeto.juridico.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UsuarioRepository: JpaRepository<Usuario, UUID> {

}