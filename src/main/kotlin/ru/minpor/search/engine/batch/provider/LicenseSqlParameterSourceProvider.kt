package ru.minpor.search.engine.batch.provider

import com.google.gson.GsonBuilder
import org.postgresql.util.PGobject
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import ru.minpor.search.engine.batch.model.License
import java.util.*


class LicenseSqlParameterSourceProvider : ItemSqlParameterSourceProvider<License> {
    override fun createSqlParameterSource(item: License): SqlParameterSource {
        return MapSqlParameterSource(object : HashMap<String, PGobject>() {

            init {
                val jsonObject = PGobject()
                jsonObject.type = "json"
                jsonObject.value = GsonBuilder().create().toJson(item)
                put("json", jsonObject)
            }
        })
    }


}
