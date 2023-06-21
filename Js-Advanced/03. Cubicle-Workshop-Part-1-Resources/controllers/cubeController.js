const router = require("express").Router()
const fs = require('fs')
const cubeManager = require('../managers/cubeManager')
router.get('/create', (req, res) => {
    res.render('create');
})
router.post('/create', (req, res) => {
    const {
        name,
        description,
        imageUrl,
        difficultyLevel,
    } = req.body;
    cubeManager.create({name, description, imageUrl, difficultyLevel: Number(difficultyLevel)})
    res.redirect('/');
})
router.get('/:cubeId/details', (req, res) => {
    const cubeId = req.params.cubeId;
    const cube = cubeManager.getById(cubeId)
    console.log(cube)
    if (!cube) {
        return res.redirect('/404');
    }
    return res.render('details', {cube})
})

module.exports = router