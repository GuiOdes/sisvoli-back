package br.com.sisvoli.api.controllers

import br.com.sisvoli.api.requests.PollRequest
import br.com.sisvoli.api.responses.PollResponse
import br.com.sisvoli.models.PollModel
import br.com.sisvoli.services.interfaces.PollService
import br.com.sisvoli.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poll")
class PollController (
    val jwtUtil: JWTUtil,
    val pollService: PollService
){
    @PostMapping("/new")
    fun save(@RequestBody pollRequest: PollRequest):ResponseEntity<PollModel>{
        val username = jwtUtil.getUsername()
        return ResponseEntity(pollService.save(pollRequest, username), HttpStatus.CREATED)
    }
    @GetMapping("/list")
    fun findAll(@RequestParam title: String?): List<Unit> {
        return pollService.findAll(title).map{it.toModel()}
    }
}