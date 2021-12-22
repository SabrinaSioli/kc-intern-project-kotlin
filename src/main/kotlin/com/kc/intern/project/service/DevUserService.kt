package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.DevUser
import model.Project
import com.kc.intern.project.model.ResponseUser

class DevUserService : AbstractService() {

    fun createUser(devs: ArrayList<DevUser>, devJson: JsonObject): DevUser {
        var devUser = DevUser()
        val projectsJson: JsonArray = devJson["projects"]

        devUser.id = returnDevId(devs)
        devUser.name = devJson["name"]
        devUser.email = devJson["email"]
        devUser.password = devJson["password"]

        for(i in 1 until projectsJson.size()) {
            var project: Project = Project()
            project.id = 0
            project.name = projectsJson.getJsonObject(i)["name"]
            project.language = projectsJson.getJsonObject(i)["language"]

            devUser.projects.add(project)
        }

        devs.add(devUser)

        return devUser
    }

    fun returnDevId(devs: ArrayList<DevUser>): Int {
        var devId: Int = 0
        if (devs.isNotEmpty()) {
            devId = devs.maxOf { dev -> dev.id } + 1
        }
        return devId
    }

    fun returnJson(dev: DevUser) : JsonObject {

        val newUser = ResponseUser(
            id = dev.id,
            name = dev.name,
            credits = dev.credits,
            projects = dev.projects
        )

        return JsonObject.mapFrom(newUser)
    }

    fun returnJson(devs: ArrayList<DevUser>) : JsonArray {
        var newDevs: JsonArray = JsonArray()

        devs.forEach{ dev ->
            newDevs.add(returnJson(dev))
        }

        return newDevs
    }
}