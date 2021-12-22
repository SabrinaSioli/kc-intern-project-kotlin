package com.kc.intern.project.handler

class ResponseHandler {

    var status_code: Int = 0
    var message: String? = ""
    var data: Any? = Any()

    constructor(status_code: Int, message: String?, data: Any?) {
        this.status_code = status_code
        this.message = message
        this.data = data
    }
}