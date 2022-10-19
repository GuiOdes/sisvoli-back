package br.com.sisvoli.database.entities

import br.com.sisvoli.models.OptionModel
import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_poll_option")
class OptionEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID? = null,

    @Column(name = "option_name", nullable = false, length = 255)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    val pollEntity: PollEntity
) {

    fun toOptionModel() = OptionModel(
        id = id,
        name = name,
        pollId = pollEntity.id!!
    )

    companion object {
        fun of(optionModel: OptionModel, pollEntity: PollEntity) = OptionEntity(
            id = optionModel.id,
            name = optionModel.name,
            pollEntity = pollEntity
        )
    }
}
