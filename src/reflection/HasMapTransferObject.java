package reflection;

import utils.SetterHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class HasMapTransferObject {
	
	//Crea y rellena de datos un POJO desde un hasmap
	//Entra un hasmap en el que la clave es el nombre de los atributos del pojo y el valor, el contenido del mismo
   public Object crearPojo(HashMap<String, Object> datosFila, String clasePojo) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

		//instancia un objeto con la clase(POJO) entrada mediante reflexion,
        Object genericObject = Class.forName(clasePojo).newInstance();
	
		//Crea un array con todos los metodos del POJO
        Method[] metodosDeclarados = genericObject.getClass().getDeclaredMethods();

		//crea un array con todos los campos del POJO
        Field[] atributos = genericObject.getClass().getDeclaredFields();
		
		//recorre los metodos del POJO
        for (int i = 0; i < metodosDeclarados.length; i++) {
			//recorre los atributos del POJO
            for (int j = 0; j < atributos.length; j++) {
				//cuando el nombre del contenga set+algun atributo, entra
                if (metodosDeclarados[i].getName().toLowerCase().contains("set" + atributos[j].getName().toLowerCase())) {
					//ejecuta el metodo set actual del POJO y extrae el objeto del hasmap que se guarde con la clave del atributo actual
					//por ejemplo si tenemos:
					// clasePojo = PersonaData
					// genericObject = new PersonaData()
					// atributos[j]= nombre 
					// metodosDeclarados[i] = setNombre(nombre)  
					// hasmap = clave:nombre valor:"antonio"
					//es como si ejecutaramos -> personaData.setNommbre("antonio")
                    new SetterHelper().ejecutarSet(datosFila.get(atributos[j].getName().toLowerCase()), genericObject, metodosDeclarados[i]);
                }
            }
        }
		//retorna el POJO relleno de los datos del hasmap
        return genericObject;
    }



}
