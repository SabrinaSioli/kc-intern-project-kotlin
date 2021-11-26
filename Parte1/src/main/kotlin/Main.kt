import model.ManagerUser
import model.DevUser
import service.MenuService
import service.DevUserService
import service.ManagerUserService

// totally securely, it's true this message
var contIdProject : Int = 0
var contIdDev : Int = 0
var contIdManager : Int = 0

//ask about the dev's creation

//Should I put a condition to reset the contIdDev when there are zero developers?

fun main() {
	var managers = ArrayList<ManagerUser>()
	var devs = ArrayList<DevUser>()

	val devService: DevUserService = DevUserService()
	val managerService: ManagerUserService = ManagerUserService()
	val menuService: MenuService = MenuService()

	var managerLogged: ManagerUser? = null
	var devLogged: DevUser? = null

	var dev: DevUser = DevUser()

	//ask about the better way to implements this part
	var opMenu = -1
	var opSubMenu = -1
	//println("      *** KNOW CODE *** \n")

	while(opMenu != 2) { //return to rootMain while the user didn't type 2 (option to exit)
		//Just for test
		println("manager")
		managers.forEach { manager -> manager.seeProfile() }
		println("\nDevelopers")
		devs.forEach { dev -> dev.seeProfile() }

		opMenu = menuService.rootMenu()
		when(opMenu) {
			0 -> { //Log In
				opSubMenu = menuService.logInMenu()
				when(opSubMenu) {
					0 -> { //Manager
						managerLogged = menuService.logInManagerMenu(managers)
						if (managerLogged != null) {
							menuService.managerMenu(managerLogged, devs)
						}
					}
					1 -> { //Dev
						devLogged = menuService.loginDevMenu(devs)
						if (devLogged != null) {
							menuService.devMenu(devLogged, devs)
						}
					}
				}
			}
			1 -> { //Sign Up
				opSubMenu = menuService.signUpMenu()
				when(opSubMenu) {
					0 -> { //Manager
						managerLogged = managerService.createUser(managers)
						if(managerLogged != null) {
							menuService.managerMenu(managerLogged, devs)
						}
					}
					1 -> {
						devLogged = devService.createUser(devs)
						if (devLogged != null) {
							menuService.devMenu(devLogged, devs)
						}
					}
				}
			}
			2 -> System.exit(0)
		}
	}




}





