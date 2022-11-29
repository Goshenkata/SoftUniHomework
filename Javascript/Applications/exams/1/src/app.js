import { render, page } from './lib.js';
import {clearUserData, getUserData} from './util.js';
import { homePage } from './view/home.js';
import { loginPage } from './view/login.js';
import { logout } from './api/data.js';
import { registerPage } from './view/register.js'
import { dashboardPage } from './view/dashboard.js'
import { addPage } from './view/add.js'

const root = document.querySelector('main');

page(decorateContext)
page('/', homePage)
page('/login', loginPage)
page('/logout', logoutUser)
page('/register', registerPage)
page('/dashboard', dashboardPage)
page('/add', addPage)
updateUserNav()
page.start()


function decorateContext(ctx, next) {
    ctx.render = (cont) => render(cont, root);
		ctx.updateUserNav = updateUserNav
		next()
}

function updateUserNav(){
	const userData = getUserData()
	if (userData) {
		document.querySelector('#user').style.display = 'block'
		document.querySelector('#anon').style.display = 'none'
	} else {
		document.querySelector('#anon').style.display = 'block'
		document.querySelector('#user').style.display = 'none'
	}
}

function logoutUser() {
	logout()
	updateUserNav()
	page.redirect('/')
}
