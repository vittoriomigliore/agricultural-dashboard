const companyCharts = {
    production: new Chart(document.getElementById('production'), {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
            },
            scales: {
                x: {
                    stacked: true,
                },
                y: {
                    stacked: true,
                    beginAtZero: true
                },
            },
        }
    }),
    sales: new Chart(document.getElementById('sales'), {
        type: 'bar',
        data: {
            labels: [],
            datasets: [
                {
                    label: 'Production Quantity',
                    data: [],
                    type: 'bar',
                    backgroundColor: 'rgba(75, 192, 192, 0.8)',
                },
                {
                    label: 'Sales Quantity',
                    data: [],
                    type: 'line',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    tension: 0.3,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: 'Production vs. Sales Over Time',
                },
            }
        }
    }),
    costs: new Chart(document.getElementById('costs'), {
        type: 'line',
        data: {
            labels: [],
            datasets: [
                {
                    label: '',
                    data: [],
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    tension: 0.3,
                },
                {
                    label: '',
                    data: [900, 850, 800, 950],
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    tension: 0.3,
                },
                {
                    label: '',
                    data: [1100, 1150, 1200, 1180],
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    tension: 0.3,
                },
                {
                    label: '',
                    data: [700, 750, 720, 780],
                    borderColor: 'rgba(255, 206, 86, 1)',
                    backgroundColor: 'rgba(255, 206, 86, 0.2)',
                    tension: 0.3,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: 'Cost Trends per Crop',
                },
            },
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Cost (Currency)',
                    },
                },
                x: {
                    title: {
                        display: true,
                        text: 'Time Period',
                    },
                },
            },
        }
    })
}

function updateCompanyCharts(resCharts) {
    var resProduction = resCharts.find((c) => c.chartType === 'production');
    companyCharts.production.data.labels = convertDateTimesToChartLabels(resProduction.dateTimes, 'month');
    companyCharts.production.data.datasets = [];
    resProduction.datasets.forEach((dataset) => {
        companyCharts.production.data.datasets.push({
            label: dataset.label,
            data: dataset.data,
            backgroundColor: `rgba(${dataset.backgroundColor.r}, ${dataset.backgroundColor.g}, ${dataset.backgroundColor.b}, 0.8)`
        })
    })
    companyCharts.production.update();
}