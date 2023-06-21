const mongoose = require('mongoose')

const cryptoSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minLength: 2
    },
    image: {
        type: String,
        required: true,
        match: [/^(http:\/\/|https:\/\/)/, 'invalid url']
    },
    price: {
        type: Number,
        required: true,
        min:0
    },
    description: {
        type: String,
        required: true,
        minLength: 10
    },
    paymentMethod: {
        type: String,
        enum: ['crypto-wallet', 'credit-card', 'debit-card', 'paypal'],
        required: true,
    },
    bought: [
        {
            user: {
                type: mongoose.Schema.Types.ObjectId,
                ref: 'User'
            }
        }
    ],
    owner: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    }
})
const Crypto = mongoose.model('Crypto', cryptoSchema)
module.exports = Crypto
