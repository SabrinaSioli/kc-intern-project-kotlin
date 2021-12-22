package com.kc.intern.project.freemarker.model

class Size {

    var width: Int = 1
    var height: Int = 1

    constructor(){}
    constructor(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    override fun toString(): String {
        return "Size(width=$width, height=$height)"
    }

}