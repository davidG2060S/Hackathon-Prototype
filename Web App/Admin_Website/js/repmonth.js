let January = 0
let February = 0
let March = 0
let April = 0
let May = 0
let June = 0
let July = 0
let August = 0
let September = 0
let October = 0
let November = 0
let December = 0

var db = firebase.firestore()
db.collection("Emergency Reports").get().then((querySnapshot) => {
  querySnapshot.forEach((emergencyReports) => {
    let reportDate = emergencyReports.data()['DATE'] // 7/23/2021
    let reportArrDate = reportDate.split('/')
    // [
    //   0 => 7,
    //   1 => 23,
    //   2 => 2021
    // ]
    if (reportArrDate[0] == 1) {
      January++
    }
    if (reportArrDate[0] == 2) {
      February++
    }
    if (reportArrDate[0] == 3) {
      March++
    }
    if (reportArrDate[0] == 4) {
      April++
    }
    if (reportArrDate[0] == 5) {
      May++
    }
    if (reportArrDate[0] == 6) {
      June++
    }
    if (reportArrDate[0] == 7) {
      July++
    }
    if (reportArrDate[0] == 8) {
      August++
    }
    if (reportArrDate[0] == 9) {
      September++
    }
    if (reportArrDate[0] == 10) {
      October++
    }
    if (reportArrDate[0] == 11) {
      November++
    }
    if (reportArrDate[0] == 12) {
      December++
    }
    
  })
})
  .then(() => {
    let reportsInAMonth = document.getElementById('reportsInAMonth').getContext('2d');

    let lineChart = new Chart(reportsInAMonth, {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May',
          'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [{
          label: 'Reports in a Month',
          backgroundColor: ["#22EBAB"],
          data: [
            January,
            February,
            March,
            April,
            May,
            June,
            July,
            August,
            September,
            October,
            November,
            December
          ]
        }]

      },
      options: {}
    });
  })
  .catch((error) => {
    console.log("Error getting emergency reports documents: ", error)
  })