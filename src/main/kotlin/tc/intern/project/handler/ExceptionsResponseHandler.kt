package tc.intern.project.handler

import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import tc.intern.project.exceptions.ObjectNotFoundException
import java.lang.IllegalArgumentException

class ExceptionsResponseHandler {

    fun objectNotFoundExceptionResponse(routingContext: RoutingContext, e: ObjectNotFoundException) {
        routingContext.response().setStatusCode(403).putHeader("content-type", "application/json")
            .end(JsonObject.mapFrom(ResponseHandler(403, e.message, null)).encodePrettily())
    }

    fun illegalArgumentException(routingContext: RoutingContext, e: IllegalArgumentException) {
        routingContext.response().setStatusCode(400).putHeader("content-type", "application/json")
            .end(JsonObject.mapFrom(ResponseHandler(400, e.message, null)).encodePrettily())
    }

}