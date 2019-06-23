package controller;

import dao.GenericDao;
import dto.PersonalData;
import procedures.ProceduresClient;
import javax.servlet.annotation.WebServlet;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/updatePersonalData")
/**
 * actualiza los datos personales del usuario
 */
public class UpdatePersonalDataController extends ClientController {

    @Override
    protected boolean accion() throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException, org.json.simple.parser.ParseException {
        //crea un personaldata a traves del id del cliente actual
        PersonalData personalData= new PersonalData(String.valueOf(session.getAttribute("idClient")));
        //rellena el objeto desde el json
        super.transferJsonToObject(personalData);
        //lo actualiza en la bbdd y retorna rl rowcount de la procedure
        return ((Integer) new GenericDao().execProcedure(ProceduresClient.UPDATE_CLIENT_DAPER.getName(), personalData)) > 0;
    }

    @Override
    protected String getNombreAccion() {
        return "update";
    }

    @Override
    protected String getNombreSiguientePagina() {
        return "client";
    }
}

