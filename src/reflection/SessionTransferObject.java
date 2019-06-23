package reflection;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SessionTransferObject {
	//rellena un objeto con informacion almacenada en la session
    public SessionTransferObject(HttpSession session, Object object) throws InvocationTargetException, IllegalAccessException {
		//recorre los metodos del objeto deseado
        for (Method method : object.getClass().getDeclaredMethods()) {
			//recorre los campos del objeto deseado
            for (Field field : object.getClass().getDeclaredFields()) {
				//si el metodo actual es el setter del campo actual, pasa
                if (isSetter(method, field)) {
					//si existe en la session un valor guardado con el nombre del campo actual como clave, entra
                    if (existField(session, field)) {
						//ejecuta el setter en el objeto, entrandole el valor que hay en la session, guardado con la clave que es el campo actual
                        fillField(session, object, method, field);
                    }
                }
            }
        }
    }
	
	//ejecuta el metodo dado, en el objeto dado, con el parametro obtenido de la session dada mediante el campo dado
    private void fillField(HttpSession session, Object object, Method m, Field f) throws IllegalAccessException, InvocationTargetException {
        m.invoke(object, session.getAttribute(f.getName()));
    }
	//valida si el metodo es un setter existente
    private boolean isSetter(Method m, Field f) {
        return m.getName().toLowerCase().contains("set" + f.getName().toLowerCase());
    }
	//valida si existe en la session un valor guardado con esa clave
    private boolean existField(HttpSession session, Field f) {
        return session.getAttribute(f.getName()) != null;
    }
}
