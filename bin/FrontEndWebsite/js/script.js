
// Search Bar functionality
let searchBtn = document.querySelector('#search-btn');
let searchBar = document.querySelector('.searchBarContainer');


window.onscroll = () => {
    searchBtn.classList.remove('fa-times');
    searchBar.classList.remove('active');
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}

searchBtn.addEventListener('click', () => {
    searchBtn.classList.toggle('fa-times');
    searchBar.classList.toggle('active');
});


// Login functionality
//Added from watching lecture: This adds an event listener to see when submit is entered in the loginformcontainer
let loginButton = document.querySelector(".loginFormContainer").addEventListener("submit", submitLogin);

let formBtn = document.querySelector('#login-btn');
let loginForm = document.querySelector('.loginFormContainer');
let loginClose = document.querySelector('#form-close');

// Calls postLogin
function submitLogin(event) {
    event.preventDefault();
    postLogin();
}

// constant URL
const URL = "http://project2-env.eba-yp8rsa4c.us-east-2.elasticbeanstalk.com";

// This is getting the value of what is inputed in the username and password fields
// and sends a post request to the database
let postLogin = async () => {
    let username = document.getElementById('login-username').value;
    let password = document.getElementById('login-password').value;

    let loginObj = {
        username,
        password
    }

    console.log(loginObj)

    let req = await fetch(`${URL}/users/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginObj)
    });

    let res = await req.json();

    console.log(res);

    // Time to store the information in the session storage
    sessionStorage.setItem("id", `${res.id}`)
    sessionStorage.setItem("username", `${res.username}`)

    window.location.href = "index.html"
}


formBtn.addEventListener('click', () => {
    loginForm.classList.add('active');
});

loginClose.addEventListener('click', () => {
    loginForm.classList.remove('active');
});

// Cart DropDown Functionality
    // MOVED TO SEPERATE JS FILE

    // CART MAPPING FUNCTIONALITY FOR STATIC ITINERARIES**********************************************************************************


    // END CART MAPPING FOR STATIC ITINERARIES*******************************************************************************************

// Menu Functionality that doesn't work
let menu = document.querySelector('#menu-bar');
let navbar = document.querySelector('.navbar');

menu.addEventListener('click', () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
});

//Home Page Video Functionality
let videoBtn = document.querySelectorAll('.video-btn');
videoBtn.forEach(btn => {
    btn.addEventListener('click', () => {
        document.querySelector('.controls .active').classList.remove('active');
        btn.classList.add('active');
        let src = btn.getAttribute('data-src');
        document.querySelector('#video-transition').src = src;
    });
});

// Slider Mechanism for Reviews
var swiper = new Swiper(".review-slider", {
    spaceBetween: 20,
    loop: true,
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
    },
    breakpoints: {
        640: {
            slidesPerView: 1,
        },
        768: {
            slidesPerView: 2,
        },
        1024: {
            slidesPerView: 3,
        },
    },
});


// Itinerary Additions 

let itineraries;

let itineraryContainer = document.getElementById('itinerary-container');
console.log(itineraryContainer);


    // for loop to populate
    function populateItinerary(itineraries){
        for(itinerary of itineraries){
            //For each itinerary in the intinerary list, create a new div
            let cDiv = document.createElement('div');
            console.log(cDiv);
    
            //set the innerHTML of the new div
            cDiv.innerHTML = `
           
                <img src="images/AKLake.JPG" alt="">
                <div class = "content">
                    <div class = "uniqueID" id="${itinerary.id}"> </div>
                    <h3 class = "bookname"> <i class="fas fa-map-marker-alt"></i> ${itinerary.destination} </h3>
                    <p class="bookdescription">${itinerary.description}</p>
                    <p> Open Slots: <p class = "bookslot"> ${itinerary.slots} </p> </p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <div class="price bookprice" > ${itinerary.price} </div>
                    <input type="submit" class="btn bookbtn" value="Book Now!">
                </div>
           
            `;

    
            console.log(cDiv);
            cDiv.setAttribute('class', 'box');
            cDiv.setAttribute('style', 'width:400px;');
    
            //Finally we can append the itineraries
            itineraryContainer.append(cDiv);
        }
    }
    
    (async () => {
        let req = await fetch(`${URL}/itineraries`);
        let res = await req.json();
        console.log(res);
        itineraries = res;
        itineraryContainer.innerHTML="";
        populateItinerary(itineraries);
    })();

// End of Itinerary Additions 
