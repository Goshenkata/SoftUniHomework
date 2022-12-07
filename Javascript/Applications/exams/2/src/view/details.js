import {html} from "../lib.js";
import {getUserData} from "../util.js";
import {apply, deleteOffer, getApplications, getOfferDetails, hasApplied} from "../api/data.js";

const detailsTemplate = (offer, isOwner, onDelete, isLoggedIn, onApply, hasApplied) => html`
    <section id="details">
        <div id="details-wrapper">
            <img id="details-img" src="${offer.imageUrl}" alt="example1"/>
            <p id="details-title">${offer.title}</p>
            <p id="details-category">
                Category: <span id="categories">${offer.category}</span>
            </p>
            <p id="details-salary">
                Salary: <span id="salary-number">${offer.salary}</span>
            </p>
            <div id="info-wrapper">
                <div id="details-description">
                    <h4>Description</h4>
                    <span>
                        ${offer.description}
                    </span
                    >
                </div>
                <div id="details-requirements">
                    <h4>Requirements</h4>
                    <span>
                        ${offer.requirements}
                    </span
                    >
                </div>
            </div>
            <p>Applications: <strong id="applications">${offer.applications}</strong></p>

            <!--Edit and Delete are only for creator-->
            <div id="action-buttons">
                ${isOwner ? html`
                    <a href="/edit/${offer._id}" id="edit-btn">Edit</a>
                    <a @click="${onDelete}" id="delete-btn">Delete</a>` : null}

                <!--Bonus - Only for logged-in users ( not authors )-->
                ${isLoggedIn && !isOwner && !hasApplied ? html`
                    <a href="" @click="${onApply}" id="apply-btn">Apply</a> ` : null}
            </div>
        </div>
    </section>
`

export async function detailsPage(ctx) {
    const userData = getUserData();
    const offer = await getOfferDetails(ctx.params.id);
    offer.applications = await getApplications(offer._id)
    const isOwner = userData && offer._ownerId == userData._id
    let applied = await hasApplied(offer._id, userData._id)
    ctx.render(detailsTemplate(offer, isOwner, onDelete, !!userData, onApply, applied))


    async function onDelete() {
        if (confirm('Are you sure you want to delete this item?')) {
            await deleteOffer(offer._id)
            ctx.page.redirect('/dashboard')
        }
    }
    async function onApply() {
        await apply(ctx.params.id)
    }

}