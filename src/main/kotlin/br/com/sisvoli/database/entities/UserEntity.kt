package br.com.sisvoli.database.entities

import br.com.sisvoli.api.requests.UserRequest
import br.com.sisvoli.enums.Gender
import br.com.sisvoli.models.UserModel
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.Email

@Entity
@Table(name = "tb_user")
class UserEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID? = null,

    @Column(name = "full_name", nullable = false, length = 255)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    val gender: Gender,

    @Column(name = "email", nullable = false, length = 255)
    @Email
    val email: String,

    @Column(name = "password", nullable = false, length = 255)
    var password: String,

    @Column(name = "cpf", nullable = false, length = 20)
    val cpf: String,

    @Column(name = "phone_number", nullable = false, length = 255)
    val phoneNumber: String? = null,

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "username", nullable = false, length = 255)
    val username: String,

    @Column(name = "creation_date", nullable = false, length = 255)
    @CreationTimestamp
    val creationDate: LocalDateTime? = null,

    @Column(name = "update_date", nullable = false, length = 255)
    @UpdateTimestamp
    val updateDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    val role: RoleEntity,

    @OneToOne(mappedBy = "userEntity")
    val addressEntity: AddressEntity? = null
) {

    fun toUserModel(): UserModel {
        return UserModel(
            id = id,
            name = name,
            gender = gender,
            email = email,
            password = password,
            cpf = cpf,
            phoneNumber = phoneNumber,
            birthDate = birthDate,
            username = username,
            creationDate = creationDate,
            updateDate = updateDate,
            roleName = role.name
        )
    }

    companion object {
        fun of(userModel: UserModel, roleEntity: RoleEntity): UserEntity {
            return UserEntity(
                id = userModel.id,
                name = userModel.name,
                gender = userModel.gender,
                email = userModel.email,
                password = userModel.password,
                cpf = userModel.cpf,
                phoneNumber = userModel.phoneNumber,
                birthDate = userModel.birthDate,
                username = userModel.username,
                creationDate = userModel.creationDate,
                updateDate = userModel.updateDate,
                role = roleEntity
            )
        }

        fun of(userRequest: UserRequest, roleEntity: RoleEntity): UserEntity {
            return UserEntity(
                name = userRequest.name,
                gender = Gender.valueOf(userRequest.gender),
                email = userRequest.email,
                password = userRequest.password,
                cpf = userRequest.cpf,
                phoneNumber = userRequest.phoneNumber,
                birthDate = userRequest.birthDate,
                username = userRequest.username,
                role = roleEntity
            )
        }
    }
}
