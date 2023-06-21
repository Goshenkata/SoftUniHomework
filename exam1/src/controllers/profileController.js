const router = require('express').Router()
const photoManager = require('../managers/photoManager')
const userManager = require('../managers/userManager')

router.get('/profile', async (req, res) => {
    const userId = req.user._id
    const user = req.user
    const photos = await photoManager.getPhotosByUser(userId)
    const count = photos.length
    res.render('profile', {photos, user, count})
})
module.exports = router
