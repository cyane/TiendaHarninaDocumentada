package reflection;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class RsTransferArrayList {
	
	//transfiere los datos de la ejecucion de un resultSet a un arrayList del POJO introducido
    public ArrayList<?> getListGenericObject(CallableStatement procedure, String clase) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, ParseException {
		//ejecuta la ocnsulta y guarda el resulset
        ResultSet rs = procedure.executeQuery();
        ArrayList<Object> filas = new ArrayList<>();

		//recorre el resultset
        while (rs.next()) {
			//crea un hasmap para usar HasMapTransferObject(otra clase de reflexion)
            HashMap<String, Object> datosFila = new HashMap<>();
			//recorre las filas de la select que ha realizado el resultset
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				//en el hasmap, inserta una linea poniendo como clave, el nombre de la columna de la select y como valor, la informacion de esa columna en esta fila
                datosFila.put(rs.getMetaData().getColumnName(i).toLowerCase(), rs.getString(i)); // rs.getString(i)); // rs.getObject(i));
            }
			//crea un pojo a traves del hasmap y lo inserta en la lista
            filas.add(new HasMapTransferObject().crearPojo(datosFila, clase));
        }
		//retorna la lista
        return filas;
    }
	//ejecuta getListGenericObject y retorna el primero de la lista como objeto
    public Object getGenericObject(CallableStatement procedure, String clase) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, ParseException {
        return getListGenericObject(procedure, clase).get(0);
    }
}
