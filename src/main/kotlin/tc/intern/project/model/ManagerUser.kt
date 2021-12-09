package model


class ManagerUser : User {

	var devs: ArrayList<DevUser> = ArrayList<DevUser>();

	constructor() {}

	constructor(id: String, name: String, credits: String, devs: Array<DevUser>, projects: Array<Project>, email: String, password: String) {
		this.id = id
		this.name = name
		this.credits = credits
		this.devs.addAll(devs)
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
		""".trimIndent())
		print("Projects:")
		if (projects.isEmpty()) print(" none")
		println()
		for (i in 0..projects.size - 1) {
			var project: Project = projects.get(i)
			println("""
				${i+1} - id: ${project.id} , Name: ${project.name}
			""".trimIndent())
		}
		print("Devs:")
		if (devs.isEmpty()) print(" none")
		println()
		for (i in 0..devs.size - 1) {
			var dev: DevUser = devs.get(i)
			println("""
				${i+1} - id: ${dev.id} , name: ${dev.name} , credits: ${dev.credits}
			""".trimIndent())
		}
		println()
	}
}