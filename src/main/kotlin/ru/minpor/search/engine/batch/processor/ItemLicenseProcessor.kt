package ru.minpor.search.engine.batch.processor

import org.springframework.batch.item.ItemProcessor
import ru.minpor.search.engine.batch.model.License

class ItemLicenseProcessor : ItemProcessor<License, License> {

    override fun process(item: License): License? {
        return item
    }

}