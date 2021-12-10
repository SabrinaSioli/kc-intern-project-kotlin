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
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", JsonObject.mapFrom(managers))))
        }

    }

}