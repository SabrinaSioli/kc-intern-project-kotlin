package model

class DevUser : User {

	var manager: ManagerUser = ManagerUser()

	constructor() {}

	constructor(id: Int, name: String, credits: String, manager: ManagerUser, projects: Array<Project>, email: String, password: String) {
		this.id = id
		this.name = name
		this.credits = credits
		this.manager = manager
		this.projects.addAll(projects)
		this.email = email
		this.password = password
	}

}
