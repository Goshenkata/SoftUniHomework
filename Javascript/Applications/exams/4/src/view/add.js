import {html} from "../lib.js";
import {createAlbum} from "../api/data.js";


const createTemplate = (onSubmit) => html`
    <!-- Create Page (Only for logged-in users) -->
    <section id="create">
        <div class="form">
            <h2>Add Album</h2>
            <form class="create-form" @submit="${onSubmit}">
                <input type="text" name="singer" id="album-singer" placeholder="Singer/Band" />
                <input type="text" name="album" id="album-album" placeholder="Album" />
                <input type="text" name="imageUrl" id="album-img" placeholder="Image url" />
                <input type="text" name="release" id="album-release" placeholder="Release date" />
                <input type="text" name="label" id="album-label" placeholder="Label" />
                <input type="text" name="sales" id="album-sales" placeholder="Sales" />

                <button type="submit">post</button>
            </form>
        </div>
    </section>
`

export async function createPage(ctx) {
    ctx.render(createTemplate(onSubmit))

    async function onSubmit(event) {
        event.preventDefault()
        const formData = new FormData(event.target)
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
        createAlbum(data)
        ctx.page.redirect('/dashboard')
    }
}