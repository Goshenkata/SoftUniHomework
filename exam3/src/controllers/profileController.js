const router = require('express').Router();
const bookManager = require('../managers/bookManager')

router.get('/profile', async (req, res) => {
    const books = await bookManager.getBooksByUser(req.user._id)
    const user = req.user;
    res.render('profile', {title: "Profile page", books, user})
})

module.exports = router;
