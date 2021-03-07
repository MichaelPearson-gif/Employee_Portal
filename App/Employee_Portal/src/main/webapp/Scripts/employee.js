function getInfo(){

    // url endpoint that will be accessed
    let url = "http://localhost:8080/Employee_Portal/api/info"

    // Use XMLHttpRequest for AJAX
    let xhr = new XMLHttpRequest()

    // Grab the tbody element by it's id
    let tbody = document.getElementById("employeeInfo")

    xhr.onreadystatechange = function(){
        if(xhr.status === 200 && xhr.readyState === 4){
            
            // Parse the JSON object
            let info = JSON.parse(xhr.responseText)

            console.log(info)

            // New elements that will be appended to the page
            let tr = document.createElement('tr')
            let email = document.createElement('td')
            let name = document.createElement('td')
            let phone = document.createElement('td')
            let gender = document.createElement('td')
            let birthday = document.createElement('td')
            let salary = document.createElement('td')
            let title = document.createElement('td')

            // Parse through the birthday
            let parsedDob = new Date(info.dob)

            // Populate the new elements with the info
            email.innerHTML = info.email
            name.innerHTML = info.firstName + " " + info.lastName
            phone.innerHTML = info.phone
            gender.innerHTML = info.gender
            birthday.innerHTML = parsedDob.getFullYear() + '-' + parsedDob.getMonth() + '-' + parsedDob.getDay()
            salary.innerHTML = '$' + info.salary
            title.innerHTML = info.title

            // Elements with setAttribute are elements that can be modified by the /update endpoint
            name.setAttribute("id", "getName")
            gender.setAttribute("id", "getGender")


            // Append all the elements together
            tr.append(email)
            tr.append(name)
            tr.append(phone)
            tr.append(gender)
            tr.append(birthday)
            tr.append(salary)
            tr.append(title)
            tbody.append(tr)

        }
    }

    xhr.open("GET", url) 
    xhr.send()

}

// Load the data when page loads
window.onload = function(){
    getInfo()
}