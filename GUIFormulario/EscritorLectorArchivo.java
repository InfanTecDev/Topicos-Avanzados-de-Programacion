import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EscritorLectorArchivo {
    private FileWriter escritor;
    private final String nombre;
    private String línea;
    private int num;
    
    public EscritorLectorArchivo(){
        nombre = "Personas ";
        num = 1;
        
    }
    
    public boolean escribirArchivo(ArrayList<Persona> listado){
        String name = asignarNombre(), RUTA_ARCHIVO = "Registros/" + name + ".txt";
        File archivo = new File(RUTA_ARCHIVO);
        try {
            archivo.createNewFile();
            escritor = new FileWriter(RUTA_ARCHIVO);
            for (Persona p : listado) {
                línea = p.toString() + "\n";
                escritor.write(línea);
            }
            escritor.close();
            return true;
        } catch (IOException ex) {
            System.err.println("Archivo no encontrado");
            return false;
        }
    }
    
    public ArrayList<Persona> cargarDatos(String nomA){
        ArrayList<Persona> lista = new ArrayList<>(); String [] x;
        String RUTA_ARCHIVO = "Registros/" + nomA + ".txt";
        try {
            Scanner s = new Scanner(new File(RUTA_ARCHIVO));
            while(s.hasNext()){
                x = s.nextLine().split(", ");
                lista.add(new Persona(x[0], Integer.parseInt(x[1]), Double.parseDouble(x[2]), Double.parseDouble(x[3]), Double.parseDouble(x[4])));
            }
                return lista;
       } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "Archivo no encontrado");
        }
        return null;
    }
    
    private String asignarNombre(){
        String nombreArchivo = nombre + num;
        while(new File("Registros/" + nombreArchivo + ".txt").exists()){
            nombreArchivo = nombre + ++num;
        }
        return nombreArchivo;
    }
}
