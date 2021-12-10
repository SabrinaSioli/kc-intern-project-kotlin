package tc.intern.project

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import model.DevUser
import model.ManagerUser
import model.Project
import tc.intern.project.verticle.DevVerticle
import tc.intern.project.verticle.ManagerVerticle

class MainVerticle : AbstractVerticle () {

  //val devs: MutableMap<Int, JsonObject> = HashMap()
  //val managers: MutableMap<Int, JsonObject> = HashMap()
  val devs: JsonArray = JsonArray()
  val managers: JsonArray = JsonArray()

  override fun start() {

    //START THE SERVER
    val router = Router.router(vertx)
    router.route().handler(BodyHandler.create())

    // USER LOGGED
    var managerLogged: ManagerUser? = null
    var devLogged: DevUser? = null

    // VERTICLES
    val devVerticle: DevVerticle = DevVerticle()
    val managerVerticle: ManagerVerticle = ManagerVerticle()

    //SET UP INICIAL DATA
    devLogged = setUpInitialDataDev()
    managerLogged = setUpInitialDataManager()
    setUpInitialDataManagers()
    setUpInitialDataManagers()

    /** MANAGER ENDPOINTS */
    router.get("/login/manager").handler { managerVerticle.returnManagerLogged(managerLogged, it)}
    router.get("/login/manager/managers").handler{ managerVerticle.handleListManagers(managers, it) }

    /** DEV ENDPOINTS */

    //GET
    router.get("/login/dev").handler{ devVerticle.returnDevLogged(devLogged, it) }
    router.get("/dev/devs").handler { devVerticle.handleListDevs(devs, it) }
    //POST
    router.post("/signUp/dev").handler { devLogged = devVerticle.createDevUser(devs, it) }
    router.post("/dev/project").handler { devVerticle.createProject(devLogged!!, it)  }
    //DELETE
    router.delete("/dev/projects/:projectId").handler { devVerticle.deleteProject(devLogged!!, it) }

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }

  private fun setUpInitialDataDev() :DevUser {
    var dev = DevUser()
    dev.id = 0
    dev.name = "inicialDev"

    var project = Project()
    project.id = 0
    dev.projects.add(project)

    devs.add(JsonObject.mapFrom(dev))

    return dev
  }

  private fun setUpInitialDataManager() :ManagerUser {
    var manager = ManagerUser()
    manager.id = 0
    manager.name = "inicialManager"

    var project = Project()
    project.id = 0
    manager.projects.add(project)

    devs.add(JsonObject.mapFrom(manager))

    return manager
  }

  private fun setUpInitialDataManagers() {
    var manager1 = ManagerUser()
    manager1.id = 1

    var project1 = Project()
    project1.id = 0
    manager1.projects.add(project1)

    managers.add(JsonObject.mapFrom(manager1))

    var manager2 = ManagerUser()
    manager2.id = 2

    var project2 = Project()
    project2.id = 0
    manager2.projects.add(project2)

    managers.add(JsonObject.mapFrom(manager2))
  }

  private fun setUpInitialDataDevs() {
    var dev1 = ManagerUser()
    dev1.id = 1

    var project1 = Project()
    project1.id = 0
    dev1.projects.add(project1)

    devs.add(JsonObject.mapFrom(dev1))

    var dev2 = ManagerUser()
    dev2.id = 2

    var project2 = Project()
    project2.id = 0
    dev2.projects.add(project2)

    devs.add(JsonObject.mapFrom(dev2))
  }


}
