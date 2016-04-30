/**
 * Created by kevin on 29/04/2016.
 */


document.addEventListener("DOMContentLoaded", function() {

    let globalIndex = 0;

    let subscriptions = [];

    let messages$ = Rx.DOM
        .fromEventSource('/sse')
        .map(v => JSON.parse(v))
        .do(() => console.log("from SSE"))
        .share()
        ;

    document.querySelector('#start').addEventListener('click', () => {
        fetch('/start')
    });

    let domConnections = document.querySelector('#connections');
    function showInDom() {

        while(domConnections.firstChild)
            domConnections.removeChild(domConnections.firstChild);

        subscriptions.forEach((c, i) => {
            let div = document.createElement("div");
            div.textContent = `Connection ${i}`;

            let buttonStopConnection = document.createElement('button');
            buttonStopConnection.textContent = 'Disconnect';
            buttonStopConnection.addEventListener('click', () => {
                c.dispose();
            });
            div.appendChild(buttonStopConnection);

            domConnections.appendChild(div);
        });
    }

    document.querySelector('#addSubscriber').addEventListener('click', () => {

        subscriptions.push(messages$.subscribe(v => {
            let index = globalIndex++;
            console.log(v);
            console.log(index);
        }));

        showInDom();
    });
});