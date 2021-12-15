package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.ManagerUser
import model.DevUser
import model.Project
import tc.intern.project.handler.ResponseUser

class ManagerUserService : AbstractService() {

	fun createUser(managers: ArrayList<ManagerUser>, jsonInput: JsonObject): ManagerUser {
		var user = ManagerUser()

		user.id = returnManagerId(managers)
		user.name = jsonInput["name"]
		user.email = jsonInput["email"]
		user.password = jsonInput["password"]

		managers.add(user)

		return user
	}

	fun returnManagerId(managers: ArrayList<ManagerUser>): Int {
		var managerId: Int = 0;

		if (managers.isNotEmpty()) {
			managerId = managers.maxOf { manager -> manager.id } + 1
		}

		return managerId
	}

	fun createDevUser(managerLogged: ManagerUser, devs: ArrayList<DevUser>, jsonDev: JsonObject) : DevUser {
		var devUser: DevUser = DevUser()

		devUser.id = returnDevId(devs)
		devUser.name = jsonDev["name"]
		devUser.managerId = managerLogged.id
		devUser.credits = jsonDev["credits"]
		devUser.email = jsonDev["email"]

		val projectsJson: JsonArray = jsonDev["projects"]

		for (i in 0 until projectsJson.size()) {
			var project = Project()

			project.id = returnPorjectId(devUser)
			project.name =  projectsJson.getJsonObject(i)["name"]
			project.language = projectsJson.getJsonObject(i)["language"]

			devUser.projects.add(project)
		}

		devs.add(devUser)
		managerLogged.devs.add(devUser)

		return devUser
	}

	fun returnDevId(devs: ArrayList<DevUser>): Int {
		/*
		var DevId: Int = 0;

		if (devs.isEmpty()) {
			return DevId
		} else {
			DevId = devs.maxOf { it.id }
		}

		if (DevId == -1) {
			return
		}

		if (devs.isEmpty()) {
			DevId = 0
		} else {
			var idMax: Int = devs.getJsonObject(0)["id"]

			for (i in 1 until devs.size()) {
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

		 */
		return 0
	}

	fun changeDevCredits(manager: ManagerUser, managers: JsonArray, devs: JsonArray, devId: Int, credits: String): Boolean {
		val devFound: Boolean = findDevById(manager, managers, devs, devId)

		return devFound
	}

	fun findDevById(managerLodded: ManagerUser, managers: JsonArray, devs: JsonArray, devId: Int) : Boolean {
		var devFound: Boolean = false
		if (managerLodded.devs.isEmpty() || devs.isEmpty || managers.isEmpty) {
			return devFound
		} else {

			//devFound = managerLodded.devs!!.any { dev -> dev.id == devId }
			//devFound = managers.find { manager -> manager.equals(managerLodded)} != null

			//var size: Int = managers["devs"]
			//for(i in 1 until managers["devs"])

			/*
			devFound = managerLodded.devs.any { dev -> dev.id == devId }
			var devF: DevUser = managerLodded.devs.find { dev -> dev.id  == devId }!!
			var devFToJson: JsonObject? = JsonObject.mapFrom(devF)
			devFound = managers.getJsonObject(0).equals(devFToJson)
			*/

			//var managerDevs: JsonObject = managers.getJsonObject(0)

			//var devId: Int = managerDevs.getJsonObject(0.toString())["id"]
			/*
			for (i in 0 until devs.size()) {
				val id: Int = devs.getJsonObject(i)["id"]
				if (id == devId) {
					devFound = true
				}
			}

			 */

		}
		return devFound

	}

	fun returnJson(manager: ManagerUser) : JsonObject {
		val newManager = ResponseUser(
			id = manager.id,
			name = manager.name,
			credits = manager.credits
		)

		return JsonObject.mapFrom(newManager)
	}

	fun returnJson(managers: ArrayList<ManagerUser>) : JsonArray {
		var newManagers: JsonArray = JsonArray()

		managers.forEach{ manager ->
			newManagers.add(returnJson(manager))
		}

		return newManagers
	}
}