/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    var msg = $(".ToastMsg").val();
    var notificationMarkup = "<div class='notification'><div class='msg'><div class='icon'>" +
            "<i class='fas fa-exclamation-circle circle-i'></i></div><div class='message'> " + msg +
            "</div></div><div class='cross'><i class='fas fa-times closeToast'></i></div></div>";
    if (msg !== undefined) {
        $(".toast").append(notificationMarkup);
    }

//Closes the notification toast when clicked on the cross sign.
    $(".closeToast").click(function () {
        $(".toast").addClass("animate__animated animate__slideOutLeft");
        empty();
    });

//Closes the notification toast automatically in 3 seconds.
    setTimeout(function () {
        $(".toast").addClass("animate__animated animate__slideOutLeft");
        empty();
    }, 3000);

//Clears the content of the notification toast on after 3.2s on either of the above condition.
    setTimeout(function empty() {
        $(".toast").empty();
    }, 3200);

});