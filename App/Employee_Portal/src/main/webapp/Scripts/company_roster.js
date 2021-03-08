function getAllEmployees(){

    // url endpoint tha will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/employee/list"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the tbody element by it's id
    let tbody = document.getElementById("rosterInfo")

    // Manipulate the DOM when the ready state is 4
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){

            // Parse the JSON object
            let allEmployees = JSON.parse(xhr.responseText)

            // Iterate through the data
            for(let ae of allEmployees){

                // New elements that will be appended to the page
                let tr = document.createElement('tr')
                let employee = document.createElement('td')
                let manager = document.createElement('td')

                // Populate the new elements with the data
                employee.innerHTML = ae.employee.firstName + ' ' + ae.employee.lastName
                manager.innerHTML = ae.manager.employee.firstName + ' ' + ae.manager.employee.lastName

                // Append all the elements together
                tr.append(employee)
                tr.append(manager)
                tbody.append(tr)
            }

        }
    }

    xhr.open("GET", url)
    xhr.send()

}

// Load the data when the page loads
window.onload = function(){
    getAllEmployees()
}