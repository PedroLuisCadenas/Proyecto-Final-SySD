import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;

public interface RMI extends Remote{
	HashSet<String> listaRemota() throws RemoteException, IOException;    
    String seleccionCarpetaRemota() throws RemoteException;
    boolean crearCarpeta(String nombreCarpeta) throws RemoteException;
    boolean borrarCarpeta(String nombreCarpeta) throws RemoteException, IOException;
    void subirArchivo(String carpetaDestino, String nombreArchivo, byte[] datosArchivo) throws RemoteException, IOException;
    void eliminarArchivo(String carpeta, String nombreArchivo) throws RemoteException, IOException;
    void subirCarpeta(String nombreCarpeta, HashSet<String> nombresArchivos, HashSet<byte[]> datosArchivos) throws RemoteException, IOException;
    boolean borrarCarpetaRecursivamente(String nombreCarpeta) throws RemoteException, IOException;
    byte[] descargarArchivo(String carpetaOrigen, String nombreArchivo) throws RemoteException, IOException;
    HashMap<String, byte[]> descargarCarpeta(String nombreCarpeta) throws RemoteException, IOException;
}

