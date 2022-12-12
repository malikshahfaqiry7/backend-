function getAllEmployee() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            generate_table1(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", "http://localhost:8080/employee", true);
    xhttp.send();
}


function deleteEmployee(eid) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
            getAllEmployee();
        }
    };
    xhttp.open("DELETE", "http://localhost:8080/employee/" + eid, true);
    xhttp.send();
}

function addEmployee() {
    //const eid =document.getElementById("eid").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const phone = document.getElementById("phone").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
            document.location.href = "list-employee.html";
        }
    };

  let jsObject=  {
            "name": name,
            "email": email,
            "phone": phone,
            "address": address
        };

    const json = JSON.stringify(jsObject);
    xhttp.open("POST", "http://localhost:8080/employee", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
}

function updateEmployee() {
    const eid = document.getElementById("eid").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const phone = document.getElementById("phone").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
            sessionStorage.removeItem('userObject');
            document.location.href = "list-employee.html";
        }
        document.location.href = "list-employee.html";
    };

    const json = JSON.stringify({
        "eid": eid,
        "name": name,
        "email": email,
        "phone": phone,
        "address": address
    });
    xhttp.open("PUT", "http://localhost:8080/employee", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
}


function generate_table1(obj) {
    // creates a <table> element and a <tbody> element
    var node = document.getElementById("employee");
    while (node.hasChildNodes()) {
        node.removeChild(node.lastChild);
    }

    var tbl = document.getElementById("employee");
    var tblBody = document.createElement("tbody");
    for (let x in obj) {
        const emp = obj[x];
        var row = document.createElement("tr");
        row.setAttribute("id", emp.eid);
        const columns = Object.keys(emp);
        columns.forEach((item) => {
            var cell = document.createElement("td");
            cell.setAttribute("class", "row-data");
            var cellText = document.createTextNode(emp[item]);
            cell.appendChild(cellText);
            row.appendChild(cell);
        });
        var cell = document.createElement("td");
        var cellText = document.createElement("input");
        cellText.setAttribute('type', 'button');
        cellText.setAttribute('value', 'DELETE');
        cellText.setAttribute('class', 'btn btn-primary');
        cellText.setAttribute('onclick', 'index()');
        cell.appendChild(cellText);
        row.appendChild(cell);


        var cell = document.createElement("td");
        var cellText = document.createElement("input");
        cellText.setAttribute('type', 'button');
        cellText.setAttribute('value', 'Update');
        cellText.setAttribute('class', 'btn btn-primary');
        cellText.setAttribute('onclick', 'redirect()');
        cell.appendChild(cellText);
        row.appendChild(cell);

        // add the row to the end of the table body
        tblBody.appendChild(row);
    }
    // put the <tbody> in the <table>
    tbl.appendChild(tblBody);
    // appends <table> into <body>
    // body.appendChild(tbl);
    // sets the border attribute of tbl to 2;
    tbl.setAttribute("border", "2");
}

function index() {
    const rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");
    deleteEmployee(data[0].innerHTML);
}

function redirect(){
    const rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");
    const jsonObj =JSON.stringify({"eid":data[0].innerHTML,"name":data[1].innerHTML,"email":data[2].innerHTML,"address":data[3].innerHTML,"phone":data[4].innerHTML});
    sessionStorage.setItem('userObject',jsonObj);
    document.location.href = "update-employee.html";
}