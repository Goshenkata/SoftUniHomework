import {html} from "../lib.js";
import {getUserData} from "../util.js";
import {deleteAlbum, getAlbumDetails, getAlbumLikes, likeAlbum, userLike} from "../api/data.js";

const detailsTemplate = (album, isOwner, onDelete, isLoggedIn, hasLiked, like) => html`
    <!-- Details page -->
    <section id="details">
        <div id="details-wrapper">
            <p id="details-title">Album Details</p>
            <div id="img-wrapper">
                <img src="${album.imageUrl}" alt="example1" />
            </div>
            <div id="info-wrapper">
                <p><strong>Band:</strong><span id="details-singer">${album.singer}</span></p>
                <p>
                    <strong>Album name:</strong><span id="details-album">${album.album}</span>
                </p>
                <p><strong>Release date:</strong><span id="details-release">${album.release}</span></p>
                <p><strong>Label:</strong><span id="details-label">${album.label}</span></p>
                <p><strong>Sales:</strong><span id="details-sales">${album.sales}</span></p>
            </div>
            <div id="likes">Likes: <span id="likes-count">${album.likes}</span></div>

            <!--Edit and Delete are only for creator-->
            <div id="action-buttons">
                ${isLoggedIn && !isOwner && !hasLiked ? html`<a href="" id="like-btn" @click="${like}">Like</a>` : null}
                ${isOwner ? html`
                <a href="/edit/${album._id}" id="edit-btn">Edit</a>
                <a @click=${onDelete} id="delete-btn">Delete</a>` : null}
            </div>
        </div>
    </section>
`

export async function detailsPage(ctx) {
    const userData = getUserData();
    const albumId = ctx.params.id
    let album = await getAlbumDetails(albumId);
    album.likes = await getAlbumLikes(albumId);
    const isOwner = userData && album._ownerId == userData._id
    const hasLiked = userData ? await userLike(album._id, userData._id) : false
    ctx.render(detailsTemplate(album, isOwner, onDelete, !!userData, !!hasLiked, like))

    async function onDelete() {
        if (confirm('Are you sure you want to delete this item?')) {
            await deleteAlbum(album._id)
            ctx.page.redirect('/dashboard')
        }
    }
    async function like() {
        await likeAlbum(albumId)
    }
}