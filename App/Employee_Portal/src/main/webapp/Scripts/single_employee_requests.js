// function Employee(email){
//     this.email = email
// }

function getRequestsofAnEmployee(){

    // url endpoint tha will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/employee/requests"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the tbody element by it's id
    let tbody = document.getElementById("allRequestsForEmployeeInfo")

    // Grab the email
    let employeeEmail = document.getElementById('employeeEmail')

    // Object to send back to the server
    // let employee1 = new Employee(email)

    // Manipulate the DOM when the ready state is 4
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){

            // Parse the JSON object
            let requestsByEmployee = JSON.parse(xhr.responseText)

            console.log(requestsByEmployee)

            // Iterate through the data
            for(let rbe of requestsByEmployee){

                // New elements that will be appended to the page
                let tr = document.createElement('tr')
                let requestID = document.createElement('td')
                let employee = document.createElement('td')
                let amount = document.createElement('td')
                let status = document.createElement('td')
                let date = document.createElement('td')

                // Parse through the date
                let parsedDate = new Date(rbe.date)

                // Populate the new elements with the data
                requestID.innerHTML = rbe.requestId
                employee.innerHTML = rbe.employee.firstName + ' ' + rbe.employee.lastName
                amount.innerHTML = '$'+ rbe.amount
                status.innerHTML = rbe.status
                date.innerHTML = parsedDate.getMonth() + '-' + parsedDate.getDay() + '-' + parsedDate.getFullYear()

                // Append all the elements together
                tr.append(requestID)
                tr.append(employee)
                tr.append(amount)
                tr.append(status)
                tr.append(date)
                tbody.append(tr)

            }

        }
    }

    xhr.open("GET", url)
    xhr.send(employeeEmail)

}

// Load the data when the button is clicked
let requestSubmit = document.getElementById('employeeRequest')

// Event Listener
requestSubmit.addEventListener("click", getRequestsofAnEmployee)
