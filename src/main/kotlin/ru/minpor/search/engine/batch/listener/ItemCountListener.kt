package ru.minpor.search.engine.batch.listener

import org.slf4j.LoggerFactory
import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.scope.context.ChunkContext

class ItemCountListener: ChunkListener {

    companion object {
        val log = LoggerFactory.getLogger(ItemCountListener::class.java)
    }

    override fun afterChunk(context: ChunkContext) {
        val stepExecution = context.stepContext.stepExecution
        log.info("ItemCount: ${stepExecution.readCount}")
    }

    override fun afterChunkError(context: ChunkContext) {

    }

    override fun beforeChunk(context: ChunkContext) {

    }

}
