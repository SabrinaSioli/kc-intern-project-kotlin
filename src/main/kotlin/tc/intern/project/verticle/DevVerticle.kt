package tc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext

class DevVerticle {

    fun handleListDevs(devs: MutableMap<String, JsonObject>, routingContext: RoutingContext) {
        val arr = JsonObject.mapFrom(devs)
        routingContext.response().putHeader("content-type", "application/json").end(Json.encodePrettily(arr))
    }
}