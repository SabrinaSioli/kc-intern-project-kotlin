package com.kc.intern.project.freemarker.model

class Component {

    var id: Int = 0
    var name: String = ""
    var location: Location = Location()
    var size: Size = Size()
    var type: String = ""
    var backgroundColor: Color? = Color("#fff");
    var backgroundImage: Image? = null;
    var label: String? = null;
    var color: Color? = Color("#fff")
    var font: Font? = Font()



    constructor(){}
    constructor(
        id: Int,
        name: String,
        location: Location,
        size: Size,
        type: String,
        backgroundColor: Color?,
        backgroundImage: Image?,
        label: String?,
        color: Color?,
        font: Font?
    ) {
        this.id = id
        this.name = name
        this.location = location
        this.size = size
        this.type = type
        this.backgroundColor = backgroundColor
        this.backgroundImage = backgroundImage
        this.label = label
        this.color = color
        this.font = font
    }


    override fun toString(): String {
        return "com.kc.intern.project.freemarker.model.Component(id=$id, name='$name', location=$location, size=$size, type='$type', backgroundColor=$backgroundColor, backgroundImage=$backgroundImage)"
    }


}