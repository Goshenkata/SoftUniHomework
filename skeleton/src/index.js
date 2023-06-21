const express = require('express')
const path = require('path')
const handlebars = require('express-handlebars')
const mongoose = require('mongoose')
const cookieParser = require('cookie-parser')
const {errorHandler} = require('./middlewares/errorHandlerMiddleware')

const {auth} = require('./middlewares/authMiddleware')
const routes = require('./routes');

const app = express()

//todo change db name
mongoose.connect('mongodb://127.0.0.1:27017/petstagram')
    .then(() => console.log('DB connected successfully'))
    .catch(err => console.log('DB error, ', err.message))


app.engine('hbs', handlebars.engine({
    extname: 'hbs'
}));
app.set('view engine', 'hbs')
app.set('views', 'src/views')


app.use(express.static(path.resolve(__dirname, 'public')))
app.use(express.urlencoded({extended: false}))
app.use(cookieParser())
app.use(auth)
app.use(routes)
app.use(errorHandler)

app.listen(3000, console.log(`listening on port 3000...`))