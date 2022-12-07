import {getUserData} from "./util.js";
import {page, render} from "./lib.js";
import {loginPage} from "./view/loginPage.js";
import {logout} from "./api/api.js";
import {homePage} from "./view/homePage.js";
import {registerPage} from "./view/register.js";
import {dashboardPage} from "./view/dashboard.js";
import {createPage} from "./view/add.js";
import {detailsPage} from "./view/details.js";
import {editPage} from "./view/edit.js";

const root = document.querySelector('main')

page(decorateContext)
page('/', homePage)
page('/login', loginPage)
page('/register', registerPage)
page('/logout', logoutUser)
page('/dashboard', dashboardPage)
page('/add', createPage)
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
