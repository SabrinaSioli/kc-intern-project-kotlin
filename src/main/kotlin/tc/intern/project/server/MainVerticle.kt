package tc.intern.project.server

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler


class MainVerticle : AbstractVerticle () {

  private val devs: MutableMap<String, JsonObject> = HashMap()

  override fun start() {
    setUpInitialData()
    val router = Router.router(vertx)
    router.route().handler(BodyHandler.create())

    router["/devs"].handler { routingContext: RoutingContext ->
      handleListDevs(
        routingContext
      )
    }
    vertx.createHttpServer().requestHandler(router).listen(8080)
  }

  private fun handleListDevs(routingContext: RoutingContext) {
    val arr = JsonArray()
    //fix this
    devs.forEach { (k: String?, v: JsonObject?) -> arr.add(v) }
    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily())
  }

  private fun setUpInitialData() {
    addDev(JsonObject().put("id", "0").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))
    addDev(JsonObject().put("id", "1").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))
    addDev(JsonObject().put("id", "2").put("name", "Renê").put("manager", "patrick").put("projects", "jsonProjects").put("email", "rene@gmail.com").put("password", 1234))
  }

  private fun addDev(dev: JsonObject) {
    devs[dev.getString("id")] = dev
  }

  /*
  override fun start(startPromise: Promise<Void>) {
    vertx
      .createHttpServer()
      .requestHandler { req ->
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!")
      }
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause());
        }
      }
  }
   */
}
