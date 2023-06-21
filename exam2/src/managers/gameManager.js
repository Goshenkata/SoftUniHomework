const Game = require('../models/Game')
const {plugin} = require("mongoose");

exports.postGame = async (data) => {
    const game = await Game.create(data)
    return game;
}
exports.getAllGames = async (data) => {
    const games = await Game.find().lean()
    return games;
}

exports.getGame = async (id) => {
    const game = await Game.findById(id).populate('boughtBy.user').lean();
    return game;
}
exports.buyGame = async (gameId, userId) => {
    const game = await Game.findById(gameId)
    game.boughtBy.push(userId)
    game.save()
}

exports.editGame = async (gameId, gameData) => {
    const game = await Game.findById(gameId)
    for (let dataKey in gameData) {
        game[dataKey] = gameData[dataKey]
    }
    await game.save();
}
exports.deleteGame = async (gameId) => {
    await Game.findByIdAndDelete(gameId)
}

exports.searchGames = async (name, platform) => {
    let games = await Game.find().lean()
    if (name) {
        games = games.filter(game => game.name.toLowerCase().includes(name.toLowerCase()));
    }
    if (platform) {
        games = games.filter(game => game.platform == platform)
    }
    return games
}