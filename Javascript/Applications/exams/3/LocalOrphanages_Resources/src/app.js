import {getUserData} from "./util.js";
import {page, render} from "./lib.js";
import {loginPage} from "./view/loginPage.js";
import {logout} from "./api/api.js";
const root = document.getElementById('main-content')

page(decorateContext)
page('/login', loginPage)
page('/logout', logoutUser)
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
        document.querySelector('#user').style.display = 'block'
        document.querySelector('#guest').style.display = 'none'
    } else {
        document.querySelector('#guest').style.display = 'block'
        document.querySelector('#user').style.display = 'none'
    }
}

function logoutUser() {
    logout()
    updateUserNav()
    page.redirect('/')
}
