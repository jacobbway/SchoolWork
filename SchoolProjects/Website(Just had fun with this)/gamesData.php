<!DOCTYPE html>
<html>
<head>
	<title>SQL queries to a madeup database</title>
	<link  rel="stylesheet" href="gamesData.css" type="text/css">

</head>

<body>
<script src="submittedPoll.js" type="text/javascript"></script>
	<div id="navBar"></div>

<h1>This Just exists to demonstrate sql queries to a fake database</h1>

	<?php 
	   /*
	   $servername = "sql40.freemysqlhosting.net"; //creating variable to access made up data base
	   $username = "gamersUnite";
	   $password = "poe2comesoutDec6th@1pmCST";
	   $database = "allgamesever";
	   
	   $conn = new mysqli($servername, $username, $password, $database); //attempting to connect to database
	   
	   if($conn->connect_error) { //checks to make sure if a connection is there if not it quits and outputs the error
	       die("Connection failed: " . $conn->connect_error);
	   }
	   
	   
	   $sql = "        //here is my sql query for the made up data base and its three tables
        SELECT
            Game.Name AS GameName,
            Publisher.Name AS PublisherName,
            Publisher.MarketValue,
            Sales.CopiesSold,
            Sales.PricePerCopy
        FROM
            Game
        INNER JOIN
            Publisher ON Game.PublisherID = Publisher.PublisherID
        INNER JOIN
            Sales ON Game.GameID = Sales.GameID
        WHERE
            Publisher.MarketValue > 5000000000; -- Example condition";
	   
	   $result = $conn->query($sql);
	   
	   if ($result->num_rows > 0) { //just setting the retieved data to their own variable which i could then use to easily display them somehow
	       while($row = $result->fetch_assoc()) {
	           $game = $row["GameName"];
	           $pubName = $row["PublsiherName"];
	           $markVal = $row["MarketValue"];
	           $copiesSold = $row["CopiesSold"];
	           $price = $row["PricePerCopy"];
	       }
	   }
	   */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	?>












</body>
</html>
    