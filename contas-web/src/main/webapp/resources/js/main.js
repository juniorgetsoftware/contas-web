function goToTop() {
	$('html, body').animate({
		scrollTop : 0
	}, 'fast');
}

function configurarMoeda() {
	$(".moeda").maskMoney({
		decimal : ",",
		thousands : "",
		allowZero : true
	});
}

function configurarInteiro() {
	$(".inteiro").maskMoney({
		decimal : "",
		thousands : "",
		allowZero : false
	});
}