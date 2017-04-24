/**
 * Created by O Collins on 4/23/17.
 * This will add some user password validation to the user registration page
 */

var validLength = false;
var validCap = false;
var validNumber = false;

$(document).ready(function() {
    $("#reg_new_user_button").click(function (event) {
        var pass1 = $("#pass_input1").val();
        var pass2 = $("#pass_input2").val();

        if ((validLength && validCap && validNumber)) {
            if(pass1 === pass2) {
                $( "#register_form" ).submit();
            } else {
                alert("Passwords do not match!");
                event.preventDefault();
                $(".data").val(" ");
            }
        } else {
            alert("Password does not meet required strength requirements");
            event.preventDefault();
            $(".data").val(" ");
        }
    })
});


$(document).ready(function() {

//Trigger an event on keyup, focus, or blur on the password field
    $('input[type=password]').keyup(function() {
        var pswd = $(this).val();
        //validate the length
        if ( pswd.length < 6 ) {
            $('#length').removeClass('valid').addClass('invalid');
            validLength = true;
        } else {
            $('#length').removeClass('invalid').addClass('valid');
        }

        //validate capital letter
        if ( pswd.match(/[A-Z]/) ) {
            $('#capital').removeClass('invalid').addClass('valid');
            validCap = true;
        } else {
            $('#capital').removeClass('valid').addClass('invalid');
        }

        //validate number
        if ( pswd.match(/\d/) ) {
            $('#number').removeClass('invalid').addClass('valid');
            validNumber = true;
        } else {
            $('#number').removeClass('valid').addClass('invalid');
        }

    }).focus(function() {
        $('#pswd_info').show();
    }).blur(function() {
        $('#pswd_info').hide();
    });


});