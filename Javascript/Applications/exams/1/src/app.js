import { render, page } from './lib.js';
import {clearUserData, getUserData} from './util.js';
import { homePage } from './view/home.js';
import { loginPage } from './view/login.js';
import { logout } from './api/data.js';
import { registerPage } from './view/register.js'
import { dashboardPage } from './view/dashboard.js'
import { detailsPage } from './view/details.js'
import { editPage } from './view/editPage.js'
import { addPage } from './view/add.js'
import { searchPage } from './view/search.js'

const root = document.querySelector('main');

page(decorateContext)
page('/', homePage)
page('/login', loginPage)
page('/logout', logoutUser)
page('/register', registerPage)
page('/dashboard', dashboardPage)
page('/add', addPage)
page('/details/:id', detailsPage)
page('/edit/:id', editPage)
page('/search', searchPage)
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
