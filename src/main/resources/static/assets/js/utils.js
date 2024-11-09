function convertDateTimesToChartLabels(dateTimes, precision) {
    return dateTimes.map((t) => {
        return formatDateForChart(new Date(Date.parse(t)), precision)
    })
}

function getWebSocketUrl(path) {
    const protocol = window.location.protocol === 'https:' ? 'wss' : 'ws';
    const host = window.location.host;
    return `${protocol}://${host}/${path}`;
}

function formatDateForChart(date, precision) {
    // Create a new Date object from the input to ensure it's properly formatted
    const d = new Date(date);

    // Format the date string based on the precision
    switch (precision) {
        case 'minute':
            // British English style, e.g., "14:23" for 2:23 PM
            return d.toLocaleTimeString('en-GB', {hour: '2-digit', minute: '2-digit'});

        case 'hour':
            // Only the hour, e.g., "14" for 2 PM
            return d.toLocaleTimeString('en-GB', {hour: '2-digit'});

        case 'day':
            // British format, e.g., "09 Nov" for 9th November
            return d.toLocaleDateString('en-GB', {day: '2-digit', month: 'short'});

        case 'month':
            // British format, e.g., "Nov" for November without the year
            return d.toLocaleDateString('en-GB', {month: 'short'});

        case 'year':
            // Just the year, e.g., "2024"
            return d.getFullYear().toString();

        default:
            // Default to British English full date format, e.g., "09/11/2024" for 9th Nov 2024
            return d.toLocaleDateString('en-GB');
    }
}

function formatNumber(num) {
    if (num >= 1e12) return (num / 1e12).toFixed(1).replace(/\.0$/, '') + 'T'; // Trillions
    if (num >= 1e9) return (num / 1e9).toFixed(1).replace(/\.0$/, '') + 'B';  // Billions
    if (num >= 1e6) return (num / 1e6).toFixed(1).replace(/\.0$/, '') + 'M';  // Millions
    if (num >= 1e3) return (num / 1e3).toFixed(1).replace(/\.0$/, '') + 'K';  // Thousands
    return num.toString(); // Less than 1,000
}