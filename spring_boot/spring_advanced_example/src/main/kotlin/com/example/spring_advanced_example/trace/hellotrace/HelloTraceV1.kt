package com.example.spring_advanced_example.trace.hellotrace

import com.example.spring_advanced_example.trace.TraceId
import com.example.spring_advanced_example.trace.TraceStatus
import mu.KotlinLogging
import org.springframework.stereotype.Component


val logger = KotlinLogging.logger {}
@Component
class HelloTraceV1 {

    private val START_PREFIX = "-->"
    private val COMPLETE_PREFIX = "<--"
    private val EX_PREFIX = "<X-"

    fun begin(message: String): TraceStatus{
        val traceId = TraceId()
        logger.info{ "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}"}
        return TraceStatus(traceId, message, System.currentTimeMillis())
    }

    fun end(status: TraceStatus){
        complete(status, null);
    }

    fun exception(status: TraceStatus, e: Exception){
        complete(status, e);
    }

    private fun complete(status: TraceStatus, e: java.lang.Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId = status.traceId
        if (e == null) {
            logger.info(
                "[{}] {}{} time={}ms",
                traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level),
                status.message,
                resultTimeMs
            )
        } else {
            logger.info(
                "[{}] {}{} time={}ms ex={}",
                traceId.id,
                addSpace(EX_PREFIX, traceId.level),
                status.message,
                resultTimeMs,
                e.toString()
            )
        }
    }

    private fun addSpace(prefix: String, level: Int): String =
        List(level) { i -> if (i == level - 1) "|$prefix" else "|   " }.joinToString(separator = "")
}
