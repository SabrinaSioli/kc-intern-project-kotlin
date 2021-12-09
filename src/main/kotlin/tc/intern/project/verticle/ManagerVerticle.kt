package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import tc.intern.project.handler.ResponseHandler
import model.ManagerUser;

class ManagerVerticle {

    fun returnManagerLogged(managerLogged: ManagerUser?, routingContext: RoutingContext ){
        if (managerLogged == null) {
            routingContext
                .response()
                .setStatusCode(401)
                .end(JsonObject
                    .mapFrom(ResponseHandler(401, "Manager not logged. You must log in.", null))
                    .encodePrettily())
        }

        routingContext
            .response()
            .setStatusCode(200)
            .putHeader("content-type", "application/json")
            .end(JsonObject
                .mapFrom(ResponseHandler(200, "Successful search", JsonObject.mapFrom(managerLogged)))
                .encodePrettily()
            )
    }

    fun handleListManagers(devs: JsonArray, routingContext: RoutingContext) {
        when(devs.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json").end(Json.encodePrettily(devs))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(devs))
        }

    }

}