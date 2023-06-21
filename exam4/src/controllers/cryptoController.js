const router = require('express').Router();
const cryptoManager = require('../managers/cryptoManager')
const {getErrorMessage} = require('../utils/errorHelpers')
const crypto = require("crypto");

router.get('/catalog', async (req, res) => {
    const crypto = await cryptoManager.getAll()
    res.render('crypto/catalog', {title: "Trade Catalog", crypto})
})

router.get('/create', (req, res) => {
    res.render('crypto/create', {title: "Create Page"})
})

router.post('/create', async (req, res) => {
    const data = req.body
    data.owner = req.user._id
    try {
        await cryptoManager.create(data);
        res.redirect('/crypto/catalog')
    } catch (err) {
        res.render('crypto/create', {title: "Create Page", data, error: getErrorMessage(err)})
    }
})

router.get('/details/:id', async (req, res) => {
    const cointId = req.params.id
    let isOwner = false;
    let canBuy = true;
    try {
        const coin = await cryptoManager.getCoin(cointId)
        if (req.user) {
            const userId = req.user._id;
            isOwner = userId == coin.owner
            for (const user of coin.bought) {
                if (user._id == userId) {
                    canBuy = false;
                    break;
                }
            }
        }
        res.render('crypto/details', {coin, isOwner, canBuy})
    } catch (err) {
        res.redirect('/404')
    }
})

router.get('/edit/:id', async (req, res) => {
    const coinId = req.params.id
    try {
        const coin = await cryptoManager.getCoin(coinId)
        res.render('crypto/edit', {coin, title:"Edit Page"})
    } catch (err) {
        res.redirect('/404')
    }
})

router.post('/edit/:id', async (req, res) => {
    const coinData = req.body
    const coinID = req.params.id
    try {
        await cryptoManager.editCoin(coinID, coinData)
        res.redirect(`/crypto/details/${coinID}`)
    } catch (err) {
        res.render('crypto/edit', {title:"Edit Page",error: getErrorMessage(err), coin: coinData})
    }
})

router.get('/delete/:id', async (req, res) => {
    const id = req.params.id
    await cryptoManager.deleteCoin(id);
    res.redirect('/crypto/catalog')
})

router.get('/buy/:id', async (req, res) => {
    const coinId = req.params.id
    const userId = req.user._id
    await cryptoManager.buyCoin(coinId, userId)
    res.redirect(`/crypto/details/${coinId}`)
})

router.get('/search', async (req, res) => {
    const coins = await cryptoManager.searchCoins()
    res.render('crypto/search', {coins, title:"Search"})
})

router.post('/search', async (req, res) => {
    const {name, paymentMethod} = req.body
    const coins = await cryptoManager.searchCoins(name, paymentMethod)
    res.render('crypto/search', {coins, name, paymentMethod, title: "Search"})
})

module.exports = router
