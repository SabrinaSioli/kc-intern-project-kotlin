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


		if (user.projects.isEmpty() != true) {
			projectId = user.projects.maxOf { it.id }
		}


		return projectId+1;
	}



}