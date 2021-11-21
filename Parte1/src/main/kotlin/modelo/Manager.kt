package modelo

class Manager(
    name: String,
    id: Int,
    credict: Int = 0,
) : User(
    name = name,
    id = id,
    credict = credict
){
    //private val developers : Array<Manager> = ManagerArrayOf()

    override fun toString(): String {
        return """
            ## Manager
            Name: $name
            Id: $id
            Crédito: $credict
            
        """.trimIndent()
    }
}
/*
fun ManagerArrayOf(vararg managers: Manager) : Array<Manager> {
    return Array<Manager> (managers.size) {
        managers[it]
    }
}
*/



