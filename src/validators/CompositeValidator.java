package validators;

import error.Error;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator implements ICompositeValidator {

    private ArrayList<Error> errors = new ArrayList<>();
	//le entra un objeto que implemente la interface validator 
    public ArrayList<Error> validate(Validator validators[]) {
		//recorre los validadores que tiene el array y ejecuta su metodo gracias a la interface
        for (Validator validator : validators) {
			//en el caso de que haya errores, se añaden
            errors.add(validator.validate());
        }

        return errors;
    }

    public static ArrayList<Error> validate(List<Validator> validators) {
        ArrayList<Error> errors = new ArrayList<>();

        for(Validator validator:validators){
            errors.add(validator.validate());
        }
        return errors;
    }
    /*
    public static String validate(List<Validator> validador ) {

        for(Validator vali:validador){

            if (!vali.validate()) {
                return vali.getError();
            }
        }
        return "";
    }
*/

}




