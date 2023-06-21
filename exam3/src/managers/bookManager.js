const Book = require('../models/Book')
const ObjectId = require('mongoose').Types.ObjectId

exports.getAllBooks = async () => {
    const books = await Book.find().lean();
    return books
}

exports.createBook = async (book) => {
    const createBook = await Book.create(book);
    return createBook
}

exports.getBook = async (bookId) => {
    const book = await Book.findById(bookId).populate('wishingList.user').lean();
    return book;
}

exports.editBook = async (bookId, bookData) => {
    let book = await Book.findById(bookId)
    for (let dataKey in bookData) {
        book[dataKey] = bookData[dataKey]
    }
    await book.save()

}
exports.deleteBook = async (bookId) => {
    await Book.findByIdAndDelete(bookId)
}
exports.wishBook = async (bookId, userId) => {
    const book = await Book.findById(bookId)
    book.wishingList.push(userId)
    await book.save()
}

exports.getBooksByUser = async (userId) => {
    let books = await Book.find().populate('wishingList.user').lean();
    books = books.filter(book => {
        for (const user of book.wishingList) {
            if (user._id == userId) {
                return true;
            }
        }
        return false;
    })
    return books
}