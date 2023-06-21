const router = require('express').Router()
const userManager = require('../managers/userManager')
const {getErrorMessage} = require('../utils/errorHelpers')

router.get('/login', (req, res) => {
    res.render('users/login', {title: "Login Page - Crypto Web"})
})

router.post('/login', async (req, res) => {
    const {email, password} = req.body
    try {
        const token = await userManager.login(email, password)
        res.cookie('token', token)
        res.redirect('/')
    } catch (err) {
        res.render('users/login', {error: getErrorMessage(err), email, title: "Login Page - Crypto Web"})
    }

})

router.post('/register', async (req, res) => {
    const {username, email, password, repeatPassword} = req.body
    try {
        const token = await userManager.register({username, email, password, repeatPassword})
        res.cookie('token', token)
        res.redirect('/')
    } catch (err) {
        res.render('users/register', {error: getErrorMessage(err), username, email, titleL: "Register Page - Crypto Web"})
    }
})

router.get('/register', (req, res) => {
    res.render('users/register', {title: "Register Page - Crypto Web"})
})

router.get('/logout', (req, res) => {
    res.clearCookie('token')
    res.redirect('/')
})

module.exports = router