package validators;

public class ListOfValuesValidator {
	//es llamado por los metodos que validan mediante una lista de posibles valores a introducir, 
	//se le entra la lista con los valores permitiros y el valor recibido, y este retorna si esta o no en la lista
    public boolean validar(String value, String [] valores){

        for (int i = 0; i < valores.length ; i++) {
            if(valores[i].toLowerCase().equals(value.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
