package com.example.core.corenetworking.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class DateDeserializer : JsonDeserializer<Date> {
    private val dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Date? {
        val formatter = SimpleDateFormat(dateFormat)

        try {
            if (json != null) {
                return formatter.parse(json.asString)
            }
            throw JsonParseException("Date is null")
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}
