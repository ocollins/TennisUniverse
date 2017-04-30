<%--
  This is a page with fitness info and calories burned calculator
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link href="css/fitness.css" rel="stylesheet" type="text/css"/>
    <title>Fitness</title>
</head>


<body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>

<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"></c:import>

<c:import url="menu.jsp"></c:import>

<div id="pictures_div_container">
        <img src="images/fitness1.jpg" alt="Fitness Picture" class="tennis_fit_image"></img>
        <img src="images/fitness2.jpg" alt="Fitness Picture" class="tennis_fit_image"></img>

</div>
<div id="fitness_info">
    <ul>
        <li>Our Fitness Center sets a lofty standard in terms of equipment and services. The facility
        includes, all the cardiovascular & strength training equipment needed to lead an active lifestyle.
        </li>
        <li>
        INNOVATIVE SPORTS MEDICINE & PHYSICAL FITNESS TRAINING
        In addition to these state-of-the-art amenities, what truly sets Tennis Universe Tennis Club’'s Fitness
        Center apart from other facilities are cutting-edge fitness evaluations and physical training programs
        designed specifically for each members’ needs.
        </li>
        Kelly Painter, who has 15 years of  experience as a
        Certified Personal Trainer, is consulting with Tennis Universe Tennis Club to create programs that
        address a variety of goals which include— losing weight, enhancing flexibility, gaining strength/muscle
        tone and increasing physical performance (speed, power, recovery, endurance, agility, etc.) for your
        tennis game. These customized programs are based on an in-depth, no-fee consultation.
        <li>
        Consultations
        include a review of health and fitness history, creation of health and fitness goals and a customized
        sample training session.
        </li>
        <li>
        To learn more about a consultation and programs please call the front desk to schedule a
        complimentary appointment.
        </li>

    </ul>
</div>

<div id="calories_calculator_page">
    <c:import url="calories_calculator.jsp"></c:import>
</div>





<c:import url="footer.jsp"/>
</body>

</html>
