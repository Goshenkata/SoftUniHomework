const router = require('express').Router()
const gameManager = require('../managers/gameManager')
const {getErrorMessage} = require("../utils/errorHelpers");
const {raw} = require("express");

router.get('/create', (req, res) => {
    res.render('games/create')
})

router.post('/create', async (req, res) => {
    const data = req.body
    data.owner = req.user._id
    try {
        await gameManager.postGame(data)
        res.redirect('/games/catalog')
    } catch (err) {
        res.render('games/create', {error: getErrorMessage(err), data})
    }
})

router.get('/catalog', async (req, res) => {
    const games = await gameManager.getAllGames()
    res.render('games/catalog', {games})
})

router.get('/details/:id', async (req, res) => {
    const id = req.params.id;
    let isOwner = false
    let canBuy = true;
    const game = await gameManager.getGame(id)
    if (req.user) {
        isOwner = game.owner == req.user._id;
        for (const user of game.boughtBy) {
            if (user._id == req.user._id) {
                canBuy = false;
                break;
            }
        }
    }
    res.render('games/details', {game, isOwner, canBuy})

})

router.get('/buy/:id', async (req, res) => {
    const gameId = req.params.id
    const userId = req.user._id
    await gameManager.buyGame(gameId, userId);
    res.redirect(`/games/details/${gameId}`)
})

router.get('/edit/:id', async (req, res) => {
    const game = await gameManager.getGame(req.params.id)
    res.render('games/edit', {game})
})

router.post('/edit/:id', async (req, res) => {
    const game = req.body
    const gameId = req.params.id
    try {
        await gameManager.editGame(gameId, game)
        res.redirect(`/games/details/${gameId}`)
    } catch (err) {
        res.render(`games/edit`, {error: getErrorMessage(err), game})
    }
})

router.get('/delete/:id', async (req, res) => {
    const gameId = req.params.id
    await gameManager.deleteGame(gameId)
    res.redirect('/games/catalog')
})

router.get('/search', async (req, res) => {
    const games = await gameManager.searchGames()
    res.render('games/search', {games})
})

router.post('/search', async (req, res) => {
    const {name, platform} = req.body
    const games = await gameManager.searchGames(name, platform)
    res.render('games/search', {games, name, platform})
})


module.exports = router
