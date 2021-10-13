var db = firebase.firestore()

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

const queryString = window.location.search
const urlParams = new URLSearchParams(queryString)

let user_id =  urlParams.get('user_id')

var tableUserData = []

function deleteUser(user_id) {
    let answer = confirm("Are you sure to delete user?")
    if (answer) {
        console.log(user_id)
        db.collection("WebUsers").doc(user_id).delete()
        .then(() => {
            location.reload(); //refresh page
        })
        .catch((error) => {
            console.log(error)
        })
    }
}

function changePassword(user_id) {
    // TODO: change prompt to a modal
    let newPassword = prompt('Please input new password')
    
    if (newPassword) {
        db.collection('WebUsers').doc(user_id).update({
            'Password': newPassword
        });
        alert("Change password successful!")
    }
    
}

db.collection("WebUsers").get().then((querySnapshot) => {
    querySnapshot.forEach((Users) => {
        let rowArray = []
        if (user_id) {
            if (Users.id == user_id) {
                rowArray.push(Users.id ?? "")
                rowArray.push((`${Users.data()['Username']}`) ?? "")
                rowArray.push(`<a href="#" onclick="deleteUser('${Users.id}')"><i title="Delete user" class="fas fa-trash-alt" style="color: red"></i></a>`)
                tableUserData.push(rowArray)
            }
        } else {
            rowArray.push(Users.id ?? "")
            rowArray.push((`${Users.data()['Username']}`) ?? "")
            rowArray.push(`
                <a href="#" onclick="deleteUser('${Users.id}')"><i title="Delete user" class="fas fa-trash-alt" style="color: red"></i></a>
                <a href="#" onclick="changePassword('${Users.id}')"><i title="Change Password" class="fas fa-key" style="color: blue"></i></a>
            `)
            tableUserData.push(rowArray)
        }
    })
})
.then(() => {
    $(document).ready(function () {
        $('#userTable').DataTable({
            data: tableUserData,
            columns: [
                { title: "User ID" },
                { title: "Username" },
                { title: "Action" }
            ]
        });
    });
})
.catch((error) => {
    console.log("Error getting user documents: ", error)
})
