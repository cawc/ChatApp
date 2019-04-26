let getStatusRequest = new XMLHttpRequest();
let setStatusRequest = new XMLHttpRequest();

function getStatus() {
    getStatusRequest.open("GET", "Controller?action=GetStatus");
    getStatusRequest.onreadystatechange = getStatusData;
    getStatusRequest.send(null);
}

function getStatusData() {
    if (getStatusRequest.readyState === 4) {
        if (getStatusRequest.status === 200) {
            let serverResponse = JSON.parse(getStatusRequest.responseText);
            let statusText = 'Your status: ' + serverResponse.status;

            let statusP = document.getElementById("statusp");
            let statusTextNode = document.createTextNode(statusText);

            while (statusP.childNodes.length > 0)
                statusP.removeChild(statusP.childNodes[0]);
            statusP.appendChild(statusTextNode);
        }
    }
}

function setStatus() {
    let statusText = document.getElementById('statusInput').value;
    let info = 'status='+encodeURIComponent(statusText);
    setStatusRequest.open("POST","Controller?action=SetStatus");
    setStatusRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    setStatusRequest.send(info);
    setTimeout(getStatus,200); //delay to compensate for server processing time of new status
}


let getStatusButton = document.getElementById("getStatusButton");
let setStatusButton = document.getElementById("setStatusButton");
getStatusButton.onclick = getStatus;
setStatusButton.onclick = setStatus;
getStatus();
console.debug('Loaded status.js');


