package service

import model.DevUser
import model.ManagerUser

class MenuService {

    fun rootMenu(): Int {
        var op = -1
        println("""
			 	  *** KNOW CODE *** 
			____________________________
			0 - Log In
			1 - Sign Up
			2 - Exit() 
	""".trimIndent())

        try {
            op = readLine()!!.toInt();

            while (op != 0 && op != 1 && op != 2) op = readLine()!!.toInt();

        } catch (ex : NumberFormatException) {
            println("Error: you didn't enter an integer")
        }
        return op
    }

    fun signUpMenu() : Int {
        println("""
		 		     SIGN UP
	   ____________________________
	   0 - Manager
	   1 - Developer
	   2 - Exit() 
	""".trimIndent())

        var op: Int = -1

        try {
            op = readLine()!!.toInt();

            while (op != 0 && op != 1 && op != 2) op = readLine()!!.toInt();

        } catch (ex : NumberFormatException) {
            println("Error: you didn't enter an integer")
        }

        return op
    }

    fun logInMenu() : Int {
        println("""
		 		    LOG IN
	   ____________________________
	   0 - Manager
	   1 - Developer
	   2 - Return 
	""".trimIndent())

        var op = -1

        try {
            op = readLine()!!.toInt();

            while (op != 0 && op != 1 && op != 2) op = readLine()!!.toInt();

        } catch (ex : NumberFormatException) {
            println("Error: you didn't enter an integer")
        }

        return op
    }

    fun logInManagerMenu (managers: ArrayList<ManagerUser>) : ManagerUser? {
        var email: String
        var password: Int
        var stop : Int = 0
        while (stop == 0){
            print("""
                       MANAGER
            ____________________________
            Email:
            """.trimIndent())
            email = readLine()!!.toString()

            print("""
			Senha:
		    """.trimIndent())
            password = readLine()!!.toInt()

            val managerFound : ManagerUser? = managers.find { it.email == email && it.password == password }

            if (managerFound != null) {
                stop = 1
                return managerFound
            } else {
                println("\nPassword or email wrong!\n")
                println("""
                    Do you want:
                    0 - Try again
                    1 - Return
                """.trimIndent())
                var op = readLine()!!.toInt()

                if (op == 1) stop = 1
            }
        }
       return null
    }

     fun loginDevMenu(devs: ArrayList<DevUser>) : DevUser?{
         var email: String
         var password: Int

         var stop: Int = 0

         while (stop == 0) {
             println()
             print("""                    
                       DEVELOPER            
             ____________________________ 
             Email: 
             """.trimIndent())

             email = readLine()!!.toString()

             print("""
             Senha: 
             """.trimIndent())
             password = readLine()!!.toInt()

             val devFound: DevUser? = devs.find { it.email == email && it.password == password }

             if (devFound != null) {
                 stop = 1
                 return devFound //return the dev found
             } else {
                 println("Password or email wrong!")
                 println("""                                                                                         
                 Do you want:                                                                                    
                 0 - Try again                                                                                   
                 1 - Return                                                                                      
             """.trimIndent())
                 var op = readLine()!!.toInt()

                 if (op == 1) stop = 1
             }
         }
         return null
     }

   fun devMenu(devUser: DevUser, devs: ArrayList<DevUser>) {
        var devUserService: DevUserService = DevUserService()
        var op: Int = 0;
        var notStop = true

        while (notStop) {
            println()
            println("""
                      MENU
            ____________________________
			0 - See profile
			1 - Create Project
			2 - Delete Project
			3 - Return
		    """.trimIndent())
            op = readLine()!!.toInt()

            while (op != 0 && op != 1 && op != 2 && op != 3) {
                println("Type a valid option")
                op = readLine()!!.toInt()
            }

            when (op) {
                0 -> devUser.seeProfile()
                1 -> devUserService.createProject(devUser)
                2 -> devUserService.deleteProject(devUser)
                3 -> notStop = false
            }
        }

    }

   fun managerMenu(managerUser: ManagerUser, devs: ArrayList<DevUser>) {
        var managerService: ManagerUserService = ManagerUserService()
        var op: Int = -1;
        var notExit = true

        while (notExit) {
            println()
            println("""
                            MENU
                ____________________________
                0 - See profile
                1 - Create Developer
                2 - Create Project
                3 - Change my credits
                4 - Change Developers credits
                5 - Delete Developer
                6 - Delete Project
                7 - Exit
		""".trimIndent())

            try {
                op = readLine()!!.toInt()

                while (op < 0 || op > 8) {
                    print("Type a valid option: ")
                    op = readLine()!!.toInt()
                }

            } catch (ex : NumberFormatException) {
                println("Error! :/")
            }

            when (op) {
                0 -> managerUser.seeProfile()
                1 -> managerService.createDev(managerUser, devs)
                2 -> managerService.createProject(managerUser)
                3 -> managerService.changeMyCredits(managerUser)
                4 -> managerService.changeDevsCredits(managerUser)
                5 -> managerService.deleteDev(managerUser, devs)
                6 -> managerService.deleteProject(managerUser)
                7 -> notExit = false
            }
        }
    }
}
