package com.example.projeto.juridico.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.util.UUID

//entidaed de usuário no banco de dados
@Entity
@Table(name = "usuarios")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,


    @Column(nullable = false)
    var nome: String,


    @Column(unique = true, nullable = false)
    var email: String,


    @Column(columnDefinition = "jsonb")
    var googleTokenData: String? = null,


    @CreationTimestamp
    val createdAt: LocalDate = LocalDate.now()

)



