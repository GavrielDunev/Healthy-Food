const recipeId = document.getElementById('recipeId').value;

const commentsContainer = document.getElementById('commentCntr');

const allComments = [];

const displayComments = (comments) => {

    commentsContainer.innerHTML = comments.map(comment => {
            return asComment(comment)
        }
    ).join('')
};

function asComment(comment) {

    let commentHtml = `<div class="user-comment" id="commentCntr-${comment.id}">`

    commentHtml += `<div class="user-info-container">
            <img alt="Profile"
             class="rounded-circle" height="50" width="50" src="${comment.authorProfilePictureUrl}"/>
            <a class="username" href="/users/profile/${comment.author}">${comment.author}</a><br/>
        </div>`

    commentHtml += `<p>${comment.text}</p>`
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