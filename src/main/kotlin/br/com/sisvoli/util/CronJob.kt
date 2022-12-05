package br.com.sisvoli.util

import br.com.sisvoli.enums.PollStatus
import br.com.sisvoli.services.interfaces.PollService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class CronJob(
    private val pollService: PollService
) {

    @Scheduled(cron = "0 * * * * *")
    fun startScheduledPolls() {
        val scheduledPolls = pollService.findAllScheduledFromToday()

        scheduledPolls.forEach {
            if (it.startDate.format(dateFormatter) == LocalDateTime.now().format(dateFormatter) &&
                it.status == PollStatus.SCHEDULED && (it.optionList?.size ?: 0) >= 2
            ) {
                logger.info { "Starting scheduled poll #${it.id}..." }
                pollService.changeStatusById(it.id!!, PollStatus.PROGRESS)
            }
        }
    }

    @Scheduled(cron = "0 * * * * *")
    fun endScheduledPolls() {
        val scheduledPolls = pollService.findAllPollsToEndToday()

        scheduledPolls.forEach {
            if (it.endDate.format(dateFormatter) == LocalDateTime.now().format(dateFormatter) &&
                it.status == PollStatus.PROGRESS
            ) {
                logger.info { "Ending scheduled poll #${it.id}..." }
                pollService.changeStatusById(it.id!!, PollStatus.FINALIZED)
            }
        }
    }

    companion object {
        val logger = KotlinLogging.logger { }
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    }
}
