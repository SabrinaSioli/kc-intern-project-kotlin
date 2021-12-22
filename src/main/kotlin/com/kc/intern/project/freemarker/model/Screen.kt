package com.kc.intern.project.freemarker.model

class Screen {

    var id: Int = 0
    var name: String = ""
    var size: Size = Size()
    var backgroundColor: Color? = null
    var startScreen: Boolean = true
    var type: String = "Screen"
    var backgroundImage: Image? = null
    var location: Location? = null;
    var components: Collection<Component>? = null

    constructor(
        id: Int,
        name: String,
        size: Size,
        backgroundColor: Color?,
        startScreen: Boolean,
        type: String,
        backgroundImage: Image?,
        location: Location?,
        components: Collection<Component>?
    ) {
        this.id = id
        this.name = name
        this.size = size
        this.backgroundColor = backgroundColor
        this.startScreen = startScreen
        this.type = type
        this.backgroundImage = backgroundImage
        this.location = location;
        this.components = components
    }

    constructor()


    override fun toString(): String {
        return "Screen(id=$id, name='$name', size=$size, backgroundColor=$backgroundColor, startScreen=$startScreen, type='$type', backgroundImage=$backgroundImage, components=$components)"
    }


}