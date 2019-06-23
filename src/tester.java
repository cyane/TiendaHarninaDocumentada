import dto.PersonalData;
import reflection.HasMapTransferObject;
import reflection.RsTransferArrayList;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class tester {
    public static void main(String[] args) throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        //for (PersonalData ppl:(ArrayList<PersonalData>) getListaClientes()) {
        //    System.out.println(ppl.toString());
        //}

        HashMap<String, Object> hashTest = new HashMap<>();
        hashTest.put("nif", "53545454P");
        hashTest.put("lastName", "asd");
        hashTest.put("address", "asd");
        hashTest.put("postalCode", "asd");
        hashTest.put("birthDate", "asd");
        hashTest.put("phone", 7);
        hashTest.put("mobile", "asd");
        hashTest.put("sex", "asd");
        hashTest.put("email", "asd");
        hashTest.put("image", "asd");

        Object o = (PersonalData)new HasMapTransferObject().crearPojo(hashTest, PersonalData.class.getName());
        System.out.println( o.toString());
    }


    public static ArrayList<?> getListaClientes() {
        String clase = PersonalData.class.getName();
        try {
            Connection conexion = (Connection) AccesoDB.getMiConexion();//quitar el static de ese getConnection
            return new RsTransferArrayList().getListGenericObject((CallableStatement) conexion.prepareCall("{call getListaClientes()}"), clase);
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

 class AccesoDB {
    private static AccesoDB miConexion = null;
    private Connection conexion = null;
    private String driver = null;
    private String dbName= null;
    private String user = null;
    private String password= null;

    synchronized public static AccesoDB getMiConexion(){

        if (miConexion == null) miConexion = new AccesoDB();
        return miConexion;
    }

    synchronized public void conectar( )
            throws SQLException, ClassNotFoundException{
        if(conexion != null) {
            if (!this.driver.equals("") ||
                    !this.dbName.equals("tienda_harnina20189") ||
                    (this.user != null && !this.user.equals("root")) ||
                    (this.password != null && !this.password.equals(""))

            ) {
                conexion.close();
            } else return;
        }

        Class.forName(driver);

        if(user == null || password == null) conexion = (Connection) DriverManager.getConnection(dbName);
        else conexion = (Connection) DriverManager.getConnection(dbName,user,password);
        System.out.println("Conectado a BD " + dbName);
        this.driver = driver;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }
    synchronized public void cerrar() throws SQLException {
        if (conexion != null) conexion.close();
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{
        if(conexion == null) return null;
        Statement sentence = (Statement) conexion.createStatement();
        return sentence.executeQuery(sqlSentence);
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException{
        if(conexion == null) return -1;
        Statement sentence = (Statement) conexion.createStatement();
        return sentence.executeUpdate(sqlSentence);
    }


    public Connection getConexion(){
        return this.conexion;

    }
}

