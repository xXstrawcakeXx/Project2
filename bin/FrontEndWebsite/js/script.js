
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