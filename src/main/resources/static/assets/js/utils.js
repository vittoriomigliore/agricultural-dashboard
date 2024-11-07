function generateChartLabels(dateTimes) {
    let dateObjects = dateTimes.map((t) => new Date(Date.parse(t)));
    const allSameDay = dateObjects.every((date, _, arr) => {
        const firstDate = arr[0];
        return date.getDate() === firstDate.getDate() &&
            date.getMonth() === firstDate.getMonth() &&
            date.getFullYear() === firstDate.getFullYear();
    });
    return dateObjects.map((t) => {
        const formattedDate = `${t.getDate()}-${t.getMonth() + 1}-${t.getFullYear()}`;
        const formattedTime = `${t.getHours()}:${t.getMinutes()}`;
        if (!allSameDay) {
            return `${formattedDate} ${formattedTime}`
        }
        return formattedTime
    })
}

function getWebSocketUrl(path) {
    const protocol = window.location.protocol === 'https:' ? 'wss' : 'ws';
    const host = window.location.host;
    return `${protocol}://${host}/${path}`;
}

function formatNumber(num) {
    if (num >= 1e12) return (num / 1e12).toFixed(1).replace(/\.0$/, '') + 'T'; // Trillions
    if (num >= 1e9) return (num / 1e9).toFixed(1).replace(/\.0$/, '') + 'B';  // Billions
    if (num >= 1e6) return (num / 1e6).toFixed(1).replace(/\.0$/, '') + 'M';  // Millions
    if (num >= 1e3) return (num / 1e3).toFixed(1).replace(/\.0$/, '') + 'K';  // Thousands
    return num.toString(); // Less than 1,000
}