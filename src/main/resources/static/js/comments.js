const recipeId = document.getElementById('recipeId').value;

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentsContainer = document.getElementById('commentCntr');

const commentForm = document.getElementById("commentForm");
commentForm.addEventListener("submit", submitComment);

const allComments = [];

const displayComments = (comments) => {

    commentsContainer.innerHTML = comments.map(comment => {
            return asComment(comment)
        }
    ).join('')
};

async function submitComment(event) {

    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        commentsContainer.insertAdjacentHTML("beforeend", asComment(responseData));

        form.reset();
    } catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldsWithErrors) {
            errorObj.fieldsWithErrors.forEach(e => {

                let elementWithError = document.getElementById(e);

                if (elementWithError) {
                    elementWithError.classList.add("is-invalid");
                }
            })
        }
    }

}

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName]: csrfHeaderValue,
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: formDataAsJSONString
    };

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
}

function asComment(comment) {

    let commentHtml = `<div class="user-comment" id="commentCntr-${comment.id}">`

    commentHtml += `<div class="user-info-container">
            <img alt="Profile"
             class="rounded-circle" height="50" width="50" src="${comment.authorProfilePictureUrl}"/>
            <a class="username" href="/users/profile/${comment.author}">${comment.author}</a><br/>
        </div>`

    commentHtml += `<p>${comment.message}</p>`
    commentHtml += `<small>(${comment.created})</small>`
    commentHtml += `</div>`

    return commentHtml;
}

fetch(`http://localhost:8080/api/${recipeId}/comments`)
    .then(response => response.json())
    .then(info => {
        for (let comment of info) {
            allComments.push(comment)
        }

        displayComments(allComments)
    });
