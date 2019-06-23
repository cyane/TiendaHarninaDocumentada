package controller;

import dao.GenericDao;
import dto.PersonalData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import procedures.ProceduresClient;
import reflection.JsonTransferObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
/**
 * Clase padre de los controladores que usa el cliente
 */
public abstract class ClientController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session;
    private JSONObject oneJson;
    private HttpServletResponse response;
    private HttpServletRequest request;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            iniciarDatos(request, response);
            if (accion()) gestionarAccionCorrecto();
            else gestionarAccionNoCorrecto();

        } catch (IllegalAccessException | ParseException | InstantiationException | SQLException | InvocationTargetException | ClassNotFoundException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        llamadaAjax(oneJson.toJSONString());
    }
    /**
     * Este metodo es codificado en los hijos segun la responsabilidad que tenga cada uno
     * @return
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws InstantiationException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws org.json.simple.parser.ParseException 
     */
    protected abstract boolean accion() throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException, org.json.simple.parser.ParseException;
    /**
     * Da el nombre de la accion
     * @return String nombre accion realizada
     */
    protected abstract String getNombreAccion();
    /**
     * Da la pagina siguiente
     * @return String nombre de la siguiente pagina
     */
    protected abstract String getNombreSiguientePagina();
    /**
     * Rellena el objeto desde un JSON
     * @param object Objeto a rellenar con la informacion que viene del JSON
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws org.json.simple.parser.ParseException 
     */
    protected void transferJsonToObject(Object object) throws InvocationTargetException, IllegalAccessException, org.json.simple.parser.ParseException {
        new JsonTransferObject().transferir(object, (JSONObject) new JSONParser().parse(request.getParameter("json")));
    }
    /**
     * Pone en el JSON el estado de error y la accion que lo provoc
     */
    private void gestionarAccionNoCorrecto() {
        this.oneJson.put("errorVerificacion", "Error" + getNombreAccion());
        this.oneJson.put("estado", "ERROR");
    }
     /**
     * Pone en el JSON el estado de OK y la pagina siguiente
     */
    private void gestionarAccionCorrecto() {
        session.setAttribute("paginaActiva", getNombreSiguientePagina());
        this.oneJson.put("estado", "OK");
    }
    
    /**
     * Inicia las variables
     * @param request
     * @param response
     * @throws IllegalAccessException 
     */
    private void iniciarDatos(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
        this.session = request.getSession();
        this.oneJson = new JSONObject();
        this.response = response;
        this.request = request;
    }
    /**
     * Llamada de retorno a JS
     * @param Mensaje devuelto a JS
     * @throws IOException 
     */
    private void llamadaAjax(String s) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }

}
