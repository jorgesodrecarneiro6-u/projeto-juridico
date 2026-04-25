package com.example.projeto.juridico.controller

import com.example.projeto.juridico.model.Usuario
import com.example.projeto.juridico.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @PostMapping
    fun criarUsuario(@RequestBody usuario: Usuario) = ResponseEntity.ok(usuarioService.salvar(usuario))

}
