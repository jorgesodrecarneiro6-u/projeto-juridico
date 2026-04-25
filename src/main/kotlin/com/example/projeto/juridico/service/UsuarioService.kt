package com.example.projeto.juridico.service

import com.example.projeto.juridico.model.Usuario
import com.example.projeto.juridico.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun salvar(usuario: Usuario): Usuario {
        // Exemplo de lógica: evitar e-mails duplicados
        return usuarioRepository.save(usuario)
    }


}