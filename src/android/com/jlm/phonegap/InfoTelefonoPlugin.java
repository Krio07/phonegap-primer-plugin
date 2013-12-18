package com.jlm.phonegap;

import java.util.ResourceBundle.Control;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;


public class InfoTelefonoPlugin extends CordovaPlugin {
	
	public static final String OBTENER_INFO_ACTION = "OBETENER_INFO_ACTION";
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
	throws JSONException{
		boolean resultado = false;
		try {
			
			if(OBTENER_INFO_ACTION.equals(action)){
				JSONObject jsonSuccess = this.obtenerInfoTelefonoImpl();
				callbackContext.sendPluginResult(
						new PluginResult(PluginResult.Status.OK, jsonSuccess));
				
				throw new IllegalArgumentException(action + " no soportada.");
			}
			
			resultado = true;
		} catch (Throwable exc) {
			// TODO: handle exception
			JSONObject jsonError = new JSONObject("{ \"mensaje\" : \""+exc.getMessage()+"\"}");
			callbackContext.sendPluginResult(new PluginResult(Status.ERROR, jsonError));
		}
		
		return resultado;
	}
	
	private JSONObject obtenerInfoTelefonoImpl() throws JSONException{
		TelephonyManager manager = (TelephonyManager)
				super.cordova.getActivity()
							 .getSystemService(Context.TELEPHONY_SERVICE);
		String numero = manager.getLine1Number();
		String imei = manager.getDeviceId();
		String imsi = manager.getSubscriberId();
		
		String jsonString = 
				"{ 'numero' : '{0}', 'imei' : '{1}', 'imsi' : '{2}'}";
		jsonString = jsonString.replaceAll("'", "\"")
				.replace("{0}", numero)
				.replace("{1}", imei)
				.replace("{2}", imsi);
		
		return new JSONObject(jsonString);
		
		
	}

}
