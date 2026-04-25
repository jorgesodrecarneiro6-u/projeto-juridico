package com.example.projeto.juridico.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.UUID

//Entidade de documentos (Petiçãoe, Recursos, etc)
@Entity
class DocumentoJuridico(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    val usuario: Usuario,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id")
    val processo: Processo? = null,


    // Petição, Recurso, Solicitação
    var titulo: String?,
    var tipoDocumento: String?,


    // Onde o assistente salva o texto gerado
    @Column(columnDefinition = "text")
    var conteudoHtml: String?,


    @CreationTimestamp
    val criadoEm: LocalDateTime = LocalDateTime.now()
)
