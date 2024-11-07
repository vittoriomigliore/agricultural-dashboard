const fieldCharts = fieldIds.map((fieldId, index) => {
    const idHtml = "field" + index
    return {
        id: fieldId,
        charts: {
            temperature: new Chart(document.getElementById('temperature-' + idHtml), {
                type: 'line',
                data: {
                    labels: [],
                    datasets: [{
                        label: 'Temperature (°C)',
                        data: [],
                        borderColor: 'rgba(75, 192, 192, 1)',
                        fill: false
                    }]
                }
            }),
            humidity: new Chart(document.getElementById('humidity-' + idHtml), {
                type: 'line',
                data: {
                    labels: [],
                    datasets: [{
                        label: 'Humidity (%)',
                        data: [],
                        borderColor: 'rgba(54, 162, 235, 1)',
                        fill: false
                    }]
                }
            }),
            precipitation: new Chart(document.getElementById('precipitation-' + idHtml), {
                type: 'bar',
                data: {
                    labels: [],
                    datasets: [{
                        label: 'Precipitation (mm)',
                        data: [],
                        backgroundColor: 'rgba(153, 102, 255, 0.6)'
                    }]
                }
            }),
            windSpeed: new Chart(document.getElementById('windSpeed-' + idHtml), {
                type: 'line',
                data: {
                    labels: [],
                    datasets: [{
                        label: 'Wind Speed (km/h)',
                        data: [],
                        borderColor: 'rgba(255, 206, 86, 1)',
                        fill: false
                    }]
                }
            }),
            irrigation: new Chart(document.getElementById('irrigation-' + idHtml), {
                type: 'line',
                data: {
                    labels: [],
                    datasets: [{
                        label: 'Irrigation Volume (L)',
                        data: [100, 90, 80, 70, 50, 30, 20, 25, 45, 70, 85, 95],
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        fill: true
                    }]
                },
                // options: {
                //     responsive: true,
                //     maintainAspectRatio: false,
                //     scales: {
                //         yAxes: [{
                //             ticks: {
                //                 beginAtZero: true
                //             }
                //         }]
                //     }
                // }
            })
        }
    }
})

let wsUrlCharts = getWebSocketUrl("charts-live-data");

const socketCharts = new WebSocket(wsUrlCharts)

socketCharts.onmessage = function (event) {
    const resFields = JSON.parse(event.data);
    resFields.forEach((resField) => {
        const fieldChart = fieldCharts.find((fieldObject) => fieldObject.id === resField.field);

        let resTemperatures = resField.charts.find((c) => c.chartType === 'temperature');
        fieldChart.charts.temperature.data.labels = generateChartLabels(resTemperatures.dateTimes);
        fieldChart.charts.temperature.data.datasets[0].data = resTemperatures.data;
        fieldChart.charts.temperature.update();

        let resHumidities = resField.charts.find((c) => c.chartType === 'humidity');
        fieldChart.charts.humidity.data.labels = generateChartLabels(resHumidities.dateTimes);
        fieldChart.charts.humidity.data.datasets[0].data = resHumidities.data;
        fieldChart.charts.humidity.update();

        let resPrecipitations = resField.charts.find((c) => c.chartType === 'precipitation');
        fieldChart.charts.precipitation.data.labels = generateChartLabels(resPrecipitations.dateTimes);
        fieldChart.charts.precipitation.data.datasets[0].data = resPrecipitations.data;
        fieldChart.charts.precipitation.update();

        let resWindSpeeds = resField.charts.find((c) => c.chartType === 'windSpeed');
        fieldChart.charts.windSpeed.data.labels = generateChartLabels(resWindSpeeds.dateTimes);
        fieldChart.charts.windSpeed.data.datasets[0].data = resWindSpeeds.data;
        fieldChart.charts.windSpeed.update();
    })
}