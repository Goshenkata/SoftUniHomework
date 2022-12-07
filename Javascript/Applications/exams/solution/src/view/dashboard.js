import {html} from "../lib.js";
import {getAlbums} from "../api/data.js";

const dashboardTemplate = (albums) => html`
    <section id="dashboard">
        <h2>Albums</h2>
        ${albums.length == 0 ?
        html` <h2>There are no albums added yet.</h2>` :
            html`
                <ul class="card-wrapper">
                    ${albums.map(album => albumTemplate(album))}
                </ul>
`
        }
            <!-- Display a li with information about every post (if any)-->

        <!-- Display an h2 if there are no posts -->
    </section>
`

const albumTemplate = (album) => html`
    <li class="card">
        <img src="${album.imageUrl}" alt="travis" />
        <p>
            <strong>Singer/Band: </strong><span class="singer">${album.singer}</span>
        </p>
        <p>
            <strong>Album name: </strong><span class="album">${album.album}</span>
        </p>
        <p><strong>Sales:</strong><span class="sales">${album.sales}</span></p>
        <a class="details-btn" href="/details/${album._id}">Details</a>
    </li>
`
export async function dashboardPage(ctx) {
    const albums = await getAlbums()
    ctx.render(dashboardTemplate(albums))
}