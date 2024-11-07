const counters = [{
    element: document.getElementById('counter-costs'),
    type: 'cost'
}, {
    element: document.getElementById('counter-income'),
    type: 'sales'
}, {
    element: document.getElementById('counter-today-income'),
    type: 'today-sales'
}]

let wsUrlCounters = getWebSocketUrl("counters-live-data");

const socketCounters = new WebSocket(wsUrlCounters)

socketCounters.onmessage = function (event) {
    const resCounters = JSON.parse(event.data);
    counters.forEach((counter) => {
        let resCounter = resCounters.find((c) => c.type === counter.type);
        if (resCounter !== undefined) {
            counter.element.getElementsByClassName('counter-value')[0].textContent = resCounter.value;
            counter.element.getElementsByClassName('counter-change')[0].textContent = resCounter.change;
            counter.element.getElementsByClassName('progress-bar')[0].style.width = resCounter.change + "%";
            counter.element.getElementsByClassName('progress-bar')[0].setAttribute('aria-valuenow', resCounter.change);
        }
    })
}