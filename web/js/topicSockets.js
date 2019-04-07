let webSocket;
let messages = document.getElementById("messages");

function openSocket(){
    webSocket = new WebSocket("ws://localhost:8080/ChatApp/topics");
    webSocket.onopen = (event) => {
        console.debug("Connection opened");
    };
    webSocket.onmessage = (event) => {
        writeResponse(event.data);
    };
    webSocket.onclose = (event) => {
        console.debug("Connection closed");
    };
}
function writeResponse(data){
    let jsondata = JSON.parse(data);
    let topic = jsondata.topic;
    let name = jsondata.name;
    let rating = jsondata.rating;
    let content = jsondata.content;

    let topicReplybox = document.getElementById("topic"+topic);
    topicReplybox.innerText += "(" + name + ") " + rating + "/10: " + content;
    topicReplybox.innerHTML += "<br/>";
}

function submitComment() {
    let topicReply = {};
    topicReply.topic = document.getElementById("topic").value;
    topicReply.name = document.getElementById("name").value;
    topicReply.content = document.getElementById("content").value;
    topicReply.rating = document.getElementById("rating").value;

    webSocket.send(JSON.stringify(topicReply));
}

openSocket();