package controller;

import dao.GenericDao;
import dto.Login;
import procedures.ProceduresClient;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/valiCliUpdateLogin")
@MultipartConfig
public class UpdateLoginController extends ClientController {

    @Override
    protected boolean accion() throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException, org.json.simple.parser.ParseException {
        //Instancia login
        Login login = new Login((int)session.getAttribute("idClient")); 
        //rellena objeto login con info del JSON
        super.transferJsonToObject(login);
        if(existeLogin(login)){
            //Ejecuta la procedure para cambiar el susuario y la contrasenia
            return ((Integer) new GenericDao().execProcedure(ProceduresClient.UPDATE_LOGIN.getName(), login)) > 0;
        }
    return false;
    }
    /**
     * Comprueba que el login es valido
     * @param login a comprobar 
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ParseException 
     */
    private boolean existeLogin(Login login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvocationTargetException, ParseException {
        return new GenericDao().execProcedure(ProceduresClient.GET_ID_LOGIN.getName(),login)==null;
    }

    @Override
    protected String getNombreAccion() {
        return "updateLogin";
    }

    @Override
    protected String getNombreSiguientePagina() {
        return "client";
    }

}
