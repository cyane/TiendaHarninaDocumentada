package controller;

import dao.GenericDao;
import dto.Carrito;
import dto.Login;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import procedures.ProceduresProductos;
import reflection.JsonTransferObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Clase llamada desde JS que accede a la BBDD para coger los datos del carrito
 * del cliente actual y volcarlos en un JSON que es devuelto a JS
 *
 */
@WebServlet("/getCarrito")
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	JSONObject oneJson;
	JSONArray arrayJson = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                //inicializa variables
		arrayJson = new JSONArray();
		session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		ArrayList<Carrito> listaModels = null;
		Carrito carrito = new Carrito();

		try {
                        //aade a el ArrayLista los items del carro del cliente en session
			carrito.setIdCliente((int) session.getAttribute("idClient"));
			listaModels = (ArrayList<Carrito>) new GenericDao().execProcedure(ProceduresProductos.GET_CARRITO.getName(),
					carrito);
                        //recorre el arrayList creando un JSON por iteracion y a�adiendolo a un ArrayJSON                    
			listaModels.forEach(modeloEntity -> {
				oneJson = new JSONObject();
				oneJson.put("IdModelo", modeloEntity.getIdModelo());
				oneJson.put("IdCliente", session.getAttribute("idClient").toString());
				oneJson.put("cantidadPedida", modeloEntity.getCantidadPedida());
				oneJson.put("actualPrecioModelo", modeloEntity.getActualPrecioModelo());
				arrayJson.add(oneJson);
			});
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException | ParseException e) {
			e.printStackTrace();
		}
                //retorna el ArrayJSON a JS
		response.getWriter().write(arrayJson.toJSONString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
}
