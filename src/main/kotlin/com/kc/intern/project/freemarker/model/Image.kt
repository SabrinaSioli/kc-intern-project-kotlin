package com.kc.intern.project.freemarker.model

class Image {
    var name: String = ""
    constructor(name: String){this.name = name}
    constructor(){}

    override fun toString(): String {
        return "Image(name='$name')"
    }

}