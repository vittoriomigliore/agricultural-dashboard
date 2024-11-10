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
                        label: '',
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
                        label: '',
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
                        label: '',
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
                        label: '',
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
                        label: '',
                        data: [],
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        fill: true
                    }]
                },
            })
        }
    }
})

function updateFieldCharts(resFields) {
    resFields.forEach((resField) => {
        const fieldChart = fieldCharts.find((fieldObject) => fieldObject.id === resField.field);

        let resTemperatures = resField.charts.find((c) => c.chartType === 'temperature');
        fieldChart.charts.temperature.data.labels = convertDateTimesToChartLabels(resTemperatures.dateTimes, 'minute');
        fieldChart.charts.temperature.data.datasets[0].label = resTemperatures.dataset.label
        fieldChart.charts.temperature.data.datasets[0].data = resTemperatures.dataset.data;
        fieldChart.charts.temperature.update();

        let resHumidities = resField.charts.find((c) => c.chartType === 'humidity');
        fieldChart.charts.humidity.data.labels = convertDateTimesToChartLabels(resHumidities.dateTimes, 'minute');
        fieldChart.charts.humidity.data.datasets[0].label = resHumidities.dataset.label
        fieldChart.charts.humidity.data.datasets[0].data = resHumidities.dataset.data;
        fieldChart.charts.humidity.update();

        let resPrecipitations = resField.charts.find((c) => c.chartType === 'precipitation');
        fieldChart.charts.precipitation.data.labels = convertDateTimesToChartLabels(resPrecipitations.dateTimes, 'minute');
        fieldChart.charts.precipitation.data.datasets[0].label = resPrecipitations.dataset.label
        fieldChart.charts.precipitation.data.datasets[0].data = resPrecipitations.dataset.data;
        fieldChart.charts.precipitation.update();

        let resWindSpeeds = resField.charts.find((c) => c.chartType === 'windSpeed');
        fieldChart.charts.windSpeed.data.labels = convertDateTimesToChartLabels(resWindSpeeds.dateTimes, 'minute');
        fieldChart.charts.windSpeed.data.datasets[0].label = resWindSpeeds.dataset.label
        fieldChart.charts.windSpeed.data.datasets[0].data = resWindSpeeds.dataset.data;
        fieldChart.charts.windSpeed.update();

        let resIrrigations = resField.charts.find((c) => c.chartType === 'irrigation');
        fieldChart.charts.irrigation.data.labels = convertDateTimesToChartLabels(resIrrigations.dateTimes, 'minute');
        fieldChart.charts.irrigation.data.datasets[0].label = resIrrigations.dataset.label
        fieldChart.charts.irrigation.data.datasets[0].data = resIrrigations.dataset.data;
        fieldChart.charts.irrigation.update();
    })
}