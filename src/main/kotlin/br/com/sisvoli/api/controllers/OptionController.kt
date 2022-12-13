package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.OptionRequest
import br.com.sisvoli.models.OptionModel
import br.com.sisvoli.services.interfaces.OptionService
import br.com.sisvoli.services.interfaces.VoteService
import br.com.sisvoli.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/option")
class OptionController(
    val jwtUtil: JWTUtil,
    val optionService: OptionService,
    val voteService: VoteService
) {
    @GetMapping("/list/{pollId}")
    fun listByPollId(@PathVariable pollId: UUID): List<OptionModel> {
        return optionService.findAllByPollId(pollId)
    }
    @PostMapping("/new")
    fun save(@RequestBody @Valid optionRequest: OptionRequest): ResponseEntity<List<OptionModel>> {
        val userDocument = jwtUtil.getUserDocument()
        return ResponseEntity(optionService.save(optionRequest, userDocument), HttpStatus.CREATED)
    }
    @PostMapping("/{optionId}/vote")
    fun pollVote(@PathVariable optionId: UUID): ResponseEntity<Unit> {
        return ResponseEntity(voteService.addVote(jwtUtil.getUserDocument(), optionId), HttpStatus.CREATED)
    }
    @DeleteMapping("/{optionId}")
    fun deleteById(@PathVariable optionId: UUID) {
        optionService.deleteById(optionId, jwtUtil.getUserDocument())
    }
}
