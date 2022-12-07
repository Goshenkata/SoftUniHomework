import {html} from "../lib.js";
import {editOffer, getAlbumDetails} from "../api/data.js";

const editTemplate = (onSubmit) => html`
    <section id="edit">
        <div class="form">
            <h2>Edit Album</h2>
            <form class="edit-form" @submit="${onSubmit}">
                <input type="text" name="singer" id="album-singer" placeholder="Singer/Band"/>
                <input type="text" name="album" id="album-album" placeholder="Album"/>
                <input type="text" name="imageUrl" id="album-img" placeholder="Image url"/>
                <input type="text" name="release" id="album-release" placeholder="Release date"/>
                <input type="text" name="label" id="album-label" placeholder="Label"/>
                <input type="text" name="sales" id="album-sales" placeholder="Sales"/>

                <button type="submit">post</button>
            </form>
        </div>
    </section>
`

export async function editPage(ctx) {
    ctx.render(editTemplate(onSubmit))
    const id = ctx.params.id
    const album = await getAlbumDetails(id)
    let formData = new FormData(document.querySelector('form'));
    document.getElementById('album-singer').value = album.singer
    document.getElementById('album-album').value = album.album
    document.getElementById('album-img').value = album.imageUrl
    document.getElementById('album-release').value = album.release
    document.getElementById('album-label').value = album.label
    document.getElementById('album-sales').value = album.sales


    async function onSubmit(event) {
        event.preventDefault()
        formData = new FormData(document.querySelector('form'));
        let data = {
            singer: formData.get('singer'),
            album: formData.get('album'),
            imageUrl: formData.get('imageUrl'),
            release: formData.get('release'),
            label: formData.get('label'),
            sales: formData.get('sales')
        }
        for (const key in data) {
            if (Object.hasOwnProperty.call(data, key)) {
                const element = data[key];
                if (!element) {
                    return alert('All fields must be filled')
                }
            }
        }
        editOffer(data, id)
        ctx.page.redirect('/dashboard')
    }
}