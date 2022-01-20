/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $(".hamburg-menu").click(function () {
        $(this).toggleClass("fa-times");
        $(".center-nav").toggleClass("active");
        $(".right-nav").toggleClass("active hidenow");
    });
});


$(document).ready(function () {
    $(".hamburg-menu-dash").click(function () {
        $(".sidebar-container").toggleClass("make-small");
    });
});
