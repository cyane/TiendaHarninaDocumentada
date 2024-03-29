package validators;

import error.Error;

public class ExtensionFileValidator extends ListOfValuesValidator implements Validator {


    private String value;
    private String[] lista_extension;
	//valida si la cadena que viene esta en la lista que viene 
    public ExtensionFileValidator(String value, String[] listaExtension) {
        this.value = value;
        this.lista_extension = listaExtension;
    }

    @Override
    public Error validate() {
        if (super.validar(value, lista_extension)) {
            return null;
        }

        return Error.ERROR_EXTENSION_BAD;
    }


}
