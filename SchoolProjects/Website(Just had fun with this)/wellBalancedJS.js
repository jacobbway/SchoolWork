fetch('navBar.html') //this grabs the navBar.html and injects it into anywhere i want the navigation bar to be
			.then(response => { 
				return response.text();
			})
			.then(data => {
			document.getElementById('navBar').innerHTML = data;
			
			setActiveButton('homeButton');//this parameter changes based on the webpage.
			});
			
function setActiveButton(activeID) { //this function changes the background color of the desired button
	
	const allNavBarItems = document.querySelectorAll('#navBar li'); //grabs all eleemts
	allNavBarItems.forEach(item => item.classList.remove('active')); //removes the active style that could be on on element already
	
	const activeItem = document.getElementById(activeID);
	if(activeItem) {
		activeItem.classList.add('active'); 
	}
}