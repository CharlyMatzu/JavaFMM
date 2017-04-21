
package manager;

import exepciones.FileException;
import java.io.File;
import java.io.IOException;



class FileManager {
    
    private final CopyFileMethods copyMethods;

    
    public FileManager() {
        this.copyMethods = new CopyFileMethods();
    }
    
    
    
    /**
     * Método que elimina un archivo o directorio (vacio)
     * @param file archivo
     * @return TRUE si fue eliminado, FALSE si no se pudo eliminar
     */
    public boolean deleteFile( File file ) throws FileException{
        if( file == null )
            throw new FileException("Archivo null");
        
        return file.delete();
    }
    
    
    /**
     * Método que permite copiar un archivo de un lugar a otro utilizando
     * por medio de "Stream" convencional
     * @param from archivo fuente
     * @param to destino del archivo
     * @return TRUE si se copio correctamente, FALSE si no
     */
    public boolean copyFile( File from, File to ) throws FileException, IOException{
        if( from == null )
            throw new FileException("No se asigno un archivo de origen");
        if( to == null )
            throw new FileException("No se asigno un archivo destino");
        
        return copyMethods.copyFileUsingStream(from, to);
    }
    
    
    
    /**
     * Método que permite crear un archivo nuevo
     * @param file archivo a crear
     * @return TRUE si se creo con exito, FALSE si no se creo
     */
    public boolean createFile(File file) throws FileException, IOException{
        if( file.exists() )
            throw new FileException("Archivo ya existe");
        
        return file.createNewFile();
    }
    
    
    public boolean createFileOnExist(File file) throws FileException, IOException{
        if( file.exists() )
            deleteFile(file);
        
        createFile(file);
            
        return false;
            
    }
    
    
    /**
     * Cierre de los metodos de lectura
     */
    private void closeFileReader(){
//        try {
//            fr.close();
//            br.close();
//        } catch (IOException ex) {
//            mensaje("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE );
//        }
    }
    
    
}
