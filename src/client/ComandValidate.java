package client;

import error.Error;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

//los commandvalidadores implementan esta interface para poder ser genericos y tener los mismo metodos
// a los comandvalidate les entra un objeto del tipo deseado por el contrstructor,
// y ejecutan un metodo que realiza todas las validaciones necesarias para ese objeto y en el caso de que haya errores los inserta en mapa
public interface ComandValidate {
    HashMap<String, Error> useCommands() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, ParseException, IllegalAccessException;
}
