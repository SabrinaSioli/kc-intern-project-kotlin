import model.ManagerUser
import model.DevUser
import service.MenuService
import service.DevUserService
import service.ManagerUserService

fun main() {
	var managers = ArrayList<ManagerUser>()
	var devs = ArrayList<DevUser>()

	val devService: DevUserService = DevUserService()
	val managerService: ManagerUserService = ManagerUserService()
	val menuService: MenuService = MenuService()

	var managerLogged: ManagerUser? = null
	var devLogged: DevUser? = null

	var dev: DevUser = DevUser()

	var opMenu = -1
	var opSubMenu = -1

	//return to rootMain while the user didn't type 2 (option to exit)
	while(opMenu != 2) {

		opMenu = menuService.rootMenu()
		when(opMenu) {
			//Log In
			0 -> {
				opSubMenu = menuService.logInMenu()
				when(opSubMenu) {
					//Manager
					0 -> {
						managerLogged = menuService.logInManagerMenu(managers)
						if (managerLogged != null) {
							menuService.managerMenu(managerLogged, devs)
						}
					}
					//Dev
					1 -> {
						devLogged = menuService.loginDevMenu(devs)
						if (devLogged != null) {
							menuService.devMenu(devLogged, devs)
						}
					}
				}
			}
			//Sign Up
			1 -> {
				opSubMenu = menuService.signUpMenu()
				when(opSubMenu) {
					//Manager
					0 -> {
						managerLogged = managerService.createUser(managers)
						if(managerLogged != null) {
							menuService.managerMenu(managerLogged, devs)
						}
					}
					//Dev
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





