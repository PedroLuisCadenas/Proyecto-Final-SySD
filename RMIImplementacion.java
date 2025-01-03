
import java.io.*;
import java.nio.file.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class RMIImplementacion extends UnicastRemoteObject implements RMI {

	
	public RMIImplementacion() throws RemoteException, IOException {
		super();
	}

    // Devuelve la lista de archivos de la carpeta remota
    @Override
    public synchronized HashSet<String> listaRemota() throws IOException, RemoteException {
        String carpetaRemota = "./carpetaRemota"; // Carpeta raíz del servidor
        File directorio = new File(carpetaRemota);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        Path ruta = FileSystems.getDefault().getPath(carpetaRemota);
        DirectoryStream<Path> stream = Files.newDirectoryStream(ruta);
        HashSet<String> files = new HashSet<>();
        for (Path file : stream) {
            files.add(file.getFileName().toString());
        }
        stream.close();
        return files;
    }

    // Elige una de las carpetas remotas
        @Override
	public synchronized String seleccionCarpetaRemota() throws RemoteException{
		System.out.print("Introducir ruta carpeta local carpeta remota:");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		return nombre;
	}

    // Descarga de un archivo desde el servidor al cliente
        @Override
    public synchronized byte[] descargarArchivo(String carpetaOrigen, String nombreArchivo) throws IOException, RemoteException {
        File archivo = new File(carpetaOrigen, nombreArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe en el servidor: " + nombreArchivo);
        }
        try (FileInputStream fis = new FileInputStream(archivo);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    // Subida de un archivo desde el cliente al servidor
        @Override
    public synchronized void subirArchivo(String carpetaDestino, String nombreArchivo, byte[] datos) throws IOException, RemoteException {
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            if (!carpeta.mkdirs()) {
                throw new IOException("No se pudo crear la carpeta de destino: " + carpetaDestino);
            }
        }
        File archivo = new File(carpeta, nombreArchivo);
        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            fos.write(datos);
        }
    }

    // Elimina un archivo en el servidor
        @Override
    public synchronized void eliminarArchivo(String carpeta, String nombreArchivo) throws IOException, RemoteException {
        File archivo = new File(carpeta, nombreArchivo);
        if (!archivo.exists()) {
            throw new IOException("El archivo no existe en el servidor: " + nombreArchivo);
        }
        if (!archivo.delete()) {
            throw new IOException("No se pudo eliminar el archivo: " + nombreArchivo);
        }
    }

    // Crea una carpeta en el servidor
        @Override
    public synchronized boolean crearCarpeta(String nombreCarpeta) throws RemoteException {
        File carpeta = new File(nombreCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir();
            return true;
        }
        return false;
    }

    // Elimina una carpeta en el servidor
        @Override
    public synchronized boolean borrarCarpeta(String nombreCarpeta) throws RemoteException, IOException {
        String carpetaRemota = "./carpetaRemota";
        File carpeta = new File(carpetaRemota, nombreCarpeta);
        File[] archivos = carpeta.listFiles();
        if (archivos != null && archivos.length > 0) {
            throw new IOException("La carpeta no está vacía: " + nombreCarpeta);
        }
        if (!carpeta.delete()) {
            throw new IOException("No se pudo eliminar la carpeta: " + nombreCarpeta);
        }
        return true;
    }

    // Elimina una carpeta con archivos dentro
        @Override
    public synchronized boolean borrarCarpetaRecursivamente(String nombreCarpeta) throws RemoteException, IOException {
        File carpeta = new File(nombreCarpeta);
        if (carpeta.exists() && carpeta.isDirectory()) {
            borrarRecursivamente(carpeta);
            return true;
        } else {
            System.out.println("La carpeta no existe o no es un directorio: " + nombreCarpeta);
        }
        return false;
    }

    // Función auxiliar para borrar una carpeta con archivos dentro
    private void borrarRecursivamente(File carpeta) throws IOException {
        for (File archivo : carpeta.listFiles()) {
            if (archivo.isDirectory()) {
                borrarRecursivamente(archivo);
            } else {
                if (!archivo.delete()) {
                    throw new IOException("No se pudo eliminar el archivo: " + archivo.getAbsolutePath());
                }
            }
        }
        if (!carpeta.delete()) {
            throw new IOException("No se pudo eliminar la carpeta: " + carpeta.getAbsolutePath());
        }
    }

    // Descarga de una carpeta completa desde el servidor
        @Override
    public synchronized HashMap<String, byte[]> descargarCarpeta(String nombreCarpeta) throws IOException, RemoteException {
        String carpetaRemota = "./carpetaRemota";
        File carpeta = new File(carpetaRemota, nombreCarpeta);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            throw new FileNotFoundException("La carpeta no existe en el servidor: " + nombreCarpeta);
        }
        HashMap<String, byte[]> archivos = new HashMap<>();
        for (File archivo : carpeta.listFiles()) {
            if (archivo.isFile()) {
                try (FileInputStream fis = new FileInputStream(archivo);
                     ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    archivos.put(archivo.getName(), baos.toByteArray());
                }
            }
        }
        return archivos;
    }

    // Subida de una carpeta completa al servidor
        @Override
    public synchronized void subirCarpeta(String nombreCarpeta, HashSet<String> nombresArchivos, HashSet<byte[]> datosArchivos) throws IOException, RemoteException {
        String carpetaRemota = "./carpetaRemota";
        File carpeta = new File(carpetaRemota, nombreCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        int index = 0;
        for (String nombreArchivo : nombresArchivos) {
            File archivo = new File(carpeta, nombreArchivo);
            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                fos.write((byte[]) datosArchivos.toArray()[index]);
            }
            index++;
        }
    }

    
}


