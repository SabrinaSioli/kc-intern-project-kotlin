package model

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get

class Project {
	
	var id: Int = -1;
	var name: String = ""
	var language: String = ""

	
	constructor(){}

	constructor(id: Int, name: String, language: String){
		this.id = id
		this.name = name
		this.language = language
	}
	
}