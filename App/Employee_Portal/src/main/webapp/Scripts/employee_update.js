// Create a JS version of the employee object with a constructor
function Employee(firstName, lastName, gender){
    this.firstName = firstName
    this.lastName = lastName
    this.gender = gender
}

function updateInfo(){

    // url and xhr variables
    let url = 'http://localhost:8080/Employee_Portal/api/update'
    let xhr = new XMLHttpRequest()

    // Get the elements from the table with an id
    let getName = document.getElementById("getName").innerHTML
    let getGender = document.getElementById("getGender").innerHTML

    // Print to the console 
    console.log(getName)
    console.log(getGender)

    // Split the string name. It will automatically made into an array of size 2
    let nameArray = getName.split(" ")

    // Set the getFirstName and getLastName elements to their respective elements in the array
    let getFirstName = nameArray[0]
    let getLastName = nameArray[1]

    // Array of the get elements
    let getArray = [getFirstName, getLastName, getGender]

    // Get input elements by id
    let inputFirstName = document.getElementById('userFirstName').value
    let inputLastName = document.getElementById('userLastName').value
    let inputGender = document.getElementById('userGender').value

    // Array of the input elements
    let inputArray = [inputFirstName, inputLastName, inputGender]
    
    // Compare the getArray and the inputArray
    for(i = 0; i <= getArray.length; i++){
        // Check to see if any elements in the inputArray are empty strings
        if(inputArray[i] === ""){
            // Set that element equal to the corresponding getArray element
            inputArray[i] = getArray[i]
        }
    }

    // Assign each element in the inputArray to a variable that will be sent to the server
    let firstName = inputArray[0]
    let lastName = inputArray[1]
    let gender = inputArray[2]

    // Print the elements to the console to double check
    console.log(firstName)
    console.log(lastName)
    console.log(gender)

    // Object to be sent back to the server
    let employee = new Employee(firstName, lastName, gender)

    console.log(employee)
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