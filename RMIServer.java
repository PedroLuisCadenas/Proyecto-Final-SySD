import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) throws RemoteException, IOException {
		
		//Start the RMI registry
		LocateRegistry.createRegistry(1099); //Default port
		
		
		RMIImplementacion pt = new RMIImplementacion();
		//Register the remote object
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("RMI", pt);
		System.out.println("Servidor listo");

		//Crear un hilo para manejar las solicitudes de los clientes
		new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
	}

}

