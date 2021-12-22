package com.kc.intern.project

import com.kc.intern.project.verticle.DevVerticle
import com.kc.intern.project.verticle.ManagerVerticle
import com.kc.intern.project.verticle.ScreenVerticle
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import model.DevUser
import model.ManagerUser

class MainVerticle : AbstractVerticle (){

  val devs: ArrayList<DevUser> = ArrayList()
  val managers: ArrayList<ManagerUser> = arrayListOf()

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
    val screenVerticle: ScreenVerticle = ScreenVerticle()

    /**Parser to HTML */
    router.get("/screen").handler{ screenVerticle.parserToHtml(it) }

    /** MANAGER ENDPOINTS */
    //GET
    router.get("/login/manager").handler { managerVerticle.returnManagerLogged(managerLogged, it)}
    router.get("/manager/managers").handler{ managerVerticle.handleListManagers(managers, it) }
    //router.get("/manager/parser").handler{ managerVerticle.parserToHtml(managerLogged!!, it) }
    //POST
    router.post("/signUp/manager").handler{ managerLogged = managerVerticle.createManagerUser(managers, it) }
    router.post("/manager/project").handler{ managerVerticle.createProject(managerLogged!!, it) }
    router.post("/manager/dev").handler{ managerVerticle.createDevUser(managerLogged!!, devs, it)}
    //PUT
    router.put("/manager/devs/:devId/credits/:newCredits").handler{ managerVerticle.changeDevCredits(managerLogged!!, it)}


    /** DEV ENDPOINTS */
    //GET
    router.get("/login/dev").handler{ devVerticle.returnDevLogged(devLogged, it) }
    router.get("/dev/devs").handler { devVerticle.handleListDevs(devs, it) }
    //router.get("/dev/parser").handler{ devVerticle.parserToHtml(devLogged!!, it) }
    //POST
    router.post("/signUp/dev").handler { devLogged = devVerticle.createDevUser(devs, it) }
    router.post("/dev/project").handler { devVerticle.createProject(devLogged!!, it)  }
    //DELETE
    router.delete("/dev/projects/:projectId").handler { devVerticle.deleteProject(devLogged!!, it) }

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }
}
