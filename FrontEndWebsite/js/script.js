
// Search Bar functionality
let searchBtn = document.querySelector('#search-btn');
let searchBar = document.querySelector('.searchBarContainer');


window.onscroll = () =>{
    searchBtn.classList.remove('fa-times');
    searchBar.classList.remove('active');
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}

searchBtn.addEventListener('click', () =>{
    searchBtn.classList.toggle('fa-times');
    searchBar.classList.toggle('active');
});


// Login functionality
let formBtn = document.querySelector('#login-btn');
let loginForm = document.querySelector('.loginFormContainer');
let loginClose = document.querySelector('#form-close');

formBtn.addEventListener('click', () =>{
    loginForm.classList.add('active');
});

loginClose.addEventListener('click', () =>{
    loginForm.classList.remove('active');
});

// Cart DropDown Functionality
let cartFormBtn = document.querySelector('#cart-btn');
let cartForm = document.querySelector('.cartbox-container');
let cartClose = document.querySelector('#cart-close');

cartFormBtn.addEventListener('click', () =>{
    cartForm.classList.add('active');
});

cartClose.addEventListener('click', () =>{
    cartForm.classList.remove('active');
});

    // CART MAPPING FUNCTIONALITY FOR STATIC ITINERARIES**********************************************************************************
    


    // END CART MAPPING FOR STATIC ITINERARIES*******************************************************************************************

// Menu Functionality that doesn't work
let menu = document.querySelector('#menu-bar');
let navbar = document.querySelector('.navbar');

menu.addEventListener('click', () =>{
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
});

//Home Page Video Functionality
let videoBtn = document.querySelectorAll('.video-btn');
videoBtn.forEach(btn =>{
    btn.addEventListener('click', ()=>{
        document.querySelector('.controls .active').classList.remove('active');
        btn.classList.add('active');
        let src = btn.getAttribute('data-src');
        document.querySelector('#video-transition').src = src;
    });
});

// Slider Mechanism for Reviews
var swiper = new Swiper(".review-slider", {
    spaceBetween: 20,
    loop:true,
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
    },
    breakpoints:{
        640: {
            slidesPerView: 1,
        },
        768: {
            slidesPerView:2,
        },
        1024:{
            slidesPerView:3,
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
                    <h3> <i class="fas fa-map-marker-alt"></i> ${itinerary.destination} </h3>
                    <p>${itinerary.description}</p>
                    <p> Open Slots: ${itinerary.slots}</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <div class="price"> ${itinerary.price} </div>
                    <a href="#" class="btn">Book Now!</a>
                </div>
           
            `;
    
            console.log(cDiv);
            cDiv.setAttribute('class', 'box');
            cDiv.setAttribute('style', 'width:400px;');
    
            //Finally we can append the itineraries
            itineraryContainer.append(cDiv);
        }
    }
    
    const URL = 'http://localhost:8080';
    
    (async () => {
        let req = await fetch(`${URL}/itineraries`);
        let res = await req.json();
        console.log(res);
        itineraries = res;
        itineraryContainer.innerHTML="";
        populateItinerary(itineraries);
    })();

// End of Itinerary Additions 