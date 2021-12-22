package com.kc.intern.project.verticle

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.DevUser
import com.kc.intern.project.handler.ResponseHandler
import model.ManagerUser;
import model.Project
import service.ManagerUserService
import com.kc.intern.project.handler.ExceptionsResponseHandler
import com.kc.intern.project.model.ResponseUser
import java.lang.IllegalArgumentException

class ManagerVerticle {

    private val managerService = ManagerUserService()
    private val exceptionsResponseHandle = ExceptionsResponseHandler()

    fun returnManagerLogged(managerLogged: ManagerUser?, routingContext: RoutingContext ){
        if (managerLogged == null) {
            routingContext.response().setStatusCode(401)
                .end(Json.encodePrettily(ResponseHandler(401, "Manager not logged.", null)))
        } else {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", managerService.returnJson(managerLogged))))
        }
    }

    fun handleListManagers(managers: ArrayList<ManagerUser>, routingContext: RoutingContext) {
        when(managers.isEmpty()) {
            true -> routingContext.response().setStatusCode(204).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(204, "No managers saved", null)))
            false -> routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(200, "Successful search", managerService.returnJson(managers))))
        }

    }

    fun createManagerUser(managers: ArrayList<ManagerUser>, routingContext: RoutingContext): ManagerUser? {
        var managerLogged: ManagerUser? = managerService.createUser(managers, routingContext.bodyAsJson)
        if (managerLogged != null)
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your account was created", managerService.returnJson(managerLogged))))
        else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your account was not created!", null)))
        }
        return managerLogged
    }

    fun createProject(managerLogged: ManagerUser, routingContext: RoutingContext){

        val project: Project?  = managerService.createProject(managerLogged, routingContext.bodyAsJson)

        if (project != null) {

            routingContext.response().putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "Your project was created!", JsonObject.mapFrom(project))))

        } else {
            routingContext.response().end(Json.encodePrettily(ResponseHandler(401, "Your project was not created!", JsonObject.mapFrom(project))))
        }

    }

    fun createDevUser(managerLogged: ManagerUser, devs: ArrayList<DevUser>, routingContext: RoutingContext) {
        try {
            managerService.createDevUser(managerLogged, devs, routingContext.bodyAsJson)

            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(
                    Json.encodePrettily(
                        ResponseHandler(
                            201,
                            "Successful creation",
                            managerService.returnJson(managerLogged.devs.last())
                        )
                    )
                )
        } catch (e: IllegalArgumentException) {
            exceptionsResponseHandle.illegalArgumentException(routingContext, e)
        }
    }

    fun changeDevCredits(managerLogged: ManagerUser, routingContext: RoutingContext) {
        try{
            var devId : Int = routingContext.request().getParam("devId").toInt()
            var newCredits: String = routingContext.request().getParam("newCredits")

            var devFound = managerService.changeDevCredits(managerLogged, devId, newCredits)

            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful change", managerService.returnJson(devFound))))

        } catch (e: NoSuchElementException) {
            exceptionsResponseHandle.noSuchElementException(routingContext, e)
        }

    }
/*
    fun parserToHtml(manager: ManagerUser, routingContext: RoutingContext) {
        val value: JsonObject = managerService.returnJson(manager)
        val parsed: String = managerService.parserToHtml(value)
        if (parsed != "") {
            routingContext.response().setStatusCode(200).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful parse", parsed)))
        } else {
            routingContext.response().setStatusCode(400).putHeader("content-type", "application/json")
                .end(Json.encodePrettily(ResponseHandler(201, "successful parse", parsed)))
        }
    }
*/
    /*
    fun parserToHtml(managers: ArrayList<ManagerUser>, routingContext: RoutingContext) {
        val value: JsonObject = JsonObject.mapFrom(managers)
        val parsed: String = managerService.parserToHtml(value)
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