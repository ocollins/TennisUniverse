<%--
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<title>Adult Tennis</title>
<link href="css/adulttennis.css" rel="stylesheet" type="text/css"/>

<body>
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

<div id="container2">

    <div class="info_div">
        <p id="club_info_p">Whether you are new to tennis, getting back into the game or a seasoned competitor,
            Tennis Universe Tennis Club offers a variety of lessons, clinics, social and competitive
            tennis programs, and events daily designed to challenge all age and ability levels. Not sure how
            to get started?  Our experienced staff can get you headed in the right direction.
        </p>
        <h2>Adult Programs</h2>
        <div id="lessons_div">
            <ul id="intro">
                <li><strong>Beginner Clinic</strong><br/>
                    This clinic is for the beginner or experienced beginner
                    who is looking for instruction in all facets of the game in a relaxed atmosphere.
                    Taught by Martha
                </li>
                <br/>
                <li><strong>Intermediate Clinic</strong><br/>
                    This clinic is for the intermediate players, 3.5 and above, who are
                    looking to improve their fundamentals of tennis through fun games and drills
                    in a relaxed atmosphere.
                    Taught by Tedd
                </li>
                <br/>
                <li><strong>Advanced Clinic</strong><br/>
                    This clinic is for the advanced players, 4.0 and above, who are
                    looking to take their game to the next level while getting a great workout
                    through play oriented games and fun drills.
                    Taught by Tom
                </li>
            </ul>
        </div>

    </div>

    <div class="info_div2">
        <img src="images/people.jpg" alt="Tennis People" id="people_image" class="info_div"></img>
        <p id="lessons_p">Lessons can be booked with our staff of world class tennis professionals by calling them directly</p>
        <div id="phones_div">
            <ul>
                <li><strong>Dale Smith</strong> (889)677-9087</li>
                <li><strong>Martha Smith </strong>(889)785-0123</li>
                <li><strong>Tedd DePrevost </strong>(889)445-9045</li>
                <li><strong>Tom Rubenstein </strong>(889)334-8945</li>
            </ul>
        </div>
    </div>


</div>


<c:import url="footer.jsp"/>
</body>

</html>
