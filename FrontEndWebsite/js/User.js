// Get the container for user info
let personContainer = document.getElementById("user-container")

function populatePerson(person) {
    let itineraryInfo = "";
    for (itinerary of person.itineraries) {
        itineraryInfo = + `<h3>${itinerary.destination}</h3>
                            <h4>${itinerary.description}</h4>
                            <br>`
    }

    personContainer.innerHTML = `
    <h1> Name: ${person.firstName}  ${person.lastName} </h1>
    <h2> Username: ${person.username} </h2>
    <h2> Email: ${person.email} </h2>
    <h2> Orders: ${person.username} </h2>
    <br>
    ${itineraryInfo}
    `
};

const URL = 'http://project2-env.eba-yp8rsa4c.us-east-2.elasticbeanstalk.com';

// Fetch function to get the information for the user
(async () => {
    if (sessionStorage.getItem("username") == null) {
        window.location.href = "index.html"
    }

    let req = await fetch(`${URL}/users/find/${sessionStorage.getItem("username")}`)
    let res = await req.json();
    personContainer.innerHTML = "";
    populatePerson(res);
    populateUpdate(res);
})();

function toggleUpdateForm() {
    let form = document.getElementById("updatebox")
    if (form.hasAttribute("hidden")) {
        form.removeAttribute("hidden")
    } else {
        form.setAttribute("hidden", "true")
    }
}

function populateUpdate(person) {
    document.getElementById("first").setAttribute("value", `${person.firstName}`)
    document.getElementById("last").setAttribute("value", `${person.lastName}`)
    document.getElementById("username").setAttribute("value", `${person.username}`)
    document.getElementById("password").setAttribute("value", `${person.password}`)
    document.getElementById("email").setAttribute("value", `${person.email}`)
}

// Send PUT request

document.getElementById("updatebox").addEventListener("submit", update)

function update(event){
    event.preventDefault();
    putUser();
}

let putUser = async () => {
    let id = sessionStorage.getItem("id");
    let firstName = document.getElementById("first").value;
    let lastName = document.getElementById("last").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;

    let updateObj = {
        id,
        firstName,
        lastName,
        username,
        password,
        email
    }

    console.log(updateObj)

    let req = await fetch(`${URL}/users/update`, {
        method: "PUT",
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(updateObj)
    })

    let res = await req.json();

    sessionStorage.setItem("username", `${res.username}`)

    location.reload();
}