
package interfaz;

import exepciones.FileException;
import java.io.File;
import java.io.IOException;
import java.util.List;


public interface IManager {
    
    public boolean eliminarArchivo(File archivo) throws FileException, IOException;
    
    public boolean eliminarDirectorio(File directorio) throws FileException, IOException;
    
    public boolean crearArchivo(File archivo) throws FileException, IOException;
    
    public boolean copiarArchivo(File origen, File destino) throws FileException, IOException;
    
    public List<File> escanearDirectorio(File directorio) throws FileException;
    
    public File[] obtenerArregloElementos(File directorio) throws FileException;
    
    public List<File> listarListaElementos(File directorio) throws FileException;
    
    
    public File seleccionarDirectorio(File ubicacion, String titulo);
    
    public File seleccionarArchivo(File ubicacion, String titulo);
    
    //public File guardarArchivo(String titulo);
    
}
