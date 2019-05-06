let participants = [];
$("#participants li").each(function() {
    participants.push($(this).attr("id"));
});

$("#sendMessageButton").click(function() {
    let messageInput = $("#messageInput");
    $.post({
        url: "Controller?action=SendChat",
        data: {
            message: messageInput.val(),
            participants: participants
        }
    });
    messageInput.val("");
    update();
});

function update() {
    $.ajax({
        url: "Controller?action=GetChat",
        data: {
            participants: participants
        },
        success: (messages) => {
            $("#messages li").remove();
            for (let i = 0; i < messages.length; i++) {
                let message = messages[i];
                $("#messages").append($("<li></li>").text(message.author.firstName + " " + message.author.lastName + ": " + message.message));
            }
        }
    });
}

setInterval(update, 3000);
