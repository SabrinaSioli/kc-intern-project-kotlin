package com.kc.intern.project.verticle

import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.DevUser
import model.Project
import service.DevUserService
import com.kc.intern.project.exceptions.ObjectNotFoundException
import com.kc.intern.project.handler.ExceptionsResponseHandler
import com.kc.intern.project.handler.ResponseHandler

class DevVerticle {

    private val devService = DevUserService()
    private val exceptionsResponseHandler = ExceptionsResponseHandler()

    fun returnDevLogged(devLogged: DevUser?, routingContext: RoutingContext ){
        if (devLogged == null) {
            routingContext.response().setStatusCode(401)
                .end(JsonObject.mapFrom(ResponseHandler(401, "Dev not logged", null)).encodePrettily())
        } else {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(JsonObject.mapFrom(ResponseHandler(200, "Successful search", devService.returnJson(devLogged))).encodePrettily())
        }
    }

    fun handleListDevs(devs: ArrayList<DevUser>, routingContext: RoutingContext) {
        when(devs.isEmpty()) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(204, "Not found", null)))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", devService.returnJson(devs))))
        }

    }

    fun createDevUser(devs: ArrayList<DevUser>, routingContext: RoutingContext): DevUser? {
            var devLogged: DevUser = DevUser()

            devLogged = devService.createUser(devs, routingContext.bodyAsJson)

            if (devLogged != null) {
                routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(ResponseHandler(201, "Your account was created", devService.returnJson(devLogged))))

            } else {
                routingContext.response().setStatusCode(401)
                    .end(Json.encodePrettily(ResponseHandler(401, "Your account was not created", null)))
            }

            return devLogged
    }

    fun createProject(devLogged: DevUser, routingContext: RoutingContext){

        val project: Project?  = devService.createProject(devLogged, routingContext.bodyAsJson)

        if (project != null) {
            routingContext.response().putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your project was created!", JsonObject.mapFrom(project))))

        } else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your project was not created!", null)))
        }
    }

    fun deleteProject(devLogged: DevUser, req: RoutingContext){
        try {
            val projectId: Int = req.request().getParam("projectId").toInt()
            devService.deleteProject(devLogged, projectId)
            req.response().putHeader("content-type", "application/json").setStatusCode(200).end(Json.encodePrettily(
                ResponseHandler(204, "The project was deleted", null)
            ))
        } catch (e: ObjectNotFoundException) {
            exceptionsResponseHandler.objectNotFoundExceptionResponse(req, e)
        }
    }
/*
    fun parserToHtml(dev: DevUser, routingContext: RoutingContext) {
        val value: JsonObject = devService.returnJson(dev)
        val parsed: String = devService.parserToHtml(value)
        if (parsed != "") {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful parse", parsed)))
        } else {
            routingContext.response().setStatusCode(400).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful parse", parsed)))
        }
    }
*/
}