package tc.intern.project.handler

import io.vertx.core.json.JsonObject

class ResponseUser {
    var id: Int = -1
    var name: String = ""
    var credits: String = "-1"

    constructor(id: Int, name: String, credits: String) {
        this.id = id
        this.name = name
        this.credits = credits
    }
}