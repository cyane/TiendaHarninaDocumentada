package utils;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;

public class ImageCharger {

    private String fileName;
    private Part filePart;
    private String idClient;
    private String path;

    /**
     * carga una imagen en la ruta introducida con el nombre introducido
     * @param filePart la imagen
     * @param path ruta
     * @param idClient nombre
     */
    public ImageCharger(Part filePart, String path, String idClient) {
        this.filePart = filePart;
        this.fileName = getFileName(filePart);
        this.idClient = idClient;
        this.path = path;
    }
    //el filePart se obtiene request.getPart("imagenCliente")
    //el path getServletContext().getRealPath("img/fotoClient/");

    public void clientFotoLoad() throws IOException {

        if (fileName.length() > 2) {

            fileName = idClient + ".png";

            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileOutputStream fs = new FileOutputStream(new File(path +"/"+ fileName));
            BufferedOutputStream buf = new BufferedOutputStream(fs);

            InputStream fileContent = filePart.getInputStream();
            BufferedInputStream bufIN = new BufferedInputStream(fileContent);

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = bufIN.read(buffer)) != -1) {
                buf.write(buffer, 0, bytesRead);
            }

            buf.close();
            bufIN.close();
        }
    }

    public String getFileName(Part filePart) {
         System.out.println(filePart.getHeader("content-disposition"));
        for (String cd : filePart.getHeader("content-disposition").split(";")) {
            System.out.println(cd);
            if (cd.trim().startsWith("filename")) {
                System.out.println(cd);
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return "fotoSin.jpg";
    }


}
