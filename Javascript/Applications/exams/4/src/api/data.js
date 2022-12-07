import * as api from './api.js'

export async function editOffer(data, id) {
    api.put(`/data/albums/${id}`, data)
}


export async function deleteAlbum(id) {
    api.del(`/data/albums/${id}`)
}


export async function getAlbumDetails(id) {
    return api.get(`/data/albums/${id}`)
}


export async function createAlbum(data) {
    api.post('/data/albums', data)
}


export async function getAlbums() {
    return api.get('/data/albums?sortBy=_createdOn%20desc')
}
export async function userLike(albumId, userId) {
    return api.get(`/data/likes?where=albumId%3D%22${albumId}%22%20and%20_ownerId%3D%22${userId}%22&count`)
}
export async function getAlbumLikes(albumId) {
    return api.get(`/data/likes?where=albumId%3D%22${albumId}%22&distinct=_ownerId&count`)
}
export async function likeAlbum(albumId) {
    api.post('/data/likes', {albumId})
}


export const login = api.login
export const register = api.register
export const logout = api.logout
