const router = require('express').Router();
const bookManager = require('../managers/bookManager')
const {getErrorMessage} = require('../utils/errorHelpers')
const {get} = require("mongoose");

router.get('/catalog', async (req, res) => {
    const books = await bookManager.getAllBooks()
    res.render('books/catalog', {title: "Catalog Page", books})
})

router.get('/create', async (req, res) => {
    res.render('books/create', {title: "Create Page"})
})

router.post('/create', async (req, res) => {
    const book = req.body
    try {
        book.owner = req.user._id
        await bookManager.createBook(book)
        res.redirect('/books/catalog')
    } catch (err) {
        res.render('books/create', {title: "Create Page", book, error: getErrorMessage(err)})
    }
})

router.get('/details/:id', async (req, res) => {
    const bookId = req.params.id
    let isAuthor = false;
    let canRead = true;
    const book = await bookManager.getBook(bookId);
    if (req.user) {
        isAuthor = req.user._id == book.owner
        for (const user of book.wishingList) {
            if (user._id == req.user._id) {
                canRead = false;
                break;
            }
        }
    }
    res.render('books/details', {title: "Details Page", book, canRead, isAuthor})
})


router.get('/edit/:id', async (req, res) => {
    const bookId = req.params.id
    const book = await bookManager.getBook(bookId);
    res.render('books/edit', {title: "Edit Page", book})
})

router.post('/edit/:id', async (req, res) => {
    const bookData = req.body;
    const bookId = req.params.id
    try {
        await bookManager.editBook(bookId, bookData)
        res.redirect(`/books/details/${bookId}`)
    } catch (err) {
        res.render(`books/edit`, {title: "Edit Page", book: bookData, error: getErrorMessage(err)})
    }
})

router.get('/delete/:id', async (req, res) => {
    const bookId = req.params.id
    await bookManager.deleteBook(bookId)
    res.redirect('/books/catalog')
})

router.get('/read/:id', async (req, res) => {
    const bookId = req.params.id
    const userId = req.user._id
    await bookManager.wishBook(bookId, userId)
    res.redirect(`/books/details/${bookId}`)
})

module.exports = router;