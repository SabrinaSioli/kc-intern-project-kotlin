package service

import contIdProject
import main
import model.DevUser
import model.ManagerUser
import model.Project
import model.User

abstract class AbstractService {

	/*
	open fun seeProfile(user: User) {

	}
	*/



	fun createProject(user: User) {
		var op = -1
		print("Project's name: ")
		var name: String = readLine()!!.toString()

		var project: Project = Project(name)

		project.id = contIdProject++
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

		if (op == 1) createProject(user) //ask about this
	}
	//A dont Know if works


	fun deleteProject(user: User) {
		user.projects.forEach { project ->
			println{"""
				id: ${project.id} name: ${project.name}
			""".trimIndent()}
		}
		println("")
		println("Project's Id: ")
		var projectId: Int = readLine()!!.toInt()

		var project: Project? = user.projects.find { project -> project.id == projectId }
		user.projects.remove(project)


	}



}