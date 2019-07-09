package ru.minpor.search.engine.batch.provider

import com.beust.klaxon.Klaxon
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import ru.minpor.search.engine.batch.model.Person
import java.util.*


class PersonSqlParameterSourceProvider : ItemSqlParameterSourceProvider<Person> {
    override fun createSqlParameterSource(item: Person): SqlParameterSource {
        return MapSqlParameterSource(object : HashMap<String, Any>() {

            init {
                put("json", Klaxon().toJsonString(item))
            }
        })
    }


}
