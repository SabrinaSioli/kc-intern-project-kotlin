package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.get
import model.DevUser
import tc.intern.project.handler.ResponseHandler
import model.ManagerUser;
import model.Project
import service.ManagerUserService
import tc.intern.project.handler.ExceptionsResponseHandler
import java.lang.IllegalArgumentException

class ManagerVerticle {

    private val managerService = ManagerUserService()
    private val exceptionsResponseHandle = ExceptionsResponseHandler()

    fun returnManagerLogged(managerLogged: ManagerUser?, routingContext: RoutingContext ){
        if (managerLogged == null) {
            routingContext.response().setStatusCode(401)
                .end(Json.encodePrettily(ResponseHandler(401, "Manager not logged.", null)))
        } else {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", JsonObject.mapFrom(managerLogged))))
        }

    }

    fun handleListManagers(managers: ArrayList<ManagerUser>, routingContext: RoutingContext) {
        when(managers.isEmpty()) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(204, "No managers saved", null)))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", managerService.returnJson(managers))))
        }

    }

    fun createManagerUser(managers: ArrayList<ManagerUser>, routingContext: RoutingContext): ManagerUser? {

        try {
            var managerLogged: ManagerUser = managerService.createUser(managers, routingContext.bodyAsJson)

            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your account was created", JsonObject.mapFrom(managerService.returnJson(managerLogged)))))

            return managerLogged
        } catch (e: IllegalArgumentException) {
            return null
            exceptionsResponseHandle.illegalArgumentException(routingContext, e)
        }
    }

    fun createProject(managerLogged: ManagerUser, routingContext: RoutingContext){

        val project: Project?  = managerService.createProject(managerLogged, routingContext.bodyAsJson)

        if (project != null) {

            routingContext.response().putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your project was created!", JsonObject.mapFrom(project))))

        } else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your project was not created!", JsonObject.mapFrom(project))))
        }

    }

    fun createDevUser(managerLogged: ManagerUser, devs: ArrayList<DevUser>, routingContext: RoutingContext) {
        try {
            var dev: DevUser? = DevUser()

            dev = managerService.createDevUser(managerLogged, devs, routingContext.bodyAsJson)

            if (dev != null) {
                routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(ResponseHandler(201, "Successful creation", JsonObject.mapFrom(dev))))

            } else {
                routingContext.response().setStatusCode(401)
                    .end(Json.encodePrettily(ResponseHandler(401, "It was not possible to create ", JsonObject.mapFrom(dev))))
            }
        } catch (e: IllegalArgumentException) {
            exceptionsResponseHandle.illegalArgumentException(routingContext, e)
        }
    }

    fun changeDevCredits(managerLogged: ManagerUser, managers: JsonArray, devs: JsonArray, routingContext: RoutingContext) {
        var devId : Int = routingContext.request().getParam("devId").toInt()
        var newCredits: String = routingContext.request().getParam("newCredits")
        val devFound = managerService.changeDevCredits(managerLogged, managers, devs, devId, newCredits)
        if (devFound) {
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful change", JsonObject.mapFrom(managerLogged))))
        } else {
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(400, "bad request", JsonObject.mapFrom(managerLogged))))
        }

    }
}