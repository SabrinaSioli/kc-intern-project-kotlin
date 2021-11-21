package modelo

class Developer(
    name: String,
    id: Int,
    credict: Int = 0,
) : User(
    name = name,
    id = id,
    credict = credict
){
    val gerente: Manager
        get() {
            return gerente
        }

    override fun toString(): String {
        return """
            ## Developer
            Name: $name
            Id: $id
            Crédito: $credict
            
        """.trimIndent()

    }
}