package br.com.sisvoli.database.entities

import br.com.sisvoli.models.PasswordRecoveryTokenModel
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_password_token_recovery")
class PasswordRecoveryTokenEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID? = null,

    @Column(name = "token", nullable = false)
    val token: String,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val userEntity: UserEntity,

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    val creationDate: LocalDateTime? = null
) {

    fun toPasswordRecoveryTokenModel() = PasswordRecoveryTokenModel(
        id = id,
        token = token,
        userId = userEntity.id!!,
        creationDate = creationDate
    )

    companion object {
        fun of(
            passwordRecoveryTokenModel: PasswordRecoveryTokenModel,
            userEntity: UserEntity
        ) = PasswordRecoveryTokenEntity(
            id = passwordRecoveryTokenModel.id,
            token = passwordRecoveryTokenModel.token,
            userEntity = userEntity,
            creationDate = passwordRecoveryTokenModel.creationDate
        )
    }
}
