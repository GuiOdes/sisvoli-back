package br.com.sisvoli.database.entities

import br.com.sisvoli.models.RoleModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_role")
class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "role_name")
    val name: String,

    @OneToMany(mappedBy = "role")
    val userList: List<UserEntity>? = null
) {

    fun toRoleModel(): RoleModel {
        return RoleModel(
            id = id,
            name = name
        )
    }

    companion object {
        fun of(roleModel: RoleModel): RoleEntity {
            return RoleEntity(
                id = roleModel.id,
                name = roleModel.name
            )
        }
    }
}
