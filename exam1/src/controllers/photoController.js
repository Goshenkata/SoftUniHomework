const router = require('express').Router();
const photoManager = require('../managers/photoManager')
const {getErrorMessage} = require("../utils/errorHelpers");

router.get('/add', (req, res) => {
    res.render('create');
})

router.post('/add', async (req, res) => {
    const data = req.body
    try {
        data.owner = req.user._id
        await photoManager.create(data)
        res.redirect('catalog')
    } catch (err) {
        res.render('create', {error: getErrorMessage(err), ...data})
    }
})

router.get('/catalog', async (req, res) => {
    const posts = await photoManager.getAll()
    res.render('catalog', {posts});
})

router.get('/:id', async (req, res) => {
    const id = req.params.id
    try {
        const photo = await photoManager.getOne(id)
        let isOwner = false
        if (req.user) {
            isOwner = photo.owner._id == req.user._id
        }
        res.render('details', {photo, isOwner})

    } catch (err) {
        res.render('404', {error: getErrorMessage(err)})
    }
})

router.post('/comment/:id', async (req, res) => {
    const id = req.params.id
    try {
        const data = {
            user: req.user._id,
            comment: req.body.comment
        }
        await photoManager.addComment(id, data)
        res.redirect(`/photos/${id}`)
    } catch (err) {
        res.render('404', {error: getErrorMessage(err)})
    }
})

router.get('/delete/:id', async (req, res ) => {
    try {
        await photoManager.delete(req.params.id)
        res.redirect('/photos/catalog')
    } catch (err) {
        res.render('404', {error: getErrorMessage(err)})
    }
})

router.get('/edit/:id', async (req, res ) => {
    try {
        const photo = await photoManager.getById(req.params.id)
        res.render('edit', {photo} )
    } catch (err) {
        res.render('404', {error: getErrorMessage(err)})
    }
})

router.post('/edit/:id', async (req, res ) => {
    const data = req.body
    const id = req.params.id;
    try {
        await photoManager.editPhoto(id, data)
        res.redirect(`/photos/${id}`)
    } catch (err) {
        res.render('404', {error: getErrorMessage(err)})
    }
})

module.exports = router