const router = require('express').Router();
const animalManager = require('../managers/animalManager')
const {getErrorMessage} = require("../utils/errorHelpers");

function isAuthenticated(req, res) {
    if (!req.user) {
        res.redirect('/404')
        return true
    }
    return false
}

router.get('/dashboard', async (req, res) => {
    const animals = await animalManager.getAll()
    res.render('posts/dashboard', {title: "Dashboard Page", animals})
})
router.get('/create', async (req, res) => {

    if (isAuthenticated(req,res)) {
        return
    }
    res.render('posts/create', {title: "Create Page"})
})

router.post('/create', async (req, res) => {
    const data = req.body
    data.owner = req.user._id
    try {
        await animalManager.create(data)
        res.redirect('/posts/dashboard')
    } catch (err) {
        res.render('posts/create', {title: "Create Page", data, error: getErrorMessage(err)})
    }
})

router.get('/details/:id', async (req, res) => {
    try {
        const animalId = req.params.id
        let isOwner = false;
        let canDonate = true;
        const animal = await animalManager.getAnimal(animalId)
        if (req.user) {
            isOwner = animal.owner == req.user._id;
            for (const user of animal.donations) {
                if (user._id == req.user._id) {
                    canDonate = false;
                    break;
                }
            }
        }
        res.render('posts/details', {title: "Details Page", animal, isOwner, canDonate})
    } catch (err) {
        res.redirect('/404')
    }

})

router.get('/edit/:id', async (req,res) => {
    if (isAuthenticated(req,res)) {
        return
    }
    const animalId = req.params.id;
    const animal = await animalManager.getAnimal(animalId)
    if(animal.owner != req.user._id) {
        res.redirect('/404')
        return
    }
    res.render('posts/edit', {title: "Edit Page", animal})
})

router.post('/edit/:id', async (req,res) => {
    const animalId = req.params.id;
    const animalData = req.body
    try {
        const animal = await animalManager.getAnimal(animalId)
        if(animal.owner != req.user._id) {
            res.redirect('/404')
            return
        }
        await animalManager.editAnimal(animalId, animalData)
        res.redirect(`/posts/details/${animalId}`)
    } catch (err) {
        res.render('posts/edit', {title: "Edit Page", animal: animalData, error: getErrorMessage(err)})
    }
})

router.get('/delete/:id', async (req,res) => {
    const animalId = req.params.id;
    if (isAuthenticated(req,res)) {
        return
    }
    const animal = await animalManager.getAnimal(animalId)
    if(animal.owner != req.user._id) {
        res.redirect('/404')
        return
    }
    await animalManager.deleteAnimal(animalId)
    res.redirect('/posts/dashboard')
})

router.get('/donate/:id', async (req,res) => {
    const animalId = req.params.id;
    if (isAuthenticated(req,res)) {
        return
    }
    const animal = await animalManager.getAnimal(animalId)
    if(animal.owner == req.user._id) {
        res.redirect('/404')
        return
    }
    const userId = req.user._id
    await animalManager.donate(userId, animalId)
    res.redirect(`/posts/details/${animalId}`)
})

router.get('/search', async (req,res) => {
    const animals = await animalManager.searchAnimals()
    res.render('posts/search', {title: "Search Page", animals})
})

router.post('/search', async (req, res) => {
    const {location} = req.body
    const animals = await animalManager.searchAnimals(location)
    res.render('posts/search', {title: "Search Page", animals, location})
})


module.exports = router