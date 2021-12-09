package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.DevUser
import model.Project
import java.lang.NumberFormatException

class DevUserService : AbstractService() {

    fun createUser(devs: JsonArray, dev: JsonObject): DevUser? {
        var user = DevUser()

        user.name = dev["name"]
        user.email = dev["email"]
        user.password = dev["password"]


        devs.add(JsonObject.mapFrom(user))

        return user
    }
}