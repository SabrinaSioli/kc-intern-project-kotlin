package model

open class User {
	var id: Int = -1
	var name: String = ""
	var credits: String = "0"
	var projects: ArrayList<Project> = ArrayList<Project>()
	var email: String = "admin"
	var password: String = "1234"

}