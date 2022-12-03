package br.com.sisvoli.database.entities

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.enums.PollType
import br.com.sisvoli.models.PollModel
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_poll")
class PollEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID? = null,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: PollType,

    @Column(name = "creation_date", nullable = false, length = 255)
    @CreationTimestamp
    val creationDate: LocalDateTime? = null,

    @Column(name = "update_date", nullable = false, length = 255)
    @UpdateTimestamp
    val updateDate: LocalDateTime? = null,

    @Column(name = "start_date", nullable = false)
    val startDate: LocalDateTime,

    @Column(name = "end_date", nullable = false)
    val endDate: LocalDateTime,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: PollStatus,

    @ManyToOne
    @JoinColumn(name = "user_owner_id", nullable = false)
    val userOwner: UserEntity,

    @OneToMany(mappedBy = "pollEntity", fetch = FetchType.LAZY)
    val optionList: MutableList<OptionEntity>? = null

) {
    fun toPollModel(): PollModel {
        return PollModel(
            id = id,
            title = title,
            description = description,
            type = type,
            creationDate = creationDate,
            updateDate = updateDate,
            startDate = startDate,
            endDate = endDate,
            status = status,
            userOwnerId = userOwner.id!!,
            optionList = optionList?.map { it.toOptionModel() }
        )
    }

    companion object {
        fun of(pollModel: PollModel, userEntity: UserEntity): PollEntity {
            return PollEntity(
                id = pollModel.id,
                title = pollModel.title,
                description = pollModel.description,
                type = pollModel.type,
                creationDate = pollModel.creationDate,
                updateDate = pollModel.updateDate,
                startDate = pollModel.startDate,
                endDate = pollModel.endDate,
                status = pollModel.status,
                userOwner = userEntity
            )
        }
    }
}
