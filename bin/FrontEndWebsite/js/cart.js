
let cartFormBtn = document.querySelector('#cart-btn');
let cartForm = document.querySelector('.cartbox-container');
let cartClose = document.querySelector('#cart-close');

// Moved these to else statement so that if not logged then can't access

// cartFormBtn.addEventListener('click', () =>{
//     cartForm.classList.add('active');
// });

// cartClose.addEventListener('click', () =>{
//     cartForm.classList.remove('active');
// });



// Trying to add functionality to cart
// Check if user is logged in before checking cart
let products;
let cartContainer = document.getElementById("cart-wrapper");
let loggedUserId = sessionStorage.getItem("username");

cartFormBtn.addEventListener('click', () =>{
    if (loggedUserId == "undefined" || loggedUserId == null || !loggedUserId) {
        window.location.href = "index.html";
        alert("Please log in to view the cart");
      }
      else{
        cartForm.classList.add('active');
      }
});

cartClose.addEventListener('click', () =>{
    cartForm.classList.remove('active');
});

// btn event
let fullContainer = document.querySelector('#itinerary-container');
let cartItemContainer = document.getElementById('cartItemContainer');
let subtotalText = document.querySelector('#subtotal span');
let checkout = document.querySelector('.checkout');
checkout.addEventListener('click', (event)=>{
  let cartItems= Array.from(cartItemContainer.children);
  let infoForServer= cartItems.map((cartItem)=>{
    let id = cartItem.querySelector('.uniqueID').id;
    let slots = parseInt(cartItem.querySelector('.quantity span').innerText);
    let newSlots = slots - 1;
    return{
      id: id , slots: newSlots
    }
  });
  console.log(infoForServer);
})

cartItemContainer.addEventListener('click', (event)=> {
  let target= event.target
  console.dir(target);
  if (target.classList.contains('removeFromCart')){
    console.log("hello");
    let cartItem = target.parentElement.parentElement;
    let priceText= cartItem.querySelector('.price span');
    cartItem.remove();

    let currentTotal = parseFloat(subtotalText.innerText, 10);
    let priceToRemove = parseFloat(priceText.innerText, 10); 
    let total = (currentTotal - priceToRemove).toFixed(2);
    subtotalText.innerText = total;

  }
})


fullContainer.addEventListener('click', (event)=> {
    let target= event.target
    if (target.classList.contains('bookbtn')){

      if (loggedUserId == "undefined" || loggedUserId == null || !loggedUserId) {
        window.location.href = "index.html";
        alert("Please log in to view the cart");
      }
      else{
        var bookBtn=target;
        var container= bookBtn.parentElement
        var name = container.querySelector('.bookname').innerHTML;
        console.log(name);
        var description = container.querySelector('.bookdescription').innerHTML;
        console.log(description);
        var quantity = container.querySelector('.bookslot').innerHTML;
        console.log(quantity);
        var price = container.querySelector('.bookprice').innerHTML;
        console.log(price);
        var id = container.querySelector('.uniqueID').id;
        console.log(id);
  
        let cDiv = document.createElement('div');
        console.log(cDiv);
    
        //set the innerHTML of the new div
        cDiv.innerHTML = `
       
                <img src="images/AoTLogo.png"> 
                <div class="details">
                <div class = "uniqueID" id="${id}"> </div>
                <h3> ${name} </h3>
                <p>${description}
                <span class="quantity">Quantity:<span> ${quantity}</span></span>
                <span class="price">Price: <span>${price}</span></span>
                </p>
                </div>
                <div class="cancel"><i class="fas fa-window-close removeFromCart"></i></div>
       
        `;
    
    
        console.log(cDiv);
        cDiv.setAttribute('class', 'cart-item');
    
        //Finally we can append the itineraries
        cartItemContainer.append(cDiv);
        //Subtotal
        let currentTotal = parseFloat(subtotalText.innerText, 10);
        let priceToUse = parseFloat(price, 10);
        let total = (currentTotal + priceToUse).toFixed(2);
        subtotalText.innerText = total;

    }
  }
})













// Forget this
// function fillCart(products) {
//   for (p of products) {
//     let divCart = document.createElement("div");

//     divCart.innerHTML = `
        
//     <img src="images/AoTLogo.png"> 
//     <div class="details">
//         <h3>Trip Destination: ${itinerary.destination}</h3>
//         <p>${itinerary.description}
//          <span class="quantity">Quantity: 1</span>
//             <span class="price">Price: ${itinerary.price}</span>
//         </p>
//     </div>
//     <div class="cancel"><i class="fas fa-window-close"></i></div>
//         `;

//     divCart.setAttribute("class", "cart-item");
//     cartContainer.append(divCart);
//   }
// }

// (async () => {
//   let req = await fetch(`${URL}/users/cart/${loggedUserId}`);
//   let res = await req.json();
//   products = res;
//   cartContainer.innerHTML = "";
//   fillCart(products);
// })();

// // remove from cart 
// let removeFromCart = async (e) => {
//   let sku = e;
//   let id = sessionStorage.getItem("id");
//   let req = await fetch(`http://localhost:8080/users/removefromcart/${sku}`, {
//     method: "POST",
//     headers: { "Content-Type": "application/json", user_id: `${id}` },
//   });
//   let res = await req.json();
//   console.log(res);
//   location.reload();
// };

// // Checkout
// let checkOut = async () => {
//   if (products == "") {
//     window.location.href = "products.html";
//     alert("Add items first!!!");
//   }
//   let totalInvoice = 0;
//   for (p of products) {
//     let amount = document.getElementById(p.sku).value;
//     totalInvoice += amount * p.unitprice;
//     let newQuantity = p.quantity - amount;
//     let updateObj = {
//       sku: p.sku,
//       category: p.category,
//       name: p.name,
//       quantity: newQuantity,
//       unitprice: p.unitprice,
//       path: p.path,
//     };

//     let req = await fetch(`http://localhost:8080/products`, {
//       method: "PUT",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(updateObj),
//     });
//     let res = await req.json();
//     console.log(res);
//   }

//   //now clearing the cart
//   let req2 = await fetch(`http://localhost:8080/users/clear/${loggedUserId}`, {
//     method: "PUT",
//   });
//   let res = await req2.json();
//   console.log(res);

//   window.location.href = "home.html";
//   alert("Order placed successfully, your total = " + totalInvoice + " $");
// };