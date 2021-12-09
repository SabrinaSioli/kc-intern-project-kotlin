package tc.intern.project

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import model.DevUser
import model.ManagerUser
import model.Project
import service.DevUserService
import service.ManagerUserService
import service.MenuService
import tc.intern.project.verticle.DevVerticle
import tc.intern.project.verticle.ManagerVerticle


import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

    //setUpInitialData()

    /*
    /** MANAGER ENDPOINTS */
    router.get("/login/manager").handler { managerVerticle.returnManagerLogged(managerLogged, it)}
    router.get("/login/manager/managers").handler{ managerVerticle.handleListManagers(managers, it) }
  */
    /** DEV ENDPOINTS */

    //GET
    router.get("/login/dev").handler{ devVerticle.returnDevLogged(devLogged, it) }
    router.get("/dev/devs").handler { devVerticle.handleListDevs(devs, it) }
    //POST
    router.post("/signUp/dev").handler { devLogged = devVerticle.createDevUser(devs, it) }
    router.post("/dev/project").handler { devVerticle.createProject(devLogged!!, it)  }
    //DELETE
    //router.delete("/dev/projects/:projectId").handler { devVerticle.deleteProject(devLogged, it) }

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }
  /*
  private fun setUpInitialData() {
    addDev(JsonObject().put("id", "0").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))
    addDev(JsonObject().put("id", "1").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))
    addDev(JsonObject().put("id", "2").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))


  }

  private fun addDev(dev: JsonObject) {
    devs[dev.getString("id")] = dev
  }
  */
  /*
  override fun start(startPromise: Promise<Void>) {
    vertx
      .createHttpServer()
      .requestHandler { req ->
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!")
      }
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause());
        }
      }
  }
   */
}
