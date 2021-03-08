// Create a JS Requests object
function Requests(amount){
    this.amount = amount
}

function submitRequest(){

    // url and xhr variables
    let url = 'http://localhost:8080/Employee_Portal/api/request'
    let xhr = new XMLHttpRequest()

    // Get the inputted amount
    let amount = document.getElementById('amount').value

    // Object to be sent back to the server
    let newRequest = new Requests(amount)

    // Ready State
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            // Print message to the console to indicate that it worked
            console.log('Request successfully sent')
        }
    }

    // Send the data
    xhr.open("POST", url)
    xhr.send(JSON.stringify(newRequest))
}

// Grab the submit button
let submitButton = document.getElementById('submitRequest')

// Event Listener
submitButton.addEventListener("click", submitRequest)