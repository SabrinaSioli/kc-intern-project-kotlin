package tc.intern.project.verticle

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.json
import model.DevUser
import tc.intern.project.handler.responseHandler

class DevVerticle {

    fun returnDevUser(devLogged: DevUser?, routingContext: RoutingContext ){
        if (devLogged == null) {
            routingContext
                .response()
                .setStatusCode(401)
                .end(JsonObject
                    .mapFrom(responseHandler(401, "Dev not logged. You must log in.", null))
                    .encodePrettily())
        }

        routingContext
            .response()
            .setStatusCode(200)
            .putHeader("content-type", "application/json")
            .end(JsonObject
                .mapFrom(responseHandler(200, "Successful search", JsonObject.mapFrom(devLogged)))
                .encodePrettily()
            )
    }

    fun handleListDevs(devs: MutableMap<String, JsonObject>, routingContext: RoutingContext) {
        val arr = JsonObject.mapFrom(devs)
        when(arr.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json").end(Json.encodePrettily(arr))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(arr))
        }

    }

    fun sendError(statusCode: Int, response: HttpServerResponse) {
        response.setStatusCode(statusCode).end()
    }


}