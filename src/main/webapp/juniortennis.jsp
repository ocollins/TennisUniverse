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

<title>Junior Tennis</title>
<link href="css/juniortennis.css" rel="stylesheet" type="text/css"/>

<body>
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

<div id="container2">

    <div id="info_div">
        <p id="club_info_p">Our objective is to help juniors develop their skills and begin
            competing as early as possible. <strong>Tennis Universe</strong> has earned a reputation
            for helping develop some of the best players in the state. We also put great effort into
            improving the play of our recreational juniors who get an early start in the game
            through the <a href="http://www.10andundertennis.com/">10 and under program</a>, junior lessons, and leagues.
        </p>
        <h2>Junior Programs</h2>
        <div id="lessons_div">
            <ul id="intro">
                <li><strong>Junior Group Lessons</strong><br/>
                    Group lessons for junior members of all levels and ages are available after school
                    and on weekends.  We maintain a student to instructor ratio of no more than 6:1,
                    which allows our staff to give plenty of attention to each player on his or her
                    court.
                </li>
                <br/>
                <li><strong>Junior Leagues</strong><br/>
                    Junior league play is offered for those junior members who wish to augment their
                    group lessons with singles and doubles match play. Competitive play is the best
                    way for players to improve their game. Leagues are available all year long.
                    Winners and runner-ups of each league receive trophies.​​​&</li>*
                <br/>
                <li><strong>Junior Interclubs</strong><br/>
                    A great way to create more matches for our juniors,
                    as well as to build camaraderie, is to play inter-club
                    matches against clubs from around the state. We try
                    to host or travel to another club on a monthly basis,
                    and if we can't find an opponent, then we'll schedule
                    an intra-club where we break our own kids into two
                    groups and play against each other. Either way,
                    it's always good competition and a fun time!​</li>
            </ul>
        </div>

    </div>

    <div id="info_div2">
        <img src="images/kidstennis2.jpg" alt="Kids Tennis" id="people_image" class="info_div"></img>
        <p id="lessons_p">Please call front desk to book!</p>
        <%--<div id="phones_div">--%>
            <%--<ul>--%>
                <%--<li><strong>Dale Smith</strong> (889)677-9087</li>--%>
                <%--<li><strong>Martha Smith </strong>(889)785-0123</li>--%>
                <%--<li><strong>Tedd DePrevost </strong>(889)445-9045</li>--%>
                <%--<li><strong>Tom Rubenstein </strong>(889)334-8945</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    </div>


</div>


<c:import url="footer.jsp"/>
</body>

</html>
