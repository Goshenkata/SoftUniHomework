import {html} from "../lib.js";
import {login} from "../api/data.js";

const loginTemplate = (onSubmit) => html`
    <section id="login">
        <div class="form">
            <h2>Login</h2>
            <form class="login-form" @submit="${onSubmit}">
                <input type="text" name="email" id="email" placeholder="email" />
                <input
                        type="password"
                        name="password"
                        id="password"
                        placeholder="password"
                />
                <button type="submit">login</button>
                <p class="message">
                    Not registered? <a href="#">Create an account</a>
                </p>
            </form>
        </div>
    </section>
`
export async function loginPage(ctx) {
    ctx.render(loginTemplate(onSubmit))
    async function onSubmit(event) {
        event.preventDefault()
        const formData = new FormData(event.target)
        const email = formData.get('email').trim()
        const password = formData.get('password').trim()
        if (email == '' || password == '') {
            return alert('All fields are required!')
        }
        await login(email, password)
        ctx.updateUserNav()
        ctx.page.redirect('/')
    }
}
