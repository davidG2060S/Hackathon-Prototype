
let totalUsersCount = 0
let totalURCount = 0
let totalUERCount = 0
let totalreportCount = 0
let totalRRCount = 0
let totalRERCount = 0

/* ----- For Emergency Reports ----- */
let pending_AIACount = 0
let pending_AAPCount = 0
let pending_AVCount = 0
let pending_BaECount = 0
let pending_FACount = 0
let pending_FireCount = 0
let pending_MedCount = 0
let pending_RTACount = 0
let pending_SRICount = 0
let pending_FloodCount = 0
let pending_TressCount = 0

let URGENT_AIACount = 0
let URGENT_AAPCount = 0
let URGENT_AVCount = 0
let URGENT_BaECount = 0
let URGENT_FACount = 0
let URGENT_FireCount = 0
let URGENT_MedCount = 0
let URGENT_RTACount = 0
let URGENT_SRICount = 0
let URGENT_FloodCount = 0
let URGENT_TressCount = 0

let resolved_AIACount = 0
let resolved_AAPCount = 0
let resolved_AVCount = 0
let resolved_BaECount = 0
let resolved_FACount = 0
let resolved_FireCount = 0
let resolved_MedCount = 0
let resolved_RTACount = 0
let resolved_SRICount = 0
let resolved_FloodCount = 0
let resolved_TressCount = 0

let total_AIACount = 0
let total_AAPCount = 0
let total_AVCount = 0
let total_BaECount = 0
let total_FACount = 0
let total_FireCount = 0
let total_MedCount = 0
let total_RTACount = 0
let total_SRICount = 0
let total_FloodCount = 0
let total_TressCount = 0


/* ----- For Normal Reports ----- */
let pending_CICount = 0
let pending_CLVCount = 0
let pending_DoPCount = 0
let pending_DistCount = 0
let pending_EngiCount = 0
let pending_ISCount = 0
let pending_SACount = 0
let pending_TheftCount = 0
let pending_WMCount = 0

let resolved_CICount = 0
let resolved_CLVCount = 0
let resolved_DoPCount = 0
let resolved_DistCount = 0
let resolved_EngiCount = 0
let resolved_ISCount = 0
let resolved_SACount = 0
let resolved_TheftCount = 0
let resolved_WMCount = 0

let total_CICount = 0
let total_CLVCount = 0
let total_DoPCount = 0
let total_DistCount = 0
let total_EngiCount = 0
let total_ISCount = 0
let total_SACount = 0
let total_TheftCount = 0
let total_WMCount = 0





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

db.collection("Users").get().then((querySnapshot) => {
    querySnapshot.forEach((Users) => {

      if (Users.data()['Users'] == "Users") {
        totalusersCount++
      }
    
    })
})

/* ----- For Emergency Report Counters ----- */

db.collection("Emergency Reports").get().then((querySnapshot) => {
    querySnapshot.forEach((emergencyReports) => {

        if (emergencyReports.data()['Report Category'] == "Accident involving Animals") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_AIACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_AIACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_AIACount++
                totalRERCount++
            }
            total_AIACount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Actions against Police") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_AAPCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_AAPCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_AAPCount++
                totalRERCount++
            }
            total_AAPCount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Assault/Violence") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_AVCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_AVCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_AAPCount++
                totalRERCount++
            }
            total_AAPCount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Breaking and Entering") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_BaECount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_BaECount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_BaECount++
                totalRERCount++
            }
            total_BaECount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Fatal Accident") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_FACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_FACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_FACount++
                totalRERCount++
            }
            total_FACount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Fire") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_FireCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_FireCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_FireCount++
                totalRERCount++
            }
            total_FireCount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Medical") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_MedCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_MedCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_MedCount++
                totalRERCount++
            }
            total_MedCount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Road Traffic Accident") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_RTACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_RTACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_RTACount++
                totalRERCount++
            }
            total_RTACount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Sports Related Injuries") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_SRICount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_SRICount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_SRICount++
                totalRERCount++
            }
            total_SRICount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Stranded (Flood)") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_FACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_FACount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_FACount++
                totalRERCount++
            }
            total_FACount++
            totalreportCount++
        }
        if (emergencyReports.data()['Report Category'] == "Trespassing") {
            if (emergencyReports.data()['Report Status'] == "Pending") {
                pending_TressCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "URGENT") {
                URGENT_TressCount++
                totalUERCount++
            }
            if (emergencyReports.data()['Report Status'] == "Resolved") {
                resolved_TressCount++
                totalRERCount++
            }
            total_TressCount++
            totalreportCount++
        }

    })
})
.then(() => {
    $('#ER-AIA-pending').html(pending_AIACount)
    $('#ER-AAP-pending').html(pending_AAPCount)
    $('#ER-AV-pending').html(pending_AVCount)
    $('#ER-BaE-pending').html(pending_BaECount)
    $('#ER-FA-pending').html(pending_FACount)
    $('#ER-fire-pending').html(pending_FireCount)
    $('#ER-med-pending').html(pending_MedCount)
    $('#ER-RTA-pending').html(pending_RTACount)
    $('#ER-SRI-pending').html(pending_SRICount)
    $('#ER-SF-pending').html(pending_FloodCount)
    $('#ER-Tress-pending').html(pending_TressCount)

    $('#ER-AIA-URGENT').html(URGENT_AIACount)
    $('#ER-AAP-URGENT').html(URGENT_AAPCount)
    $('#ER-AV-URGENT').html(URGENT_AVCount)
    $('#ER-BaE-URGENT').html(URGENT_BaECount)
    $('#ER-FA-URGENT').html(URGENT_FACount)
    $('#ER-fire-URGENT').html(URGENT_FireCount)
    $('#ER-med-URGENT').html(URGENT_MedCount)
    $('#ER-RTA-URGENT').html(URGENT_RTACount)
    $('#ER-SRI-URGENT').html(URGENT_SRICount)
    $('#ER-SF-URGENT').html(URGENT_FloodCount)
    $('#ER-Tress-URGENT').html(URGENT_TressCount)

    $('#ER-AIA-resolved').html(resolved_AIACount)
    $('#ER-AAP-resolved').html(resolved_AAPCount)
    $('#ER-AV-resolved').html(resolved_AVCount)
    $('#ER-BaE-resolved').html(resolved_BaECount)
    $('#ER-FA-resolved').html(resolved_FACount)
    $('#ER-fire-resolved').html(resolved_FireCount)
    $('#ER-med-resolved').html(resolved_MedCount)
    $('#ER-RTA-resolved').html(resolved_RTACount)
    $('#ER-SRI-resolved').html(resolved_SRICount)
    $('#ER-SF-resolved').html(resolved_FloodCount)
    $('#ER-Tress-resolved').html(resolved_TressCount)
    
    $('#ER-AIA-total').html(total_AIACount)
    $('#ER-AAP-total').html(total_AAPCount)
    $('#ER-AV-total').html(total_AVCount)
    $('#ER-BaE-total').html(total_BaECount)
    $('#ER-FA-total').html(total_FACount)
    $('#ER-fire-total').html(total_FireCount)
    $('#ER-med-total').html(total_MedCount)
    $('#ER-RTA-total').html(total_RTACount)
    $('#ER-SRI-total').html(total_SRICount)
    $('#ER-SF-total').html(total_FloodCount)
    $('#ER-Tress-total').html(total_TressCount)

    $('#totalUnresolvedEmergencyReports').html(`Total Emergency Unresolved Reports: ${totalUERCount}`)
    $('#totalResolvedEmergencyReports').html(`Total Resolved Emergency Reports: ${totalRERCount}`)
    
})
.catch((error) => {
    console.log("Error getting documents: ", error)
})




/* ----- For Normal Report Counters ----- */

db.collection("Normal Reports").get().then((querySnapshot) => {
    querySnapshot.forEach((normalReports) => {

        if (normalReports.data()['Report Category'] == "Construction Issues") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_CICount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_CICount++
                totalRRCount++
            }
            total_CICount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Covid Lockdown Violation") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_CLVCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_CLVCount++
                totalRRCount++
            }
            total_CLVCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Destruction of Property") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_DoPCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_DoPCount++
                totalRRCount++
            }
            total_DoPCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Disturbance") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_DistCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_DistCount++
                totalRRCount++
            }
            total_DistCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Engineering") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_EngiCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_EngiCount++
                totalRRCount++
            }
            total_EngiCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Informal Settlers") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_ISCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_ISCount++
                totalRRCount++
            }
            total_ISCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Stray Animals") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_SACount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_SACount++
                totalRRCount++
            }
            total_SACount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Theft") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_TheftCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_TheftCount++
                totalRRCount++
            }
            total_TheftCount++
            totalreportCount++
        }
        if (normalReports.data()['Report Category'] == "Waste Management") {
            if (normalReports.data()['Report Status'] == "Pending") {
                pending_WMCount++
                totalURCount++
            }
            if (normalReports.data()['Report Status'] == "Resolved") {
                resolved_WMCount++
                totalRRCount++
            }
            total_WMCount++
            totalreportCount++
        }

    })
})
.then(() => {
    
    $('#NR-CI-pending').html(pending_CICount)
    $('#NR-CLV-pending').html(pending_CLVCount)
    $('#NR-DoP-pending').html(pending_DoPCount)
    $('#NR-Dist-pending').html(pending_DistCount)
    $('#NR-Engi-pending').html(pending_EngiCount)
    $('#NR-IS-pending').html(pending_ISCount)
    $('#NR-SA-pending').html(pending_SACount)
    $('#NR-Theft-pending').html(pending_TheftCount)
    $('#NR-WM-pending').html(pending_WMCount)

    $('#NR-CI-resolved').html(resolved_CICount)
    $('#NR-CLV-resolved').html(resolved_CLVCount)
    $('#NR-DoP-resolved').html(resolved_DoPCount)
    $('#NR-Dist-resolved').html(resolved_DistCount)
    $('#NR-Engi-resolved').html(resolved_EngiCount)
    $('#NR-IS-resolved').html(resolved_ISCount)
    $('#NR-SA-resolved').html(resolved_SACount)
    $('#NR-Theft-resolved').html(resolved_TheftCount)
    $('#NR-WM-resolved').html(resolved_WMCount)
    
    $('#NR-CI-total').html(total_CICount)
    $('#NR-CLV-total').html(total_CLVCount)
    $('#NR-DoP-total').html(total_DoPCount)
    $('#NR-Dist-total').html(total_DistCount)
    $('#NR-Engi-total').html(total_EngiCount)
    $('#NR-IS-total').html(total_ISCount)
    $('#NR-SA-total').html(total_SACount)
    $('#NR-Theft-total').html(total_TheftCount)
    $('#NR-WM-total').html(total_WMCount)


    
    $('#totalUnresolvedReports').html(`Total Unresolved Reports: ${totalURCount}`)
    $('#totalResolvedReports').html(`Total Resolved Reports: ${totalRRCount}`)
    
    
})
.then(()=>{
    $('#totalReportsMade').html(`Total Reports Made: ${totalreportCount}`)
})

.catch((error) => {
    console.log("Error getting documents: ", error)
})

/*--- For User Counting ---*/
db.collection("Users").get().then((querySnapshot) => {
    querySnapshot.forEach((emergencyReports) => {
        totalUsersCount++
    })
})
.then(() => {
    console.log("totalUsersCount: " + totalUsersCount)
    $('#numberOfUsers').html(`Number of Users: ${totalUsersCount}`)
})
.catch((error) => {
    console.log("Error getting documents: ", error)
})





// db.collection("Emergency Reports").get().then((querySnapshot) => {
//     querySnapshot.forEach((emergencyReports) => {
//         let rowArray = []
//         rowArray.push(emergencyReports.data()['Report Category'])
//         rowArray.push(emergencyReports.data()['Report Status'])
//         rowArray.push(emergencyReports.data()['Report Location'])
//         rowArray.push(emergencyReports.data()['DATE'])
 
//         emergencyReportsData.push(rowArray)
//     })
// })
// .then(() => {
//     $(document).ready(function () {
//         $('#statTable').DataTable({
//             data: emergencyReportsData,
//             columns: [
//                 { title: "Report Categories" },
//                 { title: "Pending" },
//                 { title: "Resolved" },
//                 { title: "Total" }
//             ]
//         });
//     });
// })
// .catch((error) => {
//     console.log("Error getting emergency reports documents: ", error)
// })





