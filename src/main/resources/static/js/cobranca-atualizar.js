var Cobranca = Cobranca || {};


Cobranca.AtualizarTitulo = (function() {
	
	function AtualizarTitulo() {
		this.botaoReceber = $('.js-atualizar-status');
	}
	
	AtualizarTitulo.prototype.atualizar = function() {
		
		this.botaoReceber.on('click', function(event) {
			var url = $(event.currentTarget).data('url');
			
			console.log(url);
			
			$.ajax({
				url: url,
				type: 'put',
				success: onAtualizarSucesso.bind(this)
			});
			
			function onAtualizarSucesso() {
				window.location = '/titulos';
			}
		});
	}
		
	return AtualizarTitulo;
}());

$(function() {
	var AtualizarTitulo = new Cobranca.AtualizarTitulo();
	AtualizarTitulo.atualizar();
});