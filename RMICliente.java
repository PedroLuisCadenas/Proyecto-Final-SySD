
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;

public class RMICliente {

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, IOException {
		
		RMI x = (RMI)Naming.lookup("rmi://localhost:1099/RMI");
		

        // PRUEBA PARA CREAR CARPETA

        // Crear una nueva carpeta en el servidor
        // String nombreCarpeta = "./carpetaRemota/nuevaCarpeta5";
        // boolean creada = x.crearCarpeta(nombreCarpeta);
        // if (creada) {
        //     System.out.println("Carpeta creada exitosamente: " + nombreCarpeta);
        // } else {
        //     System.out.println("La carpeta ya existe o no se pudo crear: " + nombreCarpeta);
        // }


        // PRUEBA PARA BORRAR CARPETA VACÍA

        //eliminar carpeta vacía
        // boolean borrada = x.borrarCarpeta("nuevaCarpeta5");
        // if (borrada) {
        //     System.out.println("Carpeta borrada exitosamente: " + "nuevaCarpeta1");
        // } else {
        //     System.out.println("La carpeta no existe o no se pudo borrar: " + "nuevaCarpeta1");
        // }


        // PRUEBA PARA BORRAR CARPETA CON ARCHIVOS

        //Eliminar una carpeta en el servidor
        // String nombreCarpeta = "./carpetaRemota/nuevaCarpetaLocal";

        // // Intentar eliminar la carpeta en el servidor
        // try {
        //     boolean borrada = x.borrarCarpetaRecursivamente(nombreCarpeta);
        //     if (borrada) {
        //         System.out.println("Carpeta borrada exitosamente: " + nombreCarpeta);
        //     } else {
        //         System.out.println("La carpeta no existe o no se pudo borrar: " + nombreCarpeta);
        //     }
        // } catch (IOException e) {
        //     System.err.println("Error al eliminar la carpeta: " + e.getMessage());
        // }


        // PRUEBA PARA SUBIR ARCHIVO

        // // Leer un archivo local y subirlo al servidor
        // String rutaArchivoLocal = "C:\\Users\\Pedro Luis\\Desktop\\PruebaProyecto.txt";
        // File archivoLocal = new File(rutaArchivoLocal);
        // byte[] datosArchivo = new byte[(int) archivoLocal.length()];
        // try (FileInputStream fis = new FileInputStream(archivoLocal)) {
        //     fis.read(datosArchivo);
        // }

        // // Especificar la carpeta de destino en el servidor
        // String carpetaDestino = "./carpetaRemota/nuevaCarpeta2";
        // String nombreArchivoRemoto = "archivoRemoto1.txt";
        // x.subirArchivo(carpetaDestino, nombreArchivoRemoto, datosArchivo);
        // System.out.println("Archivo subido exitosamente a la carpeta: " + carpetaDestino);
    

        // PRUEBA PARA ELIMINAR ARCHIVO

        // Especificar la carpeta y el nombre del archivo a eliminar
        // String carpeta = "./carpetaRemota/nuevaCarpeta2";
        // String nombreArchivo = "archivoRemoto1.txt";

        // // Intentar eliminar el archivo en el servidor
        // try {
        //     x.eliminarArchivo(carpeta, nombreArchivo);
        //     System.out.println("Archivo eliminado exitosamente: " + nombreArchivo);
        // } catch (IOException e) {
        //     System.err.println("Error al eliminar el archivo: " + e.getMessage());
        // }


        // PRUEBA PARA SUBIR CARPETA

        // String rutaCarpetaLocal = "C:\\Users\\Pedro Luis\\Desktop\\CarpetaPrueba";
        // File carpetaLocal = new File(rutaCarpetaLocal);
        // if (!carpetaLocal.exists() || !carpetaLocal.isDirectory()) {
        //     throw new IOException("La carpeta local no existe o no es un directorio: " + rutaCarpetaLocal);
        // }

        // HashSet<String> nombresArchivos = new HashSet<>();
        // HashSet<byte[]> datosArchivos = new HashSet<>();

        // for (File archivoLocal : carpetaLocal.listFiles()) {
        //     if (archivoLocal.isFile()) {
        //         nombresArchivos.add(archivoLocal.getName());
        //         byte[] datosArchivo = new byte[(int) archivoLocal.length()];
        //         try (FileInputStream fis = new FileInputStream(archivoLocal)) {
        //             fis.read(datosArchivo);
        //         }
        //         datosArchivos.add(datosArchivo);
        //     }
        // }

        // // Especificar la carpeta de destino en el servidor
        // String nombreCarpetaRemota = "nuevaCarpetaLocal";
        // x.subirCarpeta(nombreCarpetaRemota, nombresArchivos, datosArchivos);
        // System.out.println("Carpeta subida exitosamente al servidor: " + nombreCarpetaRemota);


        // PRUEBA PARA DESCARGAR ARCHIVO
        // Especificar la carpeta de origen y el nombre del archivo a descargar
        // String carpetaOrigen = "./carpetaRemota/nuevaCarpeta2";
        // String nombreArchivo = "archivoRemoto1.txt";

        // // Intentar descargar el archivo del servidor
        // try {
        //     byte[] datosArchivo = x.descargarArchivo(carpetaOrigen, nombreArchivo);
        //     // Guardar el archivo descargado en una ubicación local 
        //     String rutaArchivoLocal = "C:\\Users\\Pedro Luis\\Desktop\\DescargasPrueba\\" + nombreArchivo;
        //     try (FileOutputStream fos = new FileOutputStream(rutaArchivoLocal)) {
        //         fos.write(datosArchivo);
        //     }
        //     System.out.println("Archivo descargado exitosamente: " + rutaArchivoLocal);
        // } catch (IOException e) {
        //     System.err.println("Error al descargar el archivo: " + e.getMessage());
        // }


        // PRUEBA PARA DESCARGAR CARPETA

        // Especificar la carpeta de origen a descargar
        // String nombreCarpetaRemota = "nuevaCarpeta2";

        // // Intentar descargar la carpeta del servidor
        // try {
        //     HashMap<String, byte[]> archivos = x.descargarCarpeta(nombreCarpetaRemota);
        //     // Guardar los archivos descargados en una carpeta local
        //     String rutaCarpetaLocal = "C:\\Users\\Pedro Luis\\Desktop\\DescargasPrueba\\" + nombreCarpetaRemota;
        //     File carpetaLocal = new File(rutaCarpetaLocal);
        //     if (!carpetaLocal.exists()) {
        //         carpetaLocal.mkdirs();
        //     }
        //     for (String nombreArchivo : archivos.keySet()) {
        //         byte[] datosArchivo = archivos.get(nombreArchivo);
        //         File archivoLocal = new File(carpetaLocal, nombreArchivo);
        //         try (FileOutputStream fos = new FileOutputStream(archivoLocal)) {
        //             fos.write(datosArchivo);
        //         }
        //     }
        //     System.out.println("Carpeta descargada exitosamente: " + rutaCarpetaLocal);
        // } catch (IOException e) {
        //     System.err.println("Error al descargar la carpeta: " + e.getMessage());
        // }


        // PRUEBA PARA LISTAR ARCHIVOS

		HashSet<String> archivos = x.listaRemota();
        System.out.println("Archivos en la carpeta remota:");
        for (String archivo : archivos) {
            System.out.println(archivo);
        }
    }
}