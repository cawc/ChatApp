let getStatusRequest = new XMLHttpRequest();
let setStatusRequest = new XMLHttpRequest();
let getFriendlistRequest = new XMLHttpRequest();
let addFriendRequest = new XMLHttpRequest();


function getFriendlist() {
    getFriendlistRequest.open("GET", "Controller?action=GetFriends");
    getFriendlistRequest.onreadystatechange = getFriendlistData;
    getFriendlistRequest.send(null);
}

function getFriendlistData() {
    if (getFriendlistRequest.readyState === 4) {
        if (getFriendlistRequest.status === 200) {
            let serverResponse = JSON.parse(getFriendlistRequest.responseText);
            generateFriendList(serverResponse);
        }
    }
}

function generateFriendList(friends) {
    let friendList = document.getElementById("friendList");
    let rowCount = friendList.rows.length;

    while(--rowCount) {
        friendList.deleteRow(rowCount);
    }

    friends.forEach((fr) => {
        let row = friendList.insertRow();
        let nameCell = row.insertCell(0);
        let statusCell = row.insertCell(1);
        nameCell.appendChild(document.createTextNode(fr.name));
        statusCell.appendChild(document.createTextNode(fr.status));
    });
    
}

function addFriend() {
    let friendId = document.getElementById("addFriendInput").value;
    let info = 'userid='+encodeURIComponent(friendId);
    addFriendRequest.open("POST", "Controller?action=AddFriend");
    addFriendRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    addFriendRequest.send(info);
    setTimeout(getFriendlist, 200);
}



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

window.addEventListener('load', () => {
        let getStatusButton = document.getElementById("getStatusButton");
        let setStatusButton = document.getElementById("setStatusButton");
        let addFriendButton = document.getElementById("addFriendButton");
        getStatusButton.onclick = getStatus;
        setStatusButton.onclick = setStatus;
        addFriendButton.onclick = addFriend;
        getStatus();
        getFriendlist();
        setInterval(getFriendlist, 3000);
        console.debug('Got initial status & friends list on window load');
    }
);


