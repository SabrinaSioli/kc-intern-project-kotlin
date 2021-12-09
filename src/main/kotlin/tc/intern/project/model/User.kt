package model

open class User {
	var id: Int = -1
	var name: String = ""
	var credits: Int = 0
	var projects: ArrayList<Project> = ArrayList<Project>()
	var email: String = "admin"
	var password: Int = 1234


	//should I create a construct here?
}