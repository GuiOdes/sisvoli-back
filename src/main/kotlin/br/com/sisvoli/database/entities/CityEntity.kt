package br.com.sisvoli.database.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_city")
class CityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    val id: Long? = null,

    @Column(name = "city_name", nullable = false, length = 255)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    val state: StateEntity
)
