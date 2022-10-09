package br.com.sisvoli.database.repositories.implementations

import br.com.sisvoli.database.entities.AddressEntity
import br.com.sisvoli.database.repositories.interfaces.AddressRepository
import br.com.sisvoli.database.repositories.springData.AddressSpringDataRepository
import br.com.sisvoli.database.repositories.springData.CitySpringDataRepository
import br.com.sisvoli.database.repositories.springData.UserSpringDataRepository
import br.com.sisvoli.exceptions.notFound.CityNotFoundException
import br.com.sisvoli.exceptions.notFound.UserNotFoundException
import br.com.sisvoli.models.AddressModel
import org.springframework.stereotype.Component

@Component
class AddressRepositoryImpl(
    val addressSpringDataRepository: AddressSpringDataRepository,
    val userSpringDataRepository: UserSpringDataRepository,
    val citySpringDataRepository: CitySpringDataRepository
) : AddressRepository {
    override fun save(addressModel: AddressModel): AddressModel {
        val userEntity = userSpringDataRepository.findById(addressModel.userId).orElseThrow { UserNotFoundException() }
        val cityEntity = citySpringDataRepository.findById(addressModel.cityId).orElseThrow { CityNotFoundException() }

        val addressEntity = AddressEntity.of(addressModel, cityEntity, userEntity)

        return addressSpringDataRepository.save(addressEntity).toAddressModel()
    }
}
