package validators;

import error.Error;

public class LengthValidator implements Validator {

    private String cadena;

    private int minimo;

    private int maximo;
//valida la longitud maxima y minima e una cadena
    public LengthValidator(String cadena, int minimo, int maximo) {

        assert maximo >= minimo;

        this.cadena = cadena;

        this.minimo = minimo;

        this.maximo = maximo;
    }


    @Override
    public Error validate() {

        if (this.minimo <= this.cadena.length() && this.cadena.length() <= this.maximo) {
            System.out.println("solo se dar fallos ajam: " + cadena);
            return null;
        }

        return Error.ERROR_INTERVALO;
    }


}
