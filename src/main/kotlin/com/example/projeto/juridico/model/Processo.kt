package com.example.projeto.juridico.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

//Bakend da planilha web
@Entity
class Processo(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    val usuario: Usuario,


    var numeroProcesso: String?,
    var nomeCliente: String?,


    @Column(columnDefinition = "text")
    var descricao: String?,


    var status: String = "PENDENTE",


    var dataLimite: LocalDate?,


    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null


)
