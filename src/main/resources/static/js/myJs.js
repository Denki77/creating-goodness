let userId = undefined;
const profileUrl = 'http://localhost:8080/api/v1/profile/';
const authUrl = 'http://localhost:8080/api/v1/auth/';

const profile = document.querySelector('.profile'),
    mainContainer = document.querySelector('.main-container'),
    banner = document.querySelector('.banner'),
    mainNav = document.querySelector('.main-nav'),
    news = document.querySelector('.news');

mainNav.addEventListener('click', () => {
    profile.style.display = "none";
    mainContainer.style.display = "block";
    banner.style.display = "block";
    news.style.display = "flex";
});

function onProfileLinkClick() {
    profile.style.display = "block";
    mainContainer.style.display = "none";
    banner.style.display = "none";
    news.style.display = "none";


    fetch(profileUrl, {
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token")
        }
    }).then((response) => {
        return response.json();
    }).then((data) => {
        console.log(data);
        const form = document.forms.profile;
        form.elements.firstname.value = data.firstname || '';
        form.elements.lastname.value = data.lastname || '';
        form.elements.email.value = data.mail || '';
        form.elements.comment.value = data.comment || '';
        userId = data.userId;
    });
}

function onSubmitProfile() {
    const profile = document.forms.profile;

    const user = {};
    user.firstname = profile.elements.firstname.value;
    user.lastname = profile.elements.lastname.value;
    user.comment = profile.elements.comment.value;
    user.mail = profile.elements.email.value;
    user.userId = userId;
    console.log(user);
    fetch(profileUrl, {
        method: 'POST',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(r => console.log(r.status));
}

// Get the modal
const modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function onLogin(){
    const login = document.forms.login;
    const user = {};
    user.username = login.elements.uname.value;
    user.password = login.elements.psw.value;

    fetch(authUrl + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((response) => {
        return response.text();
    }).then((data) => {
        localStorage.token = data;
        document.getElementById('id01').style.display='none'
    })

}