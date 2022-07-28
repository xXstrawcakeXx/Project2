// Register Functionality
let registerButton = document.querySelector(".registerbox").addEventListener("submit", submitRegister);

function submitRegister(event) {
    event.preventDefault();
    postRegister();
}


// This is getting the value of what is inputed in the username and password fields
// and sends a post request to the database
let postRegister = async () => {
    let firstName = document.getElementById('first').value;
    let lastName = document.getElementById('last').value;
    let email = document.getElementById('email').value;
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let registerObj = {
        firstName,
        lastName,
        username,
        password,
        email
    }

    console.log(registerObj)

    // constant URL
    const URL = "http://project2-env.eba-yp8rsa4c.us-east-2.elasticbeanstalk.com";

    let req = await fetch(`${URL}/users`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(registerObj)
    });

    let res = await req.json();

    console.log(res);

    // Time to store the information in the session storage
    sessionStorage.setItem("id", `${res.id}`)
    sessionStorage.setItem("username", `${res.username}`)

    window.location.href = "index.html"
}
