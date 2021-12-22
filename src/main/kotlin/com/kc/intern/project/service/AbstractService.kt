package service

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import model.Project
import model.User

abstract class AbstractService {

	fun createProject(user: User, jsonProject: JsonObject) : Project? {
		if(user.id == -1) { return null }

		var project: Project = Project()

		project.id = returnPorjectId(user)
		project.name = jsonProject["name"]
		project.language = jsonProject["language"]

		user.projects.add(project)

		return project
	}


	fun returnPorjectId(user: User): Int {
		var projectId: Int = 0;

		if (user.projects.isNotEmpty()) {
			projectId = user.projects.maxOf { it.id } + 1
		}

		return projectId;
	}

	fun deleteProject(user:User, projectId: Int) {
		var project: Project = user.projects.single { it.id == projectId }
		user.projects.remove(project)
	}
/*
	fun parserToHtml(value: JsonObject): String {
		val jsonMapper = jacksonObjectMapper()
		val cfg = Configuration()
		var user: ResponseUser? = jsonMapper.readValue<ResponseUser>(value.toString(), ResponseUser::class.java)
			?: return ""
		return cfg.parseTemplate(user, "users.ftlh")
	}

 */

	/*
	fun parserToHtml(value: JsonArray): String {
		val jsonMapper = jacksonObjectMapper()
		val cfg = Configuration()
		var users: ArrayList<ResponseUser> = jsonMapper.readValue(value.toString(),
			TypeReference<ArrayList<ResponseUser>>(){})
		return cfg.parseTemplate(users, "users.ftlh")
	}

	 */

}