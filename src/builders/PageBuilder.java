package builders;

import dao.GenericDao;
import dto.Page;
import dto.PageCss;
import procedures.ProceduresPage;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Encargada de montar la pagina html pedida, sacando la informacion de la bbdd
 */
public class PageBuilder {

    public PageBuilder() {
    }

    /**
     * retorna el codigo html de la pagina solicitada como string
     * @param pageName nombre de la pagina solicitada
     * @return esctructura html de la pagina
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws InstantiationException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    public String buildPage(String pageName) throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        Page page = new Page(pageName);
        fillPageDataFromDB(page);
        StringBuilder domBuilding = new StringBuilder();
        domBuilding.append("<HTML>");
        domBuilding.append(getHead(page));
        domBuilding.append(getBody(page));
        domBuilding.append("</HTML>");
        domBuilding.append(getJs(page));
        return new String(domBuilding);
    }

    /**
     * Extrae la informacion referente a la pagina de la BBDD
     * @param page Objeto pagina en el que vuelca la informacion
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws InstantiationException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    private void fillPageDataFromDB(Page page) throws IllegalAccessException, ParseException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        GenericDao genericDao = new GenericDao();
        page.setTitle((String) genericDao.execProcedure(ProceduresPage.GET_TITLE.getName(), page));
        page.setMetaAll((String) genericDao.execProcedure(ProceduresPage.GET_METAALL.getName(), page));
        page.setCssAll((List<PageCss>) genericDao.execProcedure(ProceduresPage.GET_CSSALL.getName(), new PageCss(), page));
        page.setLinkAll((String) genericDao.execProcedure(ProceduresPage.GET_LINKALL.getName(), page));
        page.setBody((String) genericDao.execProcedure(ProceduresPage.GET_BODY.getName(), page));
        page.setJsAll((List<PageCss>) genericDao.execProcedure(ProceduresPage.GET_JSALL.getName(), new PageCss(), page));
    }

    /**
     * monta y retorna el head de la pagina
     */
    private String getHead(Page page) {
        String head = "<head>";
        head += "<title>" + page.getTitle() + "</title>";
        head += page.getMetaAll();
        head += page.getLinkAll();
        head += "</head>";
        List<PageCss> cssAll = page.getCssAll();
        for (PageCss css : cssAll) {
            head += "<link rel='stylesheet' href='" + css.getPath() + "/" + css.getName() + ".css'>";
        }
        return head;
    }

    private String getBody(Page page) {
        return page.getBody();
    }
    /**
     * monta y retorna el js de la pagina
     */
    private String getJs(Page page) {

        String js = "";
        List<PageCss> jsAll = page.getJsAll();
        for (PageCss jss : jsAll) {
            js += "<script src ='" + "../" + jss.getPath() + "/" + jss.getName() + ".js'></script>";
        }
        return js;

    }


}

