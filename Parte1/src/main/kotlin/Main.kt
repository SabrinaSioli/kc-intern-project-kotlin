import model.ManagerUser
import model.DevUser
import service.MenuService
import service.DevUserService
import service.ManagerUserService
import kotlin.math.log

// totally securely, it's true this message
var contIdProject : Int = 0
var contIdDev : Int = 0
var contIdManager : Int = 0

//login mangager dont return

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
	var op = -1
	var subOp = -1
	//println("      *** KNOW CODE *** \n")

	while(op != 2) { //return to rootMain while the user didn't type 2

		println("manager")
		managers.forEach { manager -> manager.seeProfile() }
		println("\nDevelopers")
		devs.forEach { dev -> dev.seeProfile() }

		op = menuService.rootMenu()
		when(op) {
			0 -> { //Log In
				subOp = menuService.logInMenu()
				when(subOp) {
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
			1 -> { //Sign In
				subOp = menuService.signUpMenu()
				when(subOp) {
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





