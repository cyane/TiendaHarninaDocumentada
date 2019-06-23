package reflection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class RequestTransferSession {
	//guarda los parametros del request en la session
    public void guardarDatosSesion(HttpServletRequest request, HttpSession session) {
		//crea una enumeracion de los parametros del request
        Enumeration<String> parametersName = request.getParameterNames();
		
		//recorre la enumeracion
        while (parametersName.hasMoreElements()) {
            String attributeName = parametersName.nextElement();
			//guarda el parametro actual en la session con su nombre como clave
            session.setAttribute(attributeName, request.getParameter(attributeName));
        }
    }
}
