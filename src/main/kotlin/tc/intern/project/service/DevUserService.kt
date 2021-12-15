package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.DevUser

class DevUserService : AbstractService() {

    fun createUser(devs: JsonArray, dev: JsonObject): DevUser? {
        var user = DevUser()

        user.id = returnDevId(devs)
        user.name = dev["name"]
        user.email = dev["email"]
        user.password = dev["password"]


        devs.add(JsonObject.mapFrom(user))

        return user
    }

    fun returnDevId(devs: JsonArray): Int {
        var devId: Int;

        if (devs.isEmpty()) {
            devId = 0
        } else {

            var idMax: Int = devs.getJsonObject(0)["id"]

            for (i in 1 until devs.size()) {
                val id: Int = devs.getJsonObject(i)["id"]
                if (idMax < id) { idMax = id }
            }

            if(idMax == -1) {
                devId = 0
            } else {
                devId = idMax+1
            }
        }

        return devId;
    }
}