package br.com.sisvoli.database.entities

import br.com.sisvoli.models.VoteModel
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "tb_vote")
class VoteEntity(

    @EmbeddedId
    val id: UserOptionKey = UserOptionKey(),

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = true)
    val userEntity: UserEntity,

    @ManyToOne
    @MapsId("optionId")
    @JoinColumn(name = "option_id", nullable = true)
    val optionEntity: OptionEntity,

    @Column(name = "creation_date", nullable = false, length = 255)
    @CreationTimestamp
    val creationDate: LocalDateTime? = null
) {

    fun toVoteModel() = VoteModel(
        userId = userEntity.id!!,
        optionId = optionEntity.id!!,
        creationDate = creationDate
    )

    companion object {
        fun of(voteModel: VoteModel, userEntity: UserEntity, optionEntity: OptionEntity): VoteEntity {
            return VoteEntity(
                id = UserOptionKey(userEntity.id, optionEntity.id),
                userEntity = userEntity,
                optionEntity = optionEntity,
                creationDate = voteModel.creationDate
            )
        }
    }
}
