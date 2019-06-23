package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectsTransferObject {


	//transfiere los datos de uno o mas o objetos a uno solo
    public void transferir(Object molde, Object... objectos) throws InvocationTargetException, IllegalAccessException {
		//crea un array con los metodos del objeto que recibe la informacion
        Method [] metodosMolde = molde.getClass().getDeclaredMethods();
		//recorre los objetos que ventan
        for (int i = 0; i < objectos.length; i++) {
			//crea un array de los metodos del objeto que da informacion actual
            Method [] metodosTemp = objectos[i].getClass().getDeclaredMethods();
			//recorre los metodos del objeto que recibe informacion
            for (int k = 0; k < metodosMolde.length; k++) {
				//si el metodo del objeto que recibe informacion es un set, entra
                if(metodosMolde[k].getName().substring(0,3).equals("set")){
					//recorre los metodos del objeto que da informacion actual
                    for (int j = 0; j < metodosTemp.length; j++) {
						//si el metodo actual del objeto que da informacion es un getter
						//igual que el metodo del objeto que recibe informacion, entra
                        if(metodosTemp[j].getName().equals("get"+metodosMolde[k].getName().substring(3))){
							//ejecuta un setter en el objeto que recibe informacion, entrandole como 
							//parametro el resultado de la ejecucion de un getter del objeto actual que da informacion
                            metodosMolde[k].invoke(molde,metodosTemp[j].invoke(objectos[i]));
                        }
                    }
                }
            }
        }
    }
}
