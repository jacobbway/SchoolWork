<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="submittedPoll.css" type="text/css">
	<title>The PHP runs after the gamer poll is submitted</title>

</head>
<body class="background">
	
	
	<div id="navBar"></div>
	
	<script src="submittedPoll.js" type="text/javascript"></script>
	
	<?php 
	
	
	if($_SERVER['REQUEST_METHOD'] == 'POST') { //grabbing the data sent via POST from pollPage.html
	   
	   $gamerTag = $_POST['gamerTag']; //creating variables
	   $selectedGames = isset($_POST['games']) ? $_POST['games'] : [];  
	?>
	<h1><b>Gamer Info Gathered</b></h1>
	<section><p>Gamer Tag: <?=$gamerTag?></p></section>
	
	
	<table style="table-border">
			<tr>
				<th>Games:</th>
			</tr>
		<?php
		if(empty($selectedGames)) { ?> <!-- Making sure $selectedGames isnt empty -->
			<tr>
				<td>No Games Selected :(</td>
			<tr>
		<?php 
		}
		else {
		  foreach ($selectedGames as $game) { //iterating through the array that $selected games is
		?>
		
		<tr>
			<td><?=$game?></td>
		</tr>
		<?php 
		  }
		}
		?>
	
	
	</table>
<?php
 }
 else {
    echo "Ope"; //this outputs if the POST fails
 }?>
</body>
</html>