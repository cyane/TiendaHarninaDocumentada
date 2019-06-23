package controller;

import dao.GenericDao;
import dto.FacturaCabecera;
import dto.FacturaDetalle;
import dto.Login;
import org.json.simple.JSONArray;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
/**
 * Cuando es llamada desde JS va a la BBDD a por las facturas y su informacion y las retorna a JS como un JSON
 */
@WebServlet("/getFacturas")
public class PillarFacturaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    JSONObject oneJson;
    JSONArray arrayJson = null;
    JSONObject otherJson;
    JSONArray arrayLineas= null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //inicializa variables
        arrayJson = new JSONArray();
        session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        ArrayList<FacturaCabecera> listaModels = null;
        Login login = new Login();
        FacturaCabecera facturaCabecera = new FacturaCabecera();
        FacturaDetalle facturaDetalle = new FacturaDetalle();
        try {
            login.setIdClient((int) session.getAttribute("idClient"));
            //extrae las cabeceras de las facturas para mostrarlas por JS
            listaModels = (ArrayList<FacturaCabecera>) new GenericDao().execProcedure(ProceduresProductos.GET_FACTURAS_CABECERAS.getName(), facturaCabecera,login);
            System.out.println("facturas de la bbdd: "+listaModels);
            listaModels.forEach(modeloEntity -> {
                try {
                    System.out.println("numero "+modeloEntity.getNumeroFactura());
                    facturaDetalle.setNumeroFactura(modeloEntity.getNumeroFactura());
                    //extrae la informacion d las facturas una por una para cuando sea necesario su uso
                    ArrayList<FacturaDetalle> lineas = (ArrayList<FacturaDetalle>) new GenericDao().execProcedure(ProceduresProductos.GET_FACTURAS_DETALLES.getName(), facturaDetalle);
                    System.out.println("lineas"+lineas);
                    modeloEntity.setLineasFactura(lineas);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | InvocationTargetException | ParseException e) {
                    e.printStackTrace();
                }
                oneJson = new JSONObject();
                oneJson.put("numeroFactura",modeloEntity.getNumeroFactura());
                oneJson.put("nombreEmpresaFactura",modeloEntity.getNombreEmpresaFactura());
                oneJson.put("domicilioEmpresaFactura",modeloEntity.getDomicilioEmpresaFactura());
                oneJson.put("cifEmpresaFactura",modeloEntity.getCifEmpresaFactura());
                oneJson.put("nombreClienteFactura",modeloEntity.getNombreClienteFactura());
                oneJson.put("domicilioClienteFactura",modeloEntity.getDomicilioClienteFactura());
                oneJson.put("dniClienteFactura",modeloEntity.getDniClienteFactura());
                oneJson.put("iva",modeloEntity.getIva());
                oneJson.put("fecha",modeloEntity.getFechaFacturacion());
                System.out.println("factura actual -> "+modeloEntity.toString());
                System.out.println("cantidad lineas en factura "+modeloEntity.getLineasFactura().size());
                arrayLineas = new JSONArray();
                for (FacturaDetalle linea: modeloEntity.getLineasFactura() ) {
                    System.out.println(linea.toString());
                    otherJson = new JSONObject();
                    otherJson.put("idModelo",linea.getIdModelo());
                    otherJson.put("nombreModelo",linea.getNombreModelo());
                    otherJson.put("cantidadComprada",linea.getCantidadComprada());
                    otherJson.put("precioModelo",linea.getPrecioModelo());
                    System.out.println("linea actual -> "+linea.toString());
                    arrayLineas.add(otherJson);
                }
                oneJson.put("lineas",arrayLineas.toJSONString());
                arrayJson.add(oneJson);
            });
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
        //retorna un arrayJSON con las facturas del cliente completas
        response.getWriter().write(arrayJson.toJSONString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
