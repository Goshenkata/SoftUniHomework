const router = require('express').Router()
const userManager = require('../managers/userManager')
const {getErrorMessage} = require('../utils/errorHelpers')

router.get('/login', (req, res) => {
    res.render('users/login')
})

router.post('/login', async (req, res) => {
    const {username, password} = req.body
    try {
        const token = await userManager.login(username, password)
        res.cookie('token', token)
        res.redirect('/')
    } catch (err) {
        res.render('users/login', {error: getErrorMessage(err), username})
    }

})

router.post('/register', async (req, res) => {
    const {username, email, password, repeatPassword} = req.body
    try {
        const token = await userManager.register({username, email, password, repeatPassword})
        res.cookie('token', token)
        res.redirect('/')
    } catch (err) {
        res.render('users/register', {error: getErrorMessage(err), username, email})
    }
})

router.get('/register', (req, res) => {
    res.render('users/register')
})

router.get('/logout', (req, res) => {
    res.clearCookie('token')
    res.redirect('/')
})

module.exports = router