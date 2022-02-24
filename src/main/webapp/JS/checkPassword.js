/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $("#changePass").submit(function (event) {
        var password = $("#newP").val();
        var rePassword = $("#re-NewPass").val();

        if (password !== rePassword) {
            $("#errmsg").text(' Passswords do not match!!!');
            $("#errmsg").addClass('fas fa-exclamation-triangle');
//            event.preventDefault();
        }
    });
});