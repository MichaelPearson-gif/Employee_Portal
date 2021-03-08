function getAllResolvedRequests(){

    // url endpoint tha will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/resolved/requests"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the tbody element by it's id
    let tbody = document.getElementById("employeeResolvedRequestInfo")

    // Manipulate the DOM when the ready state is 4
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){

            // Parse the JSON object
            let allResolvedRequests = JSON.parse(xhr.responseText)

            // Iterate through the data
            for(let arr of allResolvedRequests){

                // New elements that will be appended to the page
                let tr = document.createElement('tr')
                let requestID = document.createElement('td')
                let employee = document.createElement('td')
                let manager = document.createElement('td')
                let amount = document.createElement('td')
                let status = document.createElement('td')
                let date = document.createElement('td')

                // Parse through the date
                let parsedDate = new Date(arr.date)

                // Populate the new elements with the data
                requestID.innerHTML = arr.requestId
                employee.innerHTML = arr.employee.firstName + ' ' + arr.employee.lastName
                manager.innerHTML = arr.manager.employee.firstName + ' ' + arr.manager.employee.lastName
                amount.innerHTML = '$'+ arr.amount
                status.innerHTML = arr.status
                date.innerHTML = parsedDate.getMonth() + '-' + parsedDate.getDay() + '-' + parsedDate.getFullYear()

                // Append all the elements together
                tr.append(requestID)
                tr.append(employee)
                tr.append(manager)
                tr.append(amount)
                tr.append(status)
                tr.append(date)
                tbody.append(tr)

            }

        }
    }

    xhr.open("GET", url)
    xhr.send()

}

// Load the data when the page loads
window.onload = function(){
    getAllResolvedRequests()
}