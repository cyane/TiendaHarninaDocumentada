package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.GenericDao;
import dto.Carrito;
import dto.Login;
import org.json.simple.JSONObject;
import procedures.ProceduresProductos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Clase llamada desde JS que recibe un JSON con el carro actual del cliente y
 * actualiza la BBDD
 *
 */
@WebServlet("/guardarCarrito")
public class CarritoGuardarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	JSONObject oneJson;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                //Inicializa variables
		session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		Login login = new Login((int) session.getAttribute("idClient"));
		String jsonSring = request.getParameter("json");
                //Gson es una libreria externa para tratar JSON como objetos de java
		final Gson gson = new Gson();
		final Type tipo = new TypeToken<List<Carrito>>() {
		}.getType();
		final List<Carrito> carro = gson.fromJson(jsonSring, tipo);
		Boolean guardado = true;
		try {
                        //borra el carro actual de la BBDD
			new GenericDao().execProcedure(ProceduresProductos.DELETE_CARRITO.getName(), login);
                        //recorre el carro que entra desde JS y los introduce en la BBDD uno a uno 
			for (int i = 0; i < carro.size() && guardado; i++) {
				new GenericDao().execProcedure(ProceduresProductos.GUARDAR_CARRITO.getName(), carro.get(i));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException
				| InvocationTargetException | ParseException e) {
			e.printStackTrace();
		}
		oneJson = new JSONObject();
		oneJson.put("guardado", guardado);
                //retorna informacion de la operacion
		response.getWriter().write(oneJson.toJSONString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
}