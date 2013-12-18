cordova.define("com.jlm.phonegap.infotelefonoplugin",
		
		function (require, exports, module){
	
			var exec = require('cordova/exec';
			var InfoTelefono = require ('./InfoTelefono');
			
			var InfoTelefonoPlugin = function (){
				
			};
			
			
			
			InfoTelefonoPlugin.prototype.obtenerInfo = function(succes, fail){
				exec(function(info){
					var resultado = new InfoTelefono();
					resultado.imei = info.imei;
					resultado.numero = info.numero;
					resultado.imsi = info.imsi;
					success(resultado);
				}, fail, 'InfoTelefonoPlugin', []);
			};
			
			module.exports = new InfoTelefonoPlugin;
			
	
		}
		
);