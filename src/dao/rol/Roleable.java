package dao.rol;

import java.sql.Connection;
import java.sql.SQLException;

public interface Roleable {

    String getUsuario();

	String getPass();

	int getConexionesIniciales();

	int getConexionesMaximas();

    Connection getConnection() throws SQLException, ClassNotFoundException;
}
