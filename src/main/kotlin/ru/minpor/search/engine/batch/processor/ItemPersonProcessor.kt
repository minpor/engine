package ru.minpor.search.engine.batch.processor

import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import ru.minpor.search.engine.batch.listener.ItemCountListener
import ru.minpor.search.engine.batch.model.Person

class ItemPersonProcessor: ItemProcessor<Person, Person> {
    companion object {
        val log = LoggerFactory.getLogger(ItemPersonProcessor::class.java)
    }


    override fun process(item: Person): Person? {
        log.info("process: $item")
        return item
    }

}