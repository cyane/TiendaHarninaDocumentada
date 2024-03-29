package validators;

import dto.PersonalData;
import dto.PostalCode;
import error.Error;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;


public class ClientValidator {
	//valida el cliente dado, ejecuntando todos los validadores necesarios para ese pojo
	//retorna un array con los errores que hayan ocurrido en la validacion
    public ArrayList<Error> validate(PersonalData personalData) throws IllegalAccessException, InvocationTargetException, InstantiationException, ParseException, SQLException, ClassNotFoundException {
        ArrayList<Error> errors = new ArrayList<>();
        errors.add(new DNINIECIFValidator(personalData.getNif()).validate());
        errors.add(new ValidacionLetrasConEspacio(personalData.getFirstName()).validate());
        errors.add(new LengthValidator(personalData.getFirstName(), 3, 50).validate());
        errors.add(new ValidacionLetrasConEspacio(personalData.getLastName()).validate());
        errors.add(new LengthValidator(personalData.getLastName(), 15, 100).validate());
        errors.add(new PostalCodeValidator(personalData.getPostalCode()).validate());
        errors.add(new ValidarDomicilio(personalData.getAddress()).validate());
        errors.add(new LengthValidator(personalData.getAddress(), 2, 100).validate());
        errors.add(new ValidacionTelefonoSpain(String.valueOf(personalData.getPhone())).validate());
        errors.add(new ValidacionTelefonoSpain(String.valueOf(personalData.getMobile())).validate());
        errors.add(new DateValidator(personalData.getBirthDate()).validate());
        errors.add(new SexValidator(personalData.getSex()).validate());
        errors.add(new EmailValidator(personalData.getEmail()).validate());
        errors.add(new VerificacionCodigoPostal(new PostalCode(personalData.getPostalCode()).check_cp()).validate());
        errors.removeIf(Objects::isNull);

        return errors;
    }

}
