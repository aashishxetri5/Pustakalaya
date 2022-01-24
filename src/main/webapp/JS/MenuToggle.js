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


$(document).ready(function () {
    $(".accordion-feedback").click(function () {
        $(this).toggleClass("active-fd");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
});