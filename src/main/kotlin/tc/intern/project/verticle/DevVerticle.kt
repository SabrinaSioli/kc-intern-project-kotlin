package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.DevUser
import model.Project
import service.DevUserService
import tc.intern.project.handler.ResponseHandler

class DevVerticle {

    val devService = DevUserService()

    fun returnDevLogged(devLogged: DevUser?, routingContext: RoutingContext ){
        if (devLogged == null) {
            routingContext
                .response()
                .setStatusCode(401)
                .end(JsonObject
                    .mapFrom(ResponseHandler(401, "Dev not logged. You must log in.", null))
                    .encodePrettily())
        }

        routingContext
            .response()
            .setStatusCode(200)
            .putHeader("content-type", "application/json")
            .end(JsonObject
                .mapFrom(ResponseHandler(200, "Successful search", JsonObject.mapFrom(devLogged)))
                .encodePrettily()
            )
    }

    fun handleListDevs(devs: JsonArray, routingContext: RoutingContext) {
        when(devs.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json").end(Json.encodePrettily(devs))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(devs))
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



/*
fun sendError(statusCode: Int, response: HttpServerResponse) {
    response.setStatusCode(statusCode).end()
}
*/
}