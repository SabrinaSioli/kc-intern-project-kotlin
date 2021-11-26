package service

import contIdDev
import contIdProject
import model.DevUser
import model.ManagerUser
import model.User
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

        println("Create - Manager")
        print("Name: ")
        name = readLine()!!.toString()


        while (isNotUniq) { //Repeat while the email is not unique
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

        if (op == 1) { //The user gives up on trying find an unique email
            print("Password: ")

            try {
                password = readLine()!!.toInt()
                user.id = contIdDev++
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


    fun UserServiceMenu(): Int {
        println("""
            1 - Add project
            2 - Delete project
        """.trimIndent())
        print("Option: ")
        var op: Int = readLine()!!.toInt()
        while (op != 1 && op != 2) {
            println("Type a valid option")
            op = readLine()!!.toInt()
        }
        return op
    }
}