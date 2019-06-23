package reflection;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectTransferSession {
	//introduce en la session actual los datos del objeto dado
    public void convertir(Object object, HttpSession session) throws InvocationTargetException, IllegalAccessException {
		//crea un array de los campos del objeto
        Field [] atributosObjetos = object.getClass().getDeclaredFields();
		//crea un array de los metodos del objeto 
        Method [] metodosObjetos = object.getClass().getDeclaredMethods();
	
		//recorre los campos del objeto
        for (int i = 0; i < atributosObjetos.length; i++) {
			//guarda el nombre del atributo actual
            String atributoActual = atributosObjetos[i].getName();
			//recorre los metodos del objeto
            for (int j = 0; j < metodosObjetos.length; j++) {
				//si el metodo actual es un getter del atributo actual, entra
                if(metodosObjetos[j].getName().toLowerCase().equals("get"+atributoActual.toLowerCase())){
					//guarda en la session el valor del atributo actual, con el nombre del atributo como clave
                    session.setAttribute(atributoActual,metodosObjetos[j].invoke(object));
                }
            }
        }

    }

}

