const router = require('express').Router();
const homeController = require('../controllers/homeController');
const cubeController = require('../controllers/cubeController');
// TODO: Require Controllers...

module.exports = (app) => {
    router.use(homeController)
    router.use(cubeController)
    router.get('/about', (req,res) => {
        res.render('about');
    })
    router.get('/404', (req,res) => {
        res.render('404')
    })
    router.get('*', (req,res) => {
        res.redirect('/404')
    })
    app.use(router)
};