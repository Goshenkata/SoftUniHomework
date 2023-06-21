const fs = require("fs");
const uniqid = require('uniqid');
const getAll = (search, from, to) => {
    let cubes = JSON.parse(fs.readFileSync('config/database.json', 'utf-8'))
    if (search) {
        cubes = cubes.filter(c => c.name.toLowerCase().includes(search.toLowerCase()));
    }
    if (from) {
        cubes = cubes.filter(c => c.difficultyLevel >= from);
    }
    if (to) {
        cubes = cubes.filter(c => c.difficultyLevel <= to);
    }
    return cubes
}
const create = (data) => {
    const cube = {
        id: uniqid(),
        ...data
    }
    const cubes = getAll()
    cubes.push(cube);
    fs.writeFileSync('config/database.json', JSON.stringify(cubes))
}
const getById = (id) => {
    return getAll().find(x => x.id == id);
}

module.exports.create = create;
module.exports.getAll = getAll;
module.exports.getById = getById;
