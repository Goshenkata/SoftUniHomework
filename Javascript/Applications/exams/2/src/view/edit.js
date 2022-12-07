import {html} from "../lib.js";
import {editOffer, getOfferDetails} from "../api/data.js";

const editTemplate = (onSubmit) => html`
    <section id="edit">
        <div class="form">
            <h2>Edit Offer</h2>
            <form class="edit-form" @submit="${onSubmit}">
                <input
                        type="text"
                        name="title"
                        id="job-title"
                        placeholder="Title"
                />
                <input
                        type="text"
                        name="imageUrl"
                        id="job-logo"
                        placeholder="Company logo url"
                />
                <input
                        type="text"
                        name="category"
                        id="job-category"
                        placeholder="Category"
                />
                <textarea
                        id="job-description"
                        name="description"
                        placeholder="Description"
                        rows="4"
                        cols="50"
                ></textarea>
                <textarea
                        id="job-requirements"
                        name="requirements"
                        placeholder="Requirements"
                        rows="4"
                        cols="50"
                ></textarea>
                <input
                        type="text"
                        name="salary"
                        id="job-salary"
                        placeholder="Salary"
                />

                <button type="submit">post</button>
            </form>
        </div>
    </section>
`

export async function editPage(ctx) {
    ctx.render(editTemplate(onSubmit))
    const id = ctx.params.id
    const offer = await getOfferDetails(id)
    let formData = new FormData(document.querySelector('form'));

    document.getElementById('job-title').value = offer.title;
    document.getElementById('job-logo').value = offer.imageUrl;
    document.getElementById('job-category').value = offer.category;
    document.getElementById('job-description').value = offer.description;
    document.getElementById('job-requirements').value = offer.requirements;
    document.getElementById('job-salary').value = offer.salary;

    async function onSubmit(event) {
        event.preventDefault()
        formData = new FormData(document.querySelector('form'));
        let data = {
            title: formData.get('title'),
            imageUrl: formData.get('imageUrl'),
            category: formData.get('category'),
            description: formData.get('description'),
            requirements: formData.get('requirements'),
            salary: formData.get('salary')
        }
        for (const key in data) {
            if (Object.hasOwnProperty.call(data, key)) {
                const element = data[key];
                if (!element) {
                    return alert('All fields must be filled')
                }
            }
        }
        editOffer(data,id)
        ctx.page.redirect('/dashboard')
    }
}