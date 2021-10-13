let usersData = []
let getUserContact = function (userId) {
    if (usersData) {
        let userFound = null
        usersData.map(user => {
            if (user.id == userId) {
                userFound = user
            }
        })
        if (userFound) {
            return userFound.data()['Contact']
        }
    }

    return null
}

var emergencyReportsData = []
var normalReportsData = []

var emertable = $('#emerTable').DataTable({
    data: emergencyReportsData,
    columns: [
        { title: "Emergency ID" },
        { title: "Category" },
        { title: "Contact" },
        { title: "Complaint Description" },
        { title: "Location" },
        { title: "Date" },
        { title: "Status" },
        { title: "Action" }
    ]
});

var norTable = $('#norTable').DataTable({
    data: normalReportsData,
    columns: [
        { title: "Report ID" },
        { title: "Category" },
        { title: "Contact" },
        { title: "Complaint Description" },
        { title: "Location" },
        { title: "Date" },
        { title: "Status" },
        { title: "Action" }
    ]
});


// REMOVE INTERVAL TO SAVE FREE DATA

//interval = 5000

//setInterval(() => {
    emergencyReportsData = []
    normalReportsData = []

    // By default JavaScript is asynchronous programming
    var db = firebase.firestore()
    db.collection("Users").get().then((querySnapshot) => {
        querySnapshot.forEach((user) => {
            usersData.push(user)
        })
    }).then(() => {
        // synchronous programming
        db.collection("Emergency Reports").get().then((querySnapshot) => {
            querySnapshot.forEach((emergencyReports) => {
                let rowArray = []
                let read = emergencyReports.data()['read']
                if (read) {
                    rowArray.push(emergencyReports.id)
                    rowArray.push(emergencyReports.data()['Report Category'])
                    rowArray.push(getUserContact(emergencyReports.data()['User UID']))
                    rowArray.push(emergencyReports.data()['Report Description'])
                    rowArray.push(emergencyReports.data()['Report Location'])
                    rowArray.push(emergencyReports.data()['DATE'])
                    rowArray.push(emergencyReports.data()['Report Status'])
                    rowArray.push(`<a href="ReplyPage.html?id=${emergencyReports.id}&type=emergency">View Details</a>`)
                } else {
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.id}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.data()['Report Category']}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${getUserContact(emergencyReports.data()['User UID'])}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.data()['Report Description']}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.data()['Report Location']}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.data()['DATE']}</b></div>`)
                    rowArray.push(`<div style="color: red;"><b>${emergencyReports.data()['Report Status']}</b></div>`)
                    rowArray.push(`<a href="ReplyPage.html?id=${emergencyReports.id}&type=emergency">View Details</a>`)
                }
                
                emergencyReportsData.push(rowArray)
            })
        })
        .then(() => {
            $(document).ready(function () {
                console.log(emergencyReportsData)
                emertable.destroy();
                emertable = $('#emerTable').DataTable({
                    data: emergencyReportsData,
                    columns: [
                        { title: "Emergency ID" },
                        { title: "Category" },
                        { title: "Contact" },
                        { title: "Complaint Description" },
                        { title: "Location" },
                        { title: "Date" },
                        { title: "Status" },
                        { title: "Action" }
                    ]
                });
            });
        })
        .catch((error) => {
            console.log("Error getting emergency reports documents: ", error)
        })
    }).catch((error) => {
        console.log("Error getting user documents: ", error)
    })




    /*-- Users --*/
    db.collection("Users").get().then((querySnapshot) => {
        querySnapshot.forEach((user) => {
            usersData.push(user)
        })
    }).then(() => {
        // synchronous programming
        db.collection("Normal Reports").get().then((querySnapshot) => {
            querySnapshot.forEach((normalReports) => {
                let rowArray = []
                let read = normalReports.data()['read']
                if (read) {
                    rowArray.push(normalReports.id)
                    rowArray.push(normalReports.data()['Report Category'])
                    rowArray.push(getUserContact(normalReports.data()['User UID']))
                    rowArray.push(normalReports.data()['Report Description'])
                    rowArray.push(normalReports.data()['Report Location'])
                    rowArray.push(normalReports.data()['DATE'])
                    rowArray.push(normalReports.data()['Report Status'])
                    rowArray.push(`<a href="ReplyPage.html?id=${normalReports.id}&type=normal">View Details</a>`)
                } else {
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.id}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.data()['Report Category']}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${getUserContact(normalReports.data()['User UID'])}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.data()['Report Description']}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.data()['Report Location']}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.data()['DATE']}</b></div>`)
                    rowArray.push(`<div style="color: gold;"><b>${normalReports.data()['Report Status']}</b></div>`)
                    rowArray.push(`<a href="ReplyPage.html?id=${normalReports.id}&type=normal">View Details</a>`)
                }
                
                normalReportsData.push(rowArray)
            })
        })
        .then(() => {
            $(document).ready(function () {
                console.log(normalReportsData)
                norTable.destroy();
                norTable = $('#norTable').DataTable({
                    data: normalReportsData,
                    columns: [
                        { title: "Report ID" },
                        { title: "Category" },
                        { title: "Contact" },
                        { title: "Complaint Description" },
                        { title: "Location" },
                        { title: "Date" },
                        { title: "Status" },
                        { title: "Action" }
                    ]
                });
            });
            
        })
        .catch((error) => {
            console.log("Error getting normal reports documents: ", error)
        })
    }).catch((error) => {
        console.log("Error getting user documents: ", error)
    })
//}, interval);

