package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.models.PollModel
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/poll")
class PollController(
    val jwtUtil: JWTUtil,
    val pollService: PollService
) {
    @PostMapping("/new")
    fun save(@RequestBody @Valid pollRequest: PollRequest): ResponseEntity<PollModel> {
        val userDocument = jwtUtil.getUserDocument()
        return ResponseEntity(pollService.save(pollRequest, userDocument), HttpStatus.CREATED)
    }
    @GetMapping("/list")
    fun findAll(): List<PollModel> {
        return pollService.findAll()
    }
    @GetMapping("/list/my")
    fun findAllByLoggedUser(): List<PollModel> {
        val userDocument = jwtUtil.getUserDocument()
        return pollService.findAllByLoggedUser(userDocument)
    }
    @GetMapping("/list/{pollId}")
    fun findById(@PathVariable pollId: UUID): PollModel {
        return pollService.findById(pollId)
    }
    @PatchMapping("/cancel/{pollId}")
    fun cancelById(@PathVariable pollId: UUID) {
        pollService.cancelById(pollId)
    }
}
