let getFriendlistRequest = new XMLHttpRequest();

function getFriendlist() {
    getStatusRequest.open("GET", "Controller?action=GetFriends");
    getStatusRequest.onreadystatechange = getFriendlistData;
    getStatusRequest.send(null);
}

function getFriendlistData() {
    if (getStatusRequest.readyState === 4) {
        if (getStatusRequest.status === 200) {
            let serverResponse = JSON.parse(getStatusRequest.responseText);
            console.debug(serverResponse);
        }
    }
}

