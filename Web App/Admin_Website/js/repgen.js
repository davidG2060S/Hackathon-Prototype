  //ECounts
  let aiaCount = 0
  let agpCount = 0
  let aVCount = 0
  let baeCount = 0
  let faCount = 0
  let fireCount = 0
  let medCount = 0
  let rtaCount = 0
  let sriCount = 0
  let strandedCount = 0
  let tresspassCount = 0

  var db = firebase.firestore()
  db.collection("Emergency Reports").get().then((querySnapshot) => {
    querySnapshot.forEach((emergencyReports) => {

      if (emergencyReports.data()['Report Category'] == "Accident involving Animals") {
        aiaCount++
      }
      if (emergencyReports.data()['Report Category'] == "Actions against Police") {
        agpCount++
      }
      if (emergencyReports.data()['Report Category'] == "Assault/Violence") {
        aVCount++
      }
      if (emergencyReports.data()['Report Category'] == "Breaking and Entering") {
        baeCount++
      }
      if (emergencyReports.data()['Report Category'] == "Fatal Accident") {
        faCount++
      }
      if (emergencyReports.data()['Report Category'] == "Fire") {
        fireCount++
      }
      if (emergencyReports.data()['Report Category'] == "Medical") {
        medCount++
      }
      if (emergencyReports.data()['Report Category'] == "Road Traffic Accident") {
        rtaCount++
      }
      if (emergencyReports.data()['Report Category'] == "Sports Related Injuries") {
        sriCount++
      }
      if (emergencyReports.data()['Report Category'] == "Stranded (Flood)") {
        strandedCount++
      }
      if (emergencyReports.data()['Report Category'] == "Trespassing") {
        tresspassCount++
      }

    })
  })
    .then(() => {
      console.log(aiaCount)
      console.log(agpCount)
      console.log(aVCount)
      console.log(baeCount)
      console.log(faCount)
      console.log(fireCount)
      console.log(medCount)
      console.log(rtaCount)
      console.log(sriCount)
      console.log(strandedCount)
      console.log(tresspassCount)

      let topReports = document.getElementById('topReports').getContext('2d');

      let lineChart = new Chart(topReports, {
        type: 'bar',
        data: {
          labels: ['Accident involving Animals', 'Actions against Police', 'Assault/Violence', 'Breaking and Entering',
           'Fatal Accident','Fire', 'Medical', 'Road Traffic Accident', 'Sports Related Injuries', 'Stranded (Flood)',
          'Trespassing'],
          datasets: [{
            label: 'Top Emergency Reports',
            backgroundColor: ["#22EBAB"],
            data: [
              aiaCount,
              agpCount,
              aVCount,
              baeCount,
              faCount,
              fireCount,
              medCount,
              rtaCount,
              sriCount,
              strandedCount,
              tresspassCount
            ]
          }]
        },
        options: {}
      });
    })
    .catch((error) => {
      console.log("Error getting documents: ", error)
    })

  //NCounts
  let bpCount = 0
  let ciCount = 0
  let clvCount = 0
  let dopCount = 0
  let disturbanceCount = 0
  let eCount = 0
  let isCount = 0
  let saCount = 0
  let theftCount = 0
  let wmCount = 0

  var db = firebase.firestore()
  db.collection("Normal Reports").get().then((querySnapshot) => {
    querySnapshot.forEach((normalReports) => {

      if (normalReports.data()['Report Category'] == "Construction Issues") {
        ciCount++
      }
      if (normalReports.data()['Report Category'] == "Covid Lockdown Violation") {
        clvCount++
      }
      if (normalReports.data()['Report Category'] == "Destruction of Property") {
        dopCount++
      }
      if (normalReports.data()['Report Category'] == "Disturbance") {
        disturbanceCount++
      }
      if (normalReports.data()['Report Category'] == "Engineering") {
        eCount++
      }
      if (normalReports.data()['Report Category'] == "Informal Settlers") {
        isCount++
      }
      if (normalReports.data()['Report Category'] == "Stray Animals") {
        saCount++
      }
      if (normalReports.data()['Report Category'] == "Theft") {
        theftCount++
      }
      if (normalReports.data()['Report Category'] == "Waste Management") {
        wmCount++
      }
    })
  })
    .then(() => {
      console.log(bpCount)
      console.log(ciCount)
      console.log(clvCount)
      console.log(dopCount)
      console.log(disturbanceCount)
      console.log(eCount)
      console.log(saCount)
      console.log(theftCount)
      console.log(wmCount)
      

      let topReports = document.getElementById('topNReports').getContext('2d');

      let lineChart = new Chart(topNReports, {
        type: 'bar',
        data: {
          labels: ['Construction Issues', 'Covid Lockdown Violation', 'Destruction of Property',
           'Disturbance','Engineering', 'Informal Settlers','Stray Animals', 'Theft', 'Waste Management'],
          datasets: [{
            label: 'Top Normal Reports',
            backgroundColor: ["#22EBAB"],
            data: [
              ciCount,
              clvCount,
              dopCount,
              disturbanceCount,
              eCount,
              isCount,
              saCount,
              theftCount,
              wmCount
            ]
          }]
        },
        options: {}
      });
    })
    .catch((error) => {
      console.log("Error getting documents: ", error)
    })