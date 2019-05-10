var Cobranca = Cobranca || {};

//	MASKMONEY

Cobranca.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimalFormat = $('.js-currency');
	}
	
	MaskMoney.prototype.decimal = function() {
		this.decimalFormat.maskMoney({
			decimal: ',',
			thousands: '.',
			allowZero: true
		});
	}
	
	return MaskMoney;
}());

$(function() {
	var MaskMoney = new Cobranca.MaskMoney();
	MaskMoney.decimal();
});
