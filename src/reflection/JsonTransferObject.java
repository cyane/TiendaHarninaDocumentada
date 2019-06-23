package reflection;

import org.json.simple.JSONObject;
import utils.SetterHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonTransferObject {
	//rellena de informacion un Objeto desde un JSON
    public void transferir(Object object, JSONObject... jsons) throws InvocationTargetException, IllegalAccessException {
		//crea un array con los metodos del objeto en cuestion
        Method[] metodosMolde = object.getClass().getDeclaredMethods();
		//recorre el array de metodos
        for (int i = 0; i < metodosMolde.length; i++) {
			//si el metodo es un set, entra
            if (metodosMolde[i].getName().substring(0, 3).equals("set")) {
				//recorre el JSON
                for (int j = 0; j < jsons.length; j++) {
					//prepara el nombre del parametro desde el metodo 
                    String nombreParametroBuscado = prepararNombreParametro(metodosMolde[i].getName().substring(3));
					//si el nombre del parametro es el del json entra
                    if (jsons[j].get(nombreParametroBuscado) != null) {
						// ejecuta un set en el objeto con los datos guardados en el json
                        new SetterHelper().ejecutarSet(jsons[j].get(nombreParametroBuscado), object, metodosMolde[i]);
                    }
                }
            }
        }
    }

    private String prepararNombreParametro(String substring) {
        char primeraLetra = substring.charAt(0);
        return  Character.toLowerCase(primeraLetra)+ substring.substring(1);
    }
}