package com.kc.intern.project.model

import model.Project

class ResponseUser {
    var id: Int = -1
    var name: String = ""
    var credits: String = "-1"
    var projects: ArrayList<Project> = arrayListOf()

    constructor(id: Int, name: String, credits: String, projects: ArrayList<Project>) {
        this.id = id
        this.name = name
        this.credits = credits
        this.projects.addAll(projects)
    }
}