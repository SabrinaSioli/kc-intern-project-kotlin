import modelo.Developer
import modelo.Manager
import modelo.Project

fun testUsers() {

    val dev = Developer(
        name = "pedro",
        credict = 0,
        id = 1
    )

    val manager = Manager(
        name = "Paulo",
        credict = 0,
        id = 2
    )

    println(dev)
    println(manager)

    var lista = mutableListOf<Project>()




    //criar via terminal
    //  manager, depois dev

}