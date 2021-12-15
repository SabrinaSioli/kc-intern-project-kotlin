package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.DevUser
import model.Project
import service.DevUserService
import tc.intern.project.exceptions.ObjectNotFoundException
import tc.intern.project.handler.ExceptionsResponseHandler
import tc.intern.project.handler.ResponseHandler

class DevVerticle {

    private val devService = DevUserService()
    private val exceptionsResponseHandler = ExceptionsResponseHandler()

    fun returnDevLogged(devLogged: DevUser?, routingContext: RoutingContext ){
        if (devLogged == null) {
            routingContext.response().setStatusCode(401)
                .end(JsonObject.mapFrom(ResponseHandler(401, "Dev not logged", null)).encodePrettily())
        } else {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(JsonObject.mapFrom(ResponseHandler(200, "Successful search", JsonObject.mapFrom(devLogged))).encodePrettily())
        }
    }

    fun handleListDevs(devs: JsonArray, routingContext: RoutingContext) {
        when(devs.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(204, "Not found", null)))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", devs)))
        }

    }

    fun createDevUser(devs: JsonArray, routingContext: RoutingContext): DevUser? {
        var devLogged: DevUser? = DevUser()

        devLogged = devService.createUser(devs, routingContext.bodyAsJson)

        if (devLogged != null) {
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your account was created", JsonObject.mapFrom(devLogged))))

        } else {
            routingContext.response().setStatusCode(401)
                .end(Json.encodePrettily(ResponseHandler(401, "Your account was not created", JsonObject.mapFrom(devLogged))))
        }

        return devLogged
    }

    fun createProject(devLogged: DevUser, routingContext: RoutingContext){

        val project: Project?  = devService.createProject(devLogged, routingContext.bodyAsJson)

        if (project != null) {

            routingContext.response().putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your project was created!", JsonObject.mapFrom(project))))

        } else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your project was not created!", JsonObject.mapFrom(project))))
        }

    }

    fun deleteProject(devLogged: DevUser, routingContext: RoutingContext){

        val projectId: Int = routingContext.request().getParam("projectId").toInt()

        try {
            devService.deleteProject(devLogged, projectId)
            routingContext.response().putHeader("content-type", "application/json").setStatusCode(204).end(Json.encodePrettily(ResponseHandler(204, "The project was deleted", null)))
        } catch (e: ObjectNotFoundException) {
            exceptionsResponseHandler.objectNotFoundExceptionResponse(routingContext, e)
        }
    }

}