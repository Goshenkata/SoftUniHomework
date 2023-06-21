const router = require('express').Router();
const animalManager = require('../managers/animalManager')

router.get('/', async (req, res) => {
    let animals = await animalManager.getAll()
    animals = animals.slice(-3).reverse()
    res.render('home', {title: "Home page", animals})
})
router.get('/404', (req,res) => {
    res.render('404', {title:"404 Page"})
})

module.exports = router