package controller;

import builders.PageBuilder;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

public class PaginaController {

    public PaginaController() {
    }
    /**
     * Monta la pagina pedida 
     * @param pageName nombre de la pagina a montar
     * @return estructura de la pagina en String
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws InstantiationException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException 
     */
    public String getPage(String pageName) throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        return new PageBuilder().buildPage(pageName);
    }

}
