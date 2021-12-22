package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.ManagerUser
import model.DevUser
import model.Project
import model.User
import com.kc.intern.project.model.ResponseUser

class ManagerUserService : AbstractService() {

	fun createUser(managers: ArrayList<ManagerUser>, jsonInput: JsonObject): ManagerUser? {
		var user = ManagerUser()

		val id : Int? = returnManagerId(managers)
		if (id == null) { return null }
		user.id = id
		user.name = jsonInput["name"]
		user.email = jsonInput["email"]
		user.password = jsonInput["password"]

		managers.add(user)

		return user
	}

	fun returnManagerId(managers: ArrayList<ManagerUser>): Int? {
		var managerId: Int = 0;

		if (managers.isNotEmpty()) {
			managerId = managers.maxOf { manager -> manager.id }
		}
		if (managerId == -1) {
			return null
		}
		return managerId + 1
	}

	fun createDevUser(managerLogged: ManagerUser, devs: ArrayList<DevUser>, jsonDev: JsonObject) {
		var devUser: DevUser = DevUser()

		devUser.id = returnDevId(devs)
		devUser.name = jsonDev["name"]

		if(jsonDev.containsKey("credits")) {
			devUser.credits = jsonDev["credits"]
		}
		devUser.email = jsonDev["email"]

		if(jsonDev.containsKey("projects")) {
			val projectsJson: JsonArray = jsonDev["projects"]

			for (i in 0 until projectsJson.size()) {
				var project = Project()

				project.id = returnPorjectId(devUser)
				project.name =  projectsJson.getJsonObject(i)["name"]
				project.language = projectsJson.getJsonObject(i)["language"]

				devUser.projects.add(project)
			}
		}
		devs.add(devUser)
		managerLogged.devs.add(devUser)
	}

	fun returnDevId(devs: ArrayList<DevUser>): Int {
		var DevId: Int = 0

		if(devs.isNotEmpty()) {
			DevId = devs.maxOf { dev -> dev.id } + 1
		}

		return DevId
	}

	fun changeDevCredits(manager: ManagerUser, devId: Int, credits: String) : DevUser {
		var devFound: DevUser = manager.devs.single { dev -> dev.id == devId }

		devFound.credits = credits

		return devFound
	}

	fun returnJson(user: User) : JsonObject {

		val newUser = ResponseUser(
			id = user.id,
			name = user.name,
			credits = user.credits,
			projects = user.projects
		)

		return JsonObject.mapFrom(newUser)
	}

	fun returnJson(managers: ArrayList<ManagerUser>) : JsonArray {
		var newManagers: JsonArray = JsonArray()

		managers.forEach{ manager ->
			newManagers.add(returnJson(manager))
		}

		return newManagers
	}
}