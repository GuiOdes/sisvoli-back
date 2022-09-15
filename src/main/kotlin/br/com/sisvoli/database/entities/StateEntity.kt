package br.com.sisvoli.database.entities

import br.com.sisvoli.models.StateModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_state")
class StateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "state_name", nullable = false, length = 255)
    val name: String,

    @Column(name = "uf", nullable = false, length = 2)
    val uf: String,

    @OneToMany(mappedBy = "state")
    val cityList: List<CityEntity>? = null
) {

    fun toStateModel(): StateModel {
        return StateModel(
            id = id!!,
            name = name,
            uf = uf
        )
    }

    companion object {
        fun of(stateModel: StateModel): StateEntity {
            return StateEntity(
                id = stateModel.id,
                name = stateModel.name,
                uf = stateModel.uf
            )
        }
    }
}
