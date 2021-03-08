function getEmployeesPendingRequests(){

    // url endpoint tha will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/pending/requests"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the tbody element by it's id
    let tbody = document.getElementById("employeePendingRequestInfo")

    // Manipulate the DOM when the ready state is 4
    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){

            // Parse the JSON object
            let employeesPendingRequests = JSON.parse(xhr.responseText)

            // Iterate through the data
            for(let epr of employeesPendingRequests){

                // New elements that will be appended to the page
                let tr = document.createElement('tr')
                let requestID = document.createElement('td')
                let employee = document.createElement('td')
                let amount = document.createElement('td')
                let status = document.createElement('td')
                let date = document.createElement('td')

                // Parse through the date
                let parsedDate = new Date(epr.date)

                // Populate the new elements with the data
                requestID.innerHTML = epr.requestId
                employee.innerHTML = epr.employee.firstName + ' ' + epr.employee.lastName
                amount.innerHTML = '$'+ epr.amount
                status.innerHTML = epr.status
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
    xhr.send()

}

// Load the data when the page loads
window.onload = function(){
    getEmployeesPendingRequests()
}