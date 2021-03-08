// Create a JS Requests object
function Requests(requestId, status){
    this.requestId = requestId
    this.status = status
}

function submitDecision(){

    // url and xhr variables
    let url = 'http://localhost:8080/Employee_Portal/api/resolving'
    let xhr = new XMLHttpRequest()

    // Get the inputted elements
    let requestId = document.getElementById('requestId').value
    let status = document.getElementById('status').value

    // Object to be sent back to the server
    let updatedRequest = new Requests(requestId, status)

    // Ready State
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            // Print message to the console to indicate that it worked
            console.log('Decision successfully sent')
        }
    }

    // Send the data
    xhr.open("POST", url)
    xhr.send(JSON.stringify(updatedRequest))
}

// Grab the submit button
let submitButton = document.getElementById('submitUpdate')

// Event Listener
submitButton.addEventListener("click", submitDecision)