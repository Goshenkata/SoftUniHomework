const Crypto = require('../models/Crypto')

exports.getAll = async () => {
    return await Crypto.find().lean()
}
exports.create = async (data) => {
    return await Crypto.create(data)
}
exports.getCoin = async (id) => {
    return await Crypto.findById(id).populate('bought.user').lean()
}

exports.editCoin = async (coinId, coinData) => {
    let coin = await Crypto.findById(coinId)
    for (let dataKey in coinData) {
        coin[dataKey] = coinData[dataKey]
    }
    await coin.save();
}
exports.deleteCoin = async (id) => {
    await Crypto.findByIdAndDelete(id)
}

exports.buyCoin = async (coinId, userId) => {
    const coin = await Crypto.findById(coinId)
    coin.bought.push(userId)
    coin.save()
}
exports.searchCoins = async (name, paymentMethod) => {
    let coins = await Crypto.find().lean()
    if (name) {
        coins = coins.filter(coin => coin.name.toLowerCase().includes(name.toLowerCase()));
    }
    if (paymentMethod) {
        coins = coins.filter(coin => coin.paymentMethod == paymentMethod)
    }
    return coins

}
