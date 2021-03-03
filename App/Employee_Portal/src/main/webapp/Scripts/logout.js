function getLogout(){

    // url endpoint that will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/logout"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            console.log("Credentials Successfully sent")
        }
    }

    xhr.open("GET", url)

    // Stringify the json object 
    xhr.send()

}

// Grab the button element
let submitButton = document.getElementById("btn")

// Create the event listener
submitButton.addEventListener("click", getLogout)