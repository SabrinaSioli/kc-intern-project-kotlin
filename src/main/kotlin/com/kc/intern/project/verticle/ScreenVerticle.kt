package com.kc.intern.project.verticle

import com.kc.intern.project.freemarker.FreemarkerUtils
import com.kc.intern.project.handler.ResponseHandler
import com.kc.intern.project.jackson.ConvertJsonToObject
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext
import java.io.File

class ScreenVerticle {
    private val freemarker = FreemarkerUtils()
    private val convert = ConvertJsonToObject()

    /*
    var inputString = """
           {
    "message": "OK",
    "application": {
        "screens": [
            {
                "type": "Screen",
                "id": 1,
                "name": "buttons",
                "size": {
                    "width": 399,
                    "height": 342
                },
                "backgroundColor": {
                    "hex": "#fafafa"
                },
                "startScreen": true,
                "backgroundImage": {
                    "name": "Grupo_46"
                },
                "components": [
                    {
                        "type": "Button",
                        "id": 10,
                        "name": "Button10",
                        "location": {
                            "x": 48,
                            "y": 102
                        },
                        "size": {
                            "width": 328,
                            "height": 44
                        },
                        "label": "Circle Button",
                        "color": {
                            "hex": "#050675"
                        },
                        "font": {
                            "family": "Family",
                            "size": 14.0
                        }
                    },
                    {
                        "type": "Button",
                        "id": 11,
                        "name": "Button11",
                        "location": {
                            "x": 48,
                            "y": 187
                        },
                        "size": {
                            "width": 328,
                            "height": 44
                        },
                        "label": "Lages lida",
                        "color": {
                            "hex": "#fcb863"
                        },
                        "font": {
                            "family": "Family",
                            "size": 13.0
                        }
                    },
                    {
                        "type": "Button",
                        "id": 12,
                        "name": "Button12",
                        "location": {
                            "x": 44,
                            "y": 15
                        },
                        "size": {
                            "width": 334,
                            "height": 44
                        },
                        "label": "Pe Tela)",
                        "color": {
                            "hex": "#89b1d2"
                        },
                        "font": {
                            "family": "Family",
                            "size": 12.0
                        }
                    },
                    {
                        "type": "Button",
                        "id": 13,
                        "name": "Button13",
                        "location": {
                            "x": 48,
                            "y": 272
                        },
                        "size": {
                            "width": 328,
                            "height": 45
                        },
                        "label": "",
                        "color": {
                            "hex": "#ebedf2"
                        },
                        "font": {
                            "family": "Family",
                            "size": 45.0
                        }
                    }
                ],
                "location": {
                    "x": 0,
                    "y": 0
                }
            }
        ]
    }
}
        """.trimIndent()
    */
    fun parserToHtml(routingContext: RoutingContext) {
        var returnHtml = false
        val inputJson = routingContext.bodyAsJson

        val screen = convert.convertToScreen(inputJson)
        if(screen != null) {
            val stringHtml: String = freemarker.parseTemplate(screen, "screen.ftl")
            File(freemarker.fileOut).writeText(stringHtml)

            if(stringHtml.isNotBlank()){
                returnHtml = true
                routingContext.response().end(stringHtml)
            }
        }

        if(!returnHtml) { routingContext.response().end(Json.encodePrettily(ResponseHandler(404, "Page not found!", null))) }

    }
}