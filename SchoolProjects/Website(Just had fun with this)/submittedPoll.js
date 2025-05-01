	fetch('navBar.html')//this .js is slightly differnet since this one is being displayed on .php that is generated from the form submitting
		.then(response => { //it has no active button since you cannot access this page from the navigation bar
		return response.text();
	})
		.then(data => {
		document.getElementById('navBar').innerHTML = data;
		
		
	});