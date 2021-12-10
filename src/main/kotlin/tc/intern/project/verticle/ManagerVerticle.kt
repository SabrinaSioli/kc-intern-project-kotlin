package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.DevUser
import tc.intern.project.handler.ResponseHandler
import model.ManagerUser;
import model.Project
import service.ManagerUserService

class ManagerVerticle {

    private val managerService = ManagerUserService()

    fun returnManagerLogged(managerLogged: ManagerUser?, routingContext: RoutingContext ){
        if (managerLogged == null) {
            routingContext.response().setStatusCode(401)
                .end(Json.encodePrettily(ResponseHandler(401, "Manager not logged.", null)))
        } else {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", JsonObject.mapFrom(managerLogged))))
        }

    }

    fun handleListManagers(managers: JsonArray, routingContext: RoutingContext) {
        when(managers.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(204, "No managers saved", null)))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", managers)))
        }

    }

    fun createManagerUser(managers: JsonArray, routingContext: RoutingContext): ManagerUser? {
        var managerLogged: ManagerUser? = ManagerUser()

        managerLogged = managerService.createUser(managers, routingContext.bodyAsJson)

        if (managerLogged != null) {
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your account was created", JsonObject.mapFrom(managerLogged))))

        } else {
            routingContext.response().setStatusCode(401)
                .end(Json.encodePrettily(ResponseHandler(401, "Your account was not created", JsonObject.mapFrom(managerLogged))))
        }

        return managerLogged
    }

    fun createProject(managerLogged: ManagerUser, routingContext: RoutingContext){

        val project: Project?  = managerService.createProject(managerLogged, routingContext.bodyAsJson)

        if (project != null) {
            managerLogged.projects.add(project)

            routingContext.response().putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your project was created!", JsonObject.mapFrom(project))))

        } else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your project was not created!", JsonObject.mapFrom(project))))
        }

    }

}