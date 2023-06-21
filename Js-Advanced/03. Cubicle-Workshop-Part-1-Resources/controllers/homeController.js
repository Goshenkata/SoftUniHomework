const router = require("express").Router()
const fs = require('fs')
const cubeManager = require('../managers/cubeManager')
router.get('/', (req, res) => {
    const {search, from, to} = req.query
    const cubes = cubeManager.getAll(search, from, to);
    res.render('index', { cubes, search, from, to});
})

module.exports = router