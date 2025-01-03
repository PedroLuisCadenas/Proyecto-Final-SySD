
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Local {
		
	//Elegir carpeta local
	public String seleccionCarpetaLocal() {
		System.out.print("Introducir ruta carpeta local carpeta local:");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		return nombre;
	}
	
	//Creamos lista de archivos de la carpeta local y los almacenamos en un hash	
		public HashSet<String> listaLocal() throws IOException, RemoteException {
			String carpetaLocal = seleccionCarpetaLocal();
			File directorio = new File(carpetaLocal);
			if(directorio.exists()) {
			    Path ruta2=FileSystems.getDefault().getPath(carpetaLocal);
			    System.out.println(ruta2.toString());
			    DirectoryStream<Path> stream2 = Files.newDirectoryStream(ruta2);
			    HashSet<String> files2 = new HashSet<>();
			    for (Path file: stream2) {
				    System.out.println(file.getFileName());
				    files2.add(file.getFileName().toString());
		        }
                stream2.close();	
			return files2;
		    }

			else {
			    directorio.mkdir();
			    Path ruta2=FileSystems.getDefault().getPath(carpetaLocal);
			    System.out.println(ruta2.toString());
			    DirectoryStream<Path> stream2 = Files.newDirectoryStream(ruta2);
			    HashSet<String> files2 = new HashSet<>();
			    for (Path file: stream2) {
				    System.out.println(file.getFileName());
				    files2.add(file.getFileName().toString());
			    }
                stream2.close();
			    return files2;
		    }
			
		}
		public void comparar() throws IOException, RemoteException {
			
			RMIImplementacion x = new RMIImplementacion();
			HashSet<String> files1 = x.listaRemota();
			HashSet<String> files2 = listaLocal();
			
			//Archivos comunes en ambas carpetas
			Set<String> intersectSet = new HashSet<>(files1);
			intersectSet.retainAll(files2);
			System.out.println("Ficheros iguales:");
			for (String s: intersectSet) {
				System.out.println(s);
				
			}
			//Archivos de descarga, que solo se encuentran en la carpeta remota
			Set<String> difSet1 = new HashSet<>(files1);
			difSet1.removeAll(files2);
			System.out.println("Ficheros solo en carpeta remota:");
			for (String s: difSet1) {
				System.out.println(s);
			}
			//Archivos de subida, que solo se encuentran en la carpeta local
			Set<String> difSet2 = new HashSet<>(files2);
			difSet1.removeAll(files1);
			System.out.println("Ficheros solo en careta local:");
			for (String s: difSet2) {
				System.out.println(s);
			}
			
	}
}
