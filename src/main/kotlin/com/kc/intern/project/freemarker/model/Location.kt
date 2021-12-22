package com.kc.intern.project.freemarker.model

class Location {

    var x: Int = 0
    var y: Int = 0

    constructor(){}
    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "Location(x=$x, y=$y)"
    }


}