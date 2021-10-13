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
        db.collection("Users").doc(user_id).delete()
        .then(() => {
            location.reload(); //refresh page
        })
        .catch((error) => {
            console.log(error)
        })
    }
}

db.collection("Users").get().then((querySnapshot) => {
    querySnapshot.forEach((Users) => {
        let rowArray = []
        if (user_id) {
            if (Users.id == user_id) {
                rowArray.push(Users.id ?? "")
                rowArray.push((`${Users.data()['FirstName']} ${Users.data()['LastName']}`) ?? "")
                rowArray.push(Users.data()['Email'] ?? "")
                rowArray.push(Users.data()['Contact'] ?? "")
                rowArray.push(Users.data()['Address'] ?? "")
                rowArray.push(`<a href="#" onclick="deleteUser('${Users.id}')"><i title="Delete user" class="fas fa-trash-alt" style="color: red"></i></a>`)
                tableUserData.push(rowArray)
            }
        } else {
            rowArray.push(Users.id ?? "")
            rowArray.push((`${Users.data()['FirstName']} ${Users.data()['LastName']}`) ?? "")
            rowArray.push(Users.data()['Email'] ?? "")
            rowArray.push(Users.data()['Contact'] ?? "")
            rowArray.push(Users.data()['Address'] ?? "")
            rowArray.push(`<a href="#" onclick="deleteUser('${Users.id}')"><i title="Delete user" class="fas fa-trash-alt" style="color: red"></i></a>`)
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
                { title: "Name" },
                { title: "Email" },
                { title: "Contact" },
                { title: "Address" },
                { title: "Action" }
            ]
        });
    });
})
.catch((error) => {
    console.log("Error getting user documents: ", error)
})



// db.collection("Users").get().then((querySnapshot) => {
//     querySnapshot.forEach((Users) => {
//         // $('#userTable').append(`
//         //         <tr>
//         //             <td>${Users.id}</td>
//         //             <td>${Users.data()['LastName', 'FirstName']}</td>
//         //             <td>${Users.data()['Email']}</td>
//         //             <td>${Users.data()['Contact']}</td>
//         //             <td>${Users.data()['Address']}</td>
//         //             <td></td>
//         //         </tr>
//         //     `)
//         // });
//     })
// })
// .then(() => {
//     // <tr>
//     //     <th>User Account ID</th>
//     //     <th>Account Name</th>
//     //     <th>E-mail</th>
//     //     <th>Contact</th>
//     //     <th>Address</th>
//     //     <th>Action</th>
//     // </tr>
// })
// .catch((error) => {
//     console.log("Error getting User documents: ", error);
// });
