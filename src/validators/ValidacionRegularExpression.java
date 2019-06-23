package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionRegularExpression {
	//es llamado por los metodos que validan mediante expresion regular, este la ejecuta y retorna true o false
    protected Boolean validar(String value,String patron){

        Pattern pattern = Pattern.compile(patron);

        Matcher matcher = pattern.matcher(value);

        return matcher.matches();

    }
}
