package modelo


abstract class User(
    var name: String,
    val id: Int,
    var credict: Int,
) {
    var projects : MutableList<Project> = mutableListOf()



}





