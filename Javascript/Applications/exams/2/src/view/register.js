import {register} from "../api/data.js";
import {html} from "../lib.js";

const registerTemplate = (onSubmit) => html`
    <section id="register">
        <div class="form">
            <h2>Register</h2>
            <form class="login-form" @submit="${onSubmit}">
                <input
                        type="text"
                        name="email"
                        id="register-email"
                        placeholder="email"
                />
                <input
                        type="password"
                        name="password"
                        id="register-password"
                        placeholder="password"
                />
                <input
                        type="password"
                        name="re-password"
                        id="repeat-password"
                        placeholder="repeat password"
                />
                <button type="submit">register</button>
                <p class="message">Already registered? <a href="/login">Login</a></p>
            </form>
        </div>
    </section>
`

export async function registerPage(ctx) {
    ctx.render(registerTemplate(onSubmit))

    async function onSubmit(event) {
        event.preventDefault()
        const formData = new FormData(event.target)
        const email = formData.get('email').trim()
        const password = formData.get('password').trim()
        const rePassword = formData.get('re-password').trim()

        if (email == '' || password == '' || rePassword == '') {
            return alert('All fields are required')
        }
        if (password != rePassword) {
            return alert('Passwords do not match')
        }
        await register(email, password)
        ctx.updateUserNav()
        ctx.page.redirect('/dashboard')
    }
}
