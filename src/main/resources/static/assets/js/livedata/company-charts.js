const companyCharts = {
    cropProduction: new Chart(document.getElementById('crop-production'), {
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
                yAxes: [{
                    ticks: {
                        callback: function(value, index, values) {
                            return '€' + formatNumber(value);
                        }
                    }
                }]
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        const transformedValue = formatNumber(tooltipItem.yLabel);
                        return data.datasets[tooltipItem.datasetIndex].label + ': €' + transformedValue;
                    }
                }
            }
        }
    }),
    productionVsSales: new Chart(document.getElementById('sales'), {
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
            },
            scales: {
                yAxes: [{
                    ticks: {
                        callback: function(value, index, values) {
                            return '€' + formatNumber(value);
                        }
                    }
                }]
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        const transformedValue = formatNumber(tooltipItem.yLabel);
                        return data.datasets[tooltipItem.datasetIndex].label + ': €' + transformedValue;
                    }
                }
            }
        }
    }),
    cropCost: new Chart(document.getElementById('costs'), {
        type: 'line',
        data: {
            labels: [],
            datasets: [],
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top'
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        callback: function(value, index, values) {
                            return '€' + formatNumber(value);
                        }
                    }
                }]
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        const transformedValue = formatNumber(tooltipItem.yLabel);
                        return data.datasets[tooltipItem.datasetIndex].label + ': €' + transformedValue;
                    }
                }
            }
        }
    })
}

function updateCompanyCharts(resCharts) {
    var resCropProduction = resCharts.find((c) => c.chartType === 'crop-production');
    companyCharts.cropProduction.data.labels = convertDateTimesToChartLabels(resCropProduction.dateTimes, 'day');
    companyCharts.cropProduction.data.datasets = [];
    resCropProduction.datasets.forEach((dataset) => {
        companyCharts.cropProduction.data.datasets.push({
            label: dataset.label,
            data: dataset.data,
            backgroundColor: `rgba(${dataset.backgroundColor.r}, ${dataset.backgroundColor.g}, ${dataset.backgroundColor.b}, 0.8)`
        })
    })
    companyCharts.cropProduction.update();

    var resProductionVsSales = resCharts.find((c) => c.chartType === 'production-vs-sales');
    companyCharts.productionVsSales.data.labels = convertDateTimesToChartLabels(resProductionVsSales.dateTimes, 'month');
    companyCharts.productionVsSales.data.datasets = [];
    resProductionVsSales.datasets.forEach((dataset) => {
        companyCharts.productionVsSales.data.datasets.push({
            label: dataset.label,
            data: dataset.data,
            backgroundColor: `rgba(${dataset.backgroundColor.r}, ${dataset.backgroundColor.g}, ${dataset.backgroundColor.b}, 0.8)`
        })
    })
    companyCharts.productionVsSales.update();

    var resCropCosts = resCharts.find((c) => c.chartType === 'crop-cost');
    companyCharts.cropCost.data.labels = convertDateTimesToChartLabels(resCropCosts.dateTimes, 'month');
    companyCharts.cropCost.data.datasets = [];
    console.log(resCropCosts)
    resCropCosts.datasets.forEach((dataset) => {
        companyCharts.cropCost.data.datasets.push({
            label: dataset.label,
            data: dataset.data,
            backgroundColor: `rgba(${dataset.backgroundColor.r}, ${dataset.backgroundColor.g}, ${dataset.backgroundColor.b}, 0.2)`,
            borderColor: `rgba(${dataset.backgroundColor.r}, ${dataset.backgroundColor.g}, ${dataset.backgroundColor.b}, 1)`,
            tension: 0.3
        })
    })
    companyCharts.cropCost.update();
}