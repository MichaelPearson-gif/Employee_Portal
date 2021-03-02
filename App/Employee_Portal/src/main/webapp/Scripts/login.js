function postLogin(){

    // url endpoint that will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/login"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the values from the input fields
    let email = document.getElementsByName('email').values
    let password = document.getElementsByName('password').values

    // Create an object to store the input values
    let credentials = {
        'email': email,
        'password': password
    }

    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            console.log("Credentials Successfully sent")
        }
    }

    xhr.open("POST", url)

    // Stringify the json object 
    xhr.send(JSON.stringify(credentials))

}

// Grab the button element
let submitButton = document.querySelector('button')

// Create the event listener
submitButton.addEventListener("click", postLogin)