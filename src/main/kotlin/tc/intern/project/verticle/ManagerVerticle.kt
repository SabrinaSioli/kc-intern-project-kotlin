package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import tc.intern.project.handler.responseHandler
import model.ManagerUser;

class ManagerVerticle {

    fun returnManagerLogged(managerLogged: ManagerUser?, routingContext: RoutingContext ){
        if (managerLogged == null) {
            routingContext
                .response()
                .setStatusCode(401)
                .end(JsonObject
                    .mapFrom(responseHandler(401, "Manager not logged. You must log in.", null))
                    .encodePrettily())
        }

        routingContext
            .response()
            .setStatusCode(200)
            .putHeader("content-type", "application/json")
            .end(JsonObject
                .mapFrom(responseHandler(200, "Successful search", JsonObject.mapFrom(managerLogged)))
                .encodePrettily()
            )
    }

    fun handleListManagers(devs: MutableMap<String, JsonObject>, routingContext: RoutingContext) {
        val arr = JsonObject.mapFrom(devs)
        when(arr.isEmpty) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json").end(Json.encodePrettily(arr))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(arr))
        }

    }

}