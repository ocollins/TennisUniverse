/**
 * Fitness
 */
var display_calculator = false;

$(document).ready(function() {

    $("#calories_button").click(function (event) {
        $("#calories_calculator_page").css("display", "block");
        display_calculator = true;
        $("#fitness_info").css("display", "none");
        $("#pictures_div_container").css("display", "none");

    });



});

$(document).ready(function() {

    if(display_calculator) {
        $("#calories_calculator_page").css("display", "block");
    }

});