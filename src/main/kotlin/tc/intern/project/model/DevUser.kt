package model

class DevUser : User {

	var manager: ManagerUser = ManagerUser()

	constructor() {}

	constructor(id: String, name: String, credits: String, manager: ManagerUser, projects: Array<Project>, email: String ,password: String) {
		this.id = id
		this.name = name
		this.credits = credits
		this.manager = manager
		this.projects.addAll(projects)
		this.email = email
		this.password = password
	}

	fun seeProfile() {
		println("""
			 - Profile -
			Id: $id
			Name: $name
			Email: $email
			Password: $password
			Credits: $credits
			manager: ${manager}
			""".trimIndent())
		println("Projects: ")
		for (i in 0..projects.size - 1) {
			var project: Project = projects.get(i)
			println("""
				${i+1} - id: ${project.id} , name:${project.name}
			""".trimIndent())
		}
		println()
	}

}
