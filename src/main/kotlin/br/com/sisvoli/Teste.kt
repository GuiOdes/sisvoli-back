package br.com.sisvoli

import br.com.sisvoli.database.entities.StateEntity
import br.com.sisvoli.database.repositories.StateSpringDataRepository
import br.com.sisvoli.models.StateModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/state")
class Teste(
    val stateSpringDataRepository: StateSpringDataRepository
) {

    @PostMapping("/save")
    fun teste(): StateModel {
        return stateSpringDataRepository.save(StateEntity(
            name = "teste",
            uf = "as"
        )).toStateModel()

//        return stateSpringDataRepository.findAll()
//            .map { it.toStateModel() }
    }
}