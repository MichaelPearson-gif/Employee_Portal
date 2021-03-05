function updateInfo(){

    // url and xhr variables
    let url = 'http://localhost:8080/Employees_Portal/api/update'
    let xhr = new XMLHttpRequest()

    // Get elements by id
    let email = document.getElementById('userEmail').innerHTML
    let firstName = document.getElementById('userFirstName').innerHTML
    let lastName = document.getElementById('userLastName').innerHTML
    let gender = document.getElementById('userGender').innerHTML
    let title = document.getElementById('userTitle').innerHTML
    let password = document.getElementById('userPassword').innerHTML

    // Object to be sent back to the server
    let employee = {
        'email': email,
        'firstName': firstName,
        'lastName': lastName,
        'gender': gender,
        'title': title,
        'password': password
    }

    // Ready State
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            // Print message to console to indicate that it worked
            console.log('Employee successfully sent')
        }
    }
    // Send the data
    xhr.open("POST", url)
    xhr.send(JSON.stringify(employee))
}

// Grab the submit button
let submit = document.getElementById('submit')

// Event Listenter
submit.addEventListener("click", updateInfo)