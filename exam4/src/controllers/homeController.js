const router = require('express').Router();

router.get('/', (req, res) => {
    res.render('home', {title: "Home Page - Crypto Web"})
})
router.get('/404', (req,res) => {
    res.render('404', {title: "404 Page"})
})

module.exports = router