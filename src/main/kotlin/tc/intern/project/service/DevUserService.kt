package service

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import model.DevUser
import model.Project
import java.lang.NumberFormatException

class DevUserService : AbstractService() {

    fun createUser(devs: ArrayList<DevUser>): DevUser? {
        var user = DevUser()
        var isNotUniq = true
        var op = 0
        var id: Int
        var name: String = ""
        var email = ""
        var password: Int = 1234 // ask about this

        println("Create - Developer")
        print("Name: ")
        name = readLine()!!.toString()

        //Repeat while the email is not unique
        while (isNotUniq) {
            print("Email: ")
            email = readLine()!!.toString()

            isNotUniq = devs.any { it -> it.email == email }

            if (isNotUniq) {
                println("This email is already used!")
                println("""
				Do you want to try another one?
				0 - yes
				1 - No
				""".trimIndent())
                op = readLine()!!.toInt()
                if (op == 1) isNotUniq = false
            } else {
                op = 1
            }
        }

        //The user gives up on trying find an unique email
        if (op == 1) {
            print("Password: ")

            try {
                password = readLine()!!.toInt()

                if (devs.isEmpty()) {
                    id = 0
                } else {
                    id = devs.last().id + 1
                }

                user.id = id
                user.name = name
                user.email = email
                user.password = password

                devs.add(user)

            } catch (e: NumberFormatException) {
                println("Error! The password must be an integer. :/")
                return null
            }
        }
        return user
    }

}