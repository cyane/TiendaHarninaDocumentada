package validators;

import error.Error;
	//es una interfaz implementada por los validadores para que tengan el metodo validate
public interface Validator {
    Error validate();
}


