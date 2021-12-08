package model

class Project {
	
	var id: Int = 0;
	var name: String = ""

	
	constructor(){}

	constructor(id: Int, name: String){
		this.id = id
		this.name = name
	}
	
}