class Developer(
    name: String,
    id: Int,
    credict: Int,
) : User(
    name = name,
    id = id,
    credict = credict
){
    val gerente: Manager
        get() {
            return gerente
        }


}