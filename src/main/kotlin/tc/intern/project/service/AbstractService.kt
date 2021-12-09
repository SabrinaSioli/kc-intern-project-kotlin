package service

import model.Project
import model.User

abstract class AbstractService {

	fun createProject(user: User) {
		var op = -1
		var id : String = "1"

		print("Project's name: ")
		var name: String = readLine()!!.toString()
		/*
		if (user.projects.isEmpty()) {
			id = 0
		} else {
			id = user.projects.last().id + 1
		}

		 */

		var project: Project = Project(id, name)

		user.projects.add(project);

		println("""
			Do you want to create another one?
			1 - Yes
			2 - No
		""".trimIndent())

		try {
			op = readLine()!!.toInt()
		} catch (e : NumberFormatException) {
			println("Error! :/")
		}

		if (op == 1) createProject(user)
	}


	fun deleteProject(user: User) {
		/*
		var continueDelete = true

		while(continueDelete) {
			//Show the projects
			if (user.projects.isEmpty()) {
				println("None")
			}
			user.projects.forEach { project ->
				println{"""
				id: ${project.id} name: ${project.name}
				""".trimIndent()}
			}
			println("")

			//Input the id
			print("Project's Id: ")
			var projectId: Int = readLine()!!.toInt()

			//test id
			var project: Project? = user.projects.find { project -> project.id == projectId }

			if (project != null){ // id exists
				user.projects.remove(project)

				println("""
				Do you want delete another project?
				0 - yes
				1 - No
				""".trimIndent())
				var op = readLine()!!.toInt()

				if (op == 1) continueDelete = false
			} else { //id not exists
				println("Id not found.\n")
				println("""
					Do you want:
					0 - try again
					1 - return
				""".trimIndent())

				var op = readLine()!!.toInt()

				if (op == 1) continueDelete = false
			}
		}
		 */

	}

}