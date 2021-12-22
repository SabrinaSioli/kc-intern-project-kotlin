package com.kc.intern.project.jackson

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kc.intern.project.freemarker.model.Screen
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get

class ConvertJsonToObject {
    private val mapper = jacksonObjectMapper()
    private val  jsonIgnore = """{
    "message": "OK",
    "application": {
        "screens": ["""

    private fun convertToScreen(json: String): Screen {
        return mapper.readValue<Screen>(json.replace(jsonIgnore, ""))
    }

    fun replaceJson(json: String): JsonObject {
        return JsonObject.mapFrom(json.replace(jsonIgnore, ""))
    }

    fun convertToScreen(json: JsonObject): Screen? {
        var screen: Screen? = null
        val jsonVerified: JsonObject? = verifyJson(json)
        if(jsonVerified != null) {
            val jsonString: String = jsonVerified.toString()
            screen = convertToScreen(jsonString)
        }
        return screen
    }

    private fun verifyJson(json: JsonObject): JsonObject? {
        val message: String = json["message"]
        var screen: JsonObject? = null
        if (message == "OK") {
            val app: JsonObject = json["application"]
            val screens: JsonArray = app["screens"]
            screen = screens[0]
        }

        return screen
    }
}