const counters = [{
    element: document.getElementById('counter-costs'),
    type: 'cost'
}]

let wsUrlCounters = getWebSocketUrl("counters-live-data");

const socketCounters = new WebSocket(wsUrlCounters)

socketCounters.onopen = function (event) {
    console.log("WebSocket connection established");
};

socketCounters.onclose = function (event) {
    console.log("WebSocket connection closed");
};

socketCounters.onerror = function (error) {
    console.error("WebSocket error:", error);
};

socketCounters.onmessage = function (event) {
    const resCounters = JSON.parse(event.data);
    counters.forEach((counter) =>{
        let resCounter = resCounters.find((c) => c.type === counter.type);
        counter.element.getElementsByClassName('counter-value')[0].textContent = resCounter.value;
        counter.element.getElementsByClassName('counter-change')[0].textContent = resCounter.change;
        counter.element.getElementsByClassName('progress-bar')[0].style.width = resCounter.change + "%";
        counter.element.getElementsByClassName('progress-bar')[0].setAttribute('aria-valuenow', resCounter.change);
    })
}