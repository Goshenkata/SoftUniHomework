import {page, render} from "./lib.js";
import {getUserData} from "./util.js";
import {homePage} from "./view/home.js";
import {loginPage} from "./view/login.js";
import {registerPage} from "./view/register.js";
import {logout} from "./api/data.js";
import {dashboardPage} from "./view/dashboard.js";
import {createPage} from "./view/create.js";
import {detailsPage} from "./view/details.js";
import {editPage} from "./view/edit.js";


const root = document.querySelector('main')

page(decorateContext)
page('/', homePage)
page('/login', loginPage)
page('/register', registerPage)
page('/logout', logoutUser)
page('/dashboard', dashboardPage)
page('/create', createPage)
page('/details/:id', detailsPage)
page('/edit/:id', editPage)
updateUserNav()
page.start()

function decorateContext(ctx, next) {
    ctx.render = (cont) => render(cont, root);
    ctx.updateUserNav = updateUserNav
    next()
}

function updateUserNav() {
    const userData = getUserData()
    if (userData) {
        document.querySelector('.user').style.display = 'block'
        document.querySelector('.guest').style.display = 'none'
    } else {
        document.querySelector('.guest').style.display = 'block'
        document.querySelector('.user').style.display = 'none'
    }
}

function logoutUser() {
    logout()
    updateUserNav()
    page.redirect('/')
}
