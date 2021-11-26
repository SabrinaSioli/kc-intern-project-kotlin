package service

import contIdDev
import contIdManager
import model.ManagerUser

import model.DevUser
import java.lang.NumberFormatException

class ManagerUserService : AbstractService() {

	fun createUser(managers: ArrayList<ManagerUser>) : ManagerUser? {
		var user = ManagerUser()
		var isNotUniq = true
		var op = 0
		var id: Int
		var name: String = ""
		var email = ""
		var password: Int = 1234 // ask about this


		println("Create - Manager")
		print("Name: ")
		name = readLine()!!.toString()


		while(isNotUniq) { //Repeat while the email is not unique
			print("Email: ")
			email = readLine()!!.toString()

			isNotUniq = managers.any { it -> it.email == email }

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
				user.id = contIdManager++
				user.name = name
				user.email = email
				user.password = password

				managers.add(user)
			} catch (e: NumberFormatException) {
				println("Error! The password must be an integer. :/")
				return null
			}
		}
		return user
	}

	fun createDev(manager : ManagerUser, devs: ArrayList<DevUser>) {
		var CreateDev = 1

		while (CreateDev == 1) {
			println("Create - Developer")
			var id: Int
			var name: String
			var email: String = ""
			var credits: Int

			print("Name: ")
			name = readLine()!!.toString()

			print("Credits: ")
			credits = readLine()!!.toInt()
			credits = credits

			var isNotUniq = true
			var op = 0

			while(isNotUniq) { //Repeat while the email is not unique
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
					op = 2
				}
			}

			if (op == 2) {
				id = contIdDev++

				var dev = DevUser()

				dev.id = id
				dev.name = name
				dev.email = email
				dev.credits = credits
				dev.manager = manager

				manager.devs.add(dev)
				devs.add(dev)

				println("\nDevelop successfully created!")
				dev.seeProfile()

			}

			println("""
				Create another Developer?
				1 - Yes
				2 - No
			""".trimIndent())
			CreateDev = readLine()!!.toInt()
		}
	}

	fun changeMyCredits(manager: ManagerUser) {
		var credit : Int = manager.credits
		println("You have ${manager.credits} credits")
		print("Type the new value: ")

		try{

			do {
				credit = readLine()!!.toInt()
			} while (credit < 0)

		} catch (e : NumberFormatException) {
			println("Error! :/")
		}

		manager.credits = credit
	}

	fun changeDevsCredits(manager: ManagerUser) {
		var continueDelete = true
		while(continueDelete) {
			println("Devs")
			for (dev in manager.devs) {
				println("""
				id: ${dev.id} - name: ${dev.name} - creditos: ${dev.credits}
			""".trimIndent())
			}

			print("Id of the Dev: ")
			var id: Int = readLine()!!.toInt()

			var foundDev = manager.devs.find { it.id == id }

			if (foundDev != null) {
				print("new value of credits: ")
				var credits = readLine()!!.toInt()
				foundDev.credits = credits
				println("Changed with success!")
			} else {
				println("Id not found!")
				println("""
					You want:
					0 - try again
					1 - return
				""".trimIndent())
				var op = readLine()!!.toInt()
				if(op == 1) continueDelete = false
			}
		}
	}

	fun deleteDev(manager: ManagerUser, devs: ArrayList<DevUser>) {
		var notStop = true
		while(notStop) {
			println("Devs")
			if (devs.isEmpty()) println("None")

			for (dev in manager.devs) {
				println("""
				${dev.id} - ${dev.name} - ${dev.credits}
			""".trimIndent())
			}
			println("")
			var isDevId = false
			while(!isDevId) {
				print("Id of the Dev: ")
				var devId: Int = readLine()!!.toInt()
				isDevId = manager.devs.any { dev -> dev.id == devId }
				if (isDevId) {
					manager.devs.removeIf { dev -> dev.id == devId }
					devs.removeIf { dev -> dev.id == devId }
					println("Successfully removed!\n")
					println("""
					Do want delete another developer?
					0 - yes
					1 - No
				""".trimIndent())
					var op = readLine()!!.toInt()
					if (op == 0) {
						isDevId = true
					}
					if (op == 1) {
						isDevId = true
						notStop = false
					}
				} else {
					println("Id not found.\n")
					println("""
					What do you want to do?
					0 - try again
					1 - return
				""".trimIndent())
					var op = readLine()!!.toInt()
					if (op == 1) {
						isDevId = true
						notStop = false
					}
				}
			}
		}
	}
}