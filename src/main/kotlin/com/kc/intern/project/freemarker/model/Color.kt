package com.kc.intern.project.freemarker.model

class Color {

    var hex: String = ""

    constructor(){}
    constructor(hex: String){this.hex = hex}

    override fun toString(): String {
        return "com.kc.intern.project.freemarker.model.Color(hex='$hex')"
    }


}