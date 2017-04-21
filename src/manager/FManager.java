
package manager;

import exepciones.FileException;
import interfaz.IManager;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class FManager implements IManager{

    private final ChooserManager cm;
    private final DirectoryManager dm;
    private final FileManager fm;
    private final IOManager iom;
    
    public FManager() {
        this.cm = new ChooserManager();
        this.dm = new DirectoryManager();
        this.fm = new FileManager();
        this.iom = new IOManager();
    }
    

    @Override
    public boolean eliminarArchivo(File archivo) throws FileException, IOException{
        if( archivo != null )
            return fm.deleteFile(archivo);
        
        return false;
    }
    
    
    @Override
    public boolean eliminarDirectorio(File directorio) throws FileException, IOException{
        if( directorio != null )
            return dm.deleteDirectory(directorio);
        
        return false;
    }

    
    @Override
    public boolean crearArchivo(File archivo) throws FileException, IOException{
        if( archivo != null )
            return fm.createFile(archivo);
        
        return false;
    }

    
    @Override
    public boolean copiarArchivo(File origen, File destino) throws FileException, IOException{
        if( origen != null && destino != null)
            return fm.copyFile(origen, destino);
        
        return false;
    }
    
    
    @Override
    public List<File> escanearDirectorio(File directorio) throws FileException{
        return dm.scanDirectory(directorio);
    }
    

    
    @Override
    public File seleccionarDirectorio(File ubicacion, String titulo) {
        return cm.selectDirectory(ubicacion, titulo);
    }

    
    @Override
    public File seleccionarArchivo(File ubicacion, String titulo) {
        return cm.selectFile(ubicacion, titulo);
    }
    
//    @Override
//    public File guardarArchivo(String titulo) {
//        return cm.saveChooser( titulo );
//    }

    @Override
    public File[] obtenerArregloElementos(File directorio) throws FileException{
        return this.dm.getElements(directorio);
    }

    @Override
    public List<File> listarListaElementos(File directorio) throws FileException{
        return this.dm.getElementsList(directorio);
    }

    
    
    

    
    
    
}
