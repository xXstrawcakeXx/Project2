// Get the container for user info
let personContainer = document.getElementById("user-container")

function populatePerson(person){
    let itineraryInfo = "";
    for(itinerary of person.itineraries){
        itineraryInfo =+ `<h3>${itinerary.itinId}<h3>
                            <h4>${itinerary.name}<h4>
                            <br>`
    }

    personContainer.innerHTML = `
    <h1> Name: ${person.first}  ${person.last} </h1>
    <h2> Username: ${person.username} </h2>
    <h2> Email: ${person.email} </h2>
    <h2> Orders: ${person.username} </h2>
    <br>
    ${itineraryInfo}
    `
}

// Fetch function to get the information for the user
(async () =>{
    
})