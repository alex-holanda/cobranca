var Cobranca = Cobranca || {};

//	REMOVER TÍTULO

Cobranca.RemoverTitulo = (function() {
	
	function RemoverTitulo() {
		this.botaoExcluir = $('.js-excluir');
	}
	
	RemoverTitulo.prototype.remover = function() {
		this.botaoExcluir.on('click', function(event){
			var url = '/titulos/' + $(event.currentTarget).data('codigo');
			
			$.ajax({
				url: url,
				method: 'delete',
				contentType: 'application/json',
				success: onRemoverSucesso.bind(this)
			});
		});
	}
		
	function onRemoverSucesso() {
		window.location = '/titulos';
	}
	
	return RemoverTitulo;
}());

$(function() {
	var RemoverTitulo = new Cobranca.RemoverTitulo();
	RemoverTitulo.remover();
	
	$('[rel="tooltip"]').tooltip();
});

//ANTES DE CRIAR O OBJETO EM JAVASCRIPT

//$('.js-excluir').on('click', function(event) {
//	var button = $(event.currentTarget);
//	var codigo = button.data('codigo');
//	var url = '/titulos/remover/' + codigo;
//	
//	$.ajax({
//		url: url,
//		method: 'delete',
//		contentType: 'application/json',
//		success: onRemoverSucesso
//	});
//	
//	function onRemoverSucesso() {
//		
//		window.location = '/titulos';
//		console.log('Sucesso removendo título');
//	}
//});