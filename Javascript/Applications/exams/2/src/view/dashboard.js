import {html} from "../lib.js";
import {getOffers} from "../api/data.js";

const dashboardTemplate = (offers) => html`
    <section id="dashboard">
        <h2>Job Offers</h2>
        ${offers.length == 0 ?
                html` <h2>No offers yet.</h2> ` :
                offers.map(offer => offerTemplate(offer))
        }
        <!-- Display an h2 if there are no posts -->
    </section>
`
const offerTemplate = (offer) => html`
    <div class="offer">
        <img src="${offer.imageUrl}" alt="example1" />
        <p>
            <strong>Title: </strong><span class="title">${offer.title}</span>
        </p>
        <p><strong>Salary:</strong><span class="salary">${offer.salary}</span></p>
        <a class="details-btn" href="/details/${offer._id}">Details</a>
    </div>
`
export async function dashboardPage(ctx) {
    const offers = await getOffers()
    ctx.render(dashboardTemplate(offers))
}