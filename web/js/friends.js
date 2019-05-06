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
        let chatLink = document.createElement("a");
        chatLink.appendChild(document.createTextNode(fr.name));
        chatLink.setAttribute("href", "Controller?action=ChatRoom&uid="+fr.userId);
        nameCell.appendChild(chatLink);
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

let addFriendButton = document.getElementById("addFriendButton");
addFriendButton.onclick = addFriend;
getFriendlist();
setInterval(getFriendlist, 3000);
console.debug('Loaded friends.js');
