package model

class Project {
	
	var id: String = "0";
	var name: String = ""

	
	constructor(){}

	constructor(id: String, name: String){
		this.id = id
		this.name = name
	}
	
}