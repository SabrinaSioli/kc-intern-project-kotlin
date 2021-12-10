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
        var DevId: Int;

        if (devs.isEmpty()) {
            DevId = 0
        } else {
            var idMax: Int = devs.getJsonObject(0)["id"]
            val sizeMinusOne = devs.size() - 1

            for (i in 1 .. sizeMinusOne) {
                val id: Int = devs.getJsonObject(i)["id"]
                if (idMax < id) { idMax = id }
            }

            if(idMax == -1) {
                DevId = 0
            } else {
                DevId = idMax+1
            }
        }

        return DevId;
    }
}