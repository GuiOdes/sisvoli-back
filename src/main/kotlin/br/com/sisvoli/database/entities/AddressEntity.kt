package br.com.sisvoli.database.entities

import br.com.sisvoli.models.AddressModel
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_address")
class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    val id: UUID? = null,

    @Column(name = "zip_code", nullable = false, length = 255)
    val zipCode: String,

    @Column(name = "number", nullable = false, length = 255)
    val number: String,

    @Column(name = "street", nullable = false, length = 255)
    val street: String,

    @Column(name = "district", nullable = false, length = 255)
    val district: String,

    @Column(name = "complement", nullable = false, length = 255)
    val complement: String,

    @ManyToOne
    @JoinColumn(name = "city_id")
    val cityEntity: CityEntity,

    @OneToOne
    @JoinColumn(name = "user_owner_id")
    val userEntity: UserEntity
) {

    fun toAddressModel() = AddressModel(
        id = id,
        zipCode = zipCode,
        number = number,
        street = street,
        district = district,
        complement = complement,
        cityId = cityEntity.id!!,
        userId = userEntity.id!!
    )

    companion object {
        fun of(
            addressModel: AddressModel,
            cityEntity: CityEntity,
            userEntity: UserEntity
        ) = AddressEntity(
            id = addressModel.id,
            zipCode = addressModel.zipCode,
            number = addressModel.number,
            street = addressModel.street,
            district = addressModel.district,
            complement = addressModel.complement,
            cityEntity = cityEntity,
            userEntity = userEntity
        )
    }
}
