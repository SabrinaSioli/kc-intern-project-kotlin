package model

class DevUser : User {

	var managerId: Int = -1

	constructor() {}

	constructor(id: Int, name: String, credits: String, managerId: Int, projects: Array<Project>, email: String, password: String) {
		this.id = id
		this.name = name
		this.credits = credits
		this.managerId = managerId
		this.projects.addAll(projects)
		this.email = email
		this.password = password
	}

}
