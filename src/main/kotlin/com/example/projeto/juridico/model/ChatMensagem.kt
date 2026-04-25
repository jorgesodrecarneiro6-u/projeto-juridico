package com.example.projeto.juridico.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.UUID
@Entity
class ChatMensagem(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    val usuario: Usuario,


    // Identificador único para cada conversa
    val sessaoId: UUID,


    // Enum: USER, ASSISTANT
    @Enumerated(EnumType.STRING)
    val remetente: com.example.projeto.juridico.model.Role,


    @Column(columnDefinition = "text")
    val mensagem: String,


    @CreationTimestamp
    val enviadoEm: LocalDateTime = LocalDateTime.now()


)
