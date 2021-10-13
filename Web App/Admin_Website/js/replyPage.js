var db = firebase.firestore()

const queryString = window.location.search
const urlParams = new URLSearchParams(queryString)

let collectionName = (urlParams.get('type') == "emergency") ? "Emergency Reports" : "Normal Reports";
let documentId =  urlParams.get('id')

var reportId = null
let reportData = null
let userData = null

var reportRef = db.collection(collectionName).doc(documentId);

reportRef.update({
	'read': true
})

reportRef.get().then((report) => {
    if (report.exists) {
		console.log("Document ID:", report.id)
        console.log("Document data:", report.data())

		reportId = report.id
        reportData = report.data()

		$('#display-reportID').html(`Report ID: ${report.id}`)
		$('#diplay-reportCategory').html(`Category: ${reportData['Report Category']}`)
		$('#diplay-loction').html(`Location: ${reportData['Report Location']}`)
		$('#report-tbl-status').val(reportData['Report Status'])

		$('#adminReply').html(reportData['Admin Reply'])
		$('#userReply').html(reportData['User Reply'])

		if (reportData.Image) {
			$('#reportImage').attr('src', reportData.Image)
		}
    } else {
        console.log("No such document!")
    }
}).then(() => {
    var userRef = db.collection('Users').doc(reportData['User UID']);
    userRef.get().then((user) => {
		console.log("User ID:", user.id)
        console.log("User data:", user.data())
        userData = user.data()

		$('#reporter-name').html(`${userData.FirstName} ${userData.LastName}`)

        $('#user-link').attr('href', `UserInformation.html?user_id=${user.id}`)
    }).catch((error) => {
        console.log("Error getting user:", error)
    });
}).catch((error) => {
    console.log("Error getting document:", error)
})

$(document).ready(() => {
	$('#btn-update-status').click(() => {
		let jsonUpdate = {
			'Report Status': $('#report-tbl-status').val()
		}
		alert("Update successful");
		console.log(jsonUpdate)
		reportRef.update(jsonUpdate)
	})

	$('#btn-reply').click(() => {
		let reply = $('#adminReply').val()
		if(reply) {
			let jsonUpdate = {
				'Admin Reply': reply
			}
			alert("Reply sent.");
			console.log(jsonUpdate)
			reportRef.update(jsonUpdate)
		} else {
			alert("Please input reply...");
			console.log("Please input reply...")
		}	
	})
})
