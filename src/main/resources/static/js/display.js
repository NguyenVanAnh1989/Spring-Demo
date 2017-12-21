(function(){
	window.addEventListener('load',() => {
		console.log("done");
		$.get('/getUser',(data,status) => {
			$("#user").text(data.user);
			$("#pass").text(data.pass);
			console.log(data);
		});
	});
})();