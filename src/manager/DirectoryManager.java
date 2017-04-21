
package manager;

import exepciones.FileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class DirectoryManager {

    private final FileManager fileManager;
    private  List<File> directoryScan;
    
    
    public DirectoryManager() {
        fileManager = new FileManager();
    }
    
    
    /**
     * Método que permite obtener la estructura de archivos completa de un
     * directorio
     * @param dir directorio
     * @return lista que representa la estructura de archivos del directorio
     */
    public List<File> scanDirectory( File dir ) throws FileException{
        
        if( dir.isFile() )
            throw new FileException("No es directorio");
        
        if( !dir.exists() )
            throw new FileException("No existe directorio");
        
        if( dir == null )
            throw new FileException("Directorio es objeto null");
        
        
        //Si se selecciono directorio
        this.directoryScan = new ArrayList<>();
        scan( dir, this.directoryScan );
        //System.out.println("Escaneo finalizado a " + dir.getName() );
        return this.directoryScan;

    }
    
    
    
    /**
     * Método que crea un nuevo directorio individual
     * @param dir ruta y nombre del directorio
     * @return TRUE si se pudo crear, FALSE si no se creo
     */
    public boolean createDirectory( File dir ) throws FileException{
        //Si no existe directorio
        if( dir.exists() )
            throw new FileException("Directorio ya existe");
        
        if( dir.mkdir() ){
            System.out.println("Se creo directorio: " +dir.getName()+" en: "+ dir.getParent() );
            return true;
        }
        return false;
    }
    
    
    public boolean createDirectoryOnExist( File dir ) throws FileException{
        //Si no existe directorio
        if( dir.exists() ){
            if( dir.delete() )
                throw new FileException("No se pudo eliminar directorio");
        }
            
        if( dir.mkdir() ){
            System.out.println("Se creo directorio: " +dir.getName()+" en: "+ dir.getParent() );
            return true;
        }
        return false;
    }
    
    
    
    
    /**
     * Método que permite copiar todo un directorio completo
     * @param from elemento de origen a copiar
     * @param to elemento destino
     * @param onlyContent cuando es directorio: TRUE si se desea copiar solo contenido, 
     * FALSE copiar también directorio base
     * @return 
     */
//    public void copyDirectory(File from, File to, boolean onlyContent) throws FileException{
//        
//        //Excepciones
//        if( from == null )
//            throw new FileException("No se ha asignado un directorio de origen");
//        
//        if( !from.exists() )
//            throw new FileException("No existe el directorio origen");
//        
//        if( to == null )
//            throw new FileException("No se ha asignado un directorio destino");
//        
//        if( !from.exists() )
//            throw new FileException("No existe el directorio destino");
//        
//        //Si se selecciono un archivo, se asigna el directorio al que pertenece
//        if( from.isFile() )
//            from = from.getParentFile();
//        
//        if( to.isFile() )
//            to = to.getParentFile();
//        
//        
//        //Si se desea crear el directorio base (directorio seleccionado)
//        if( !onlyContent ){
//            to = copyDirectoryName(from, to);
//            createDirectory( to );
//        }
//        //Inicia el copiado
//        copyAllDirectory(from, to);
//        
//    }
    
    
//    public void copyDirectory(List<File> fromItemList, File to){
//        
//        for (File item : fromItemList) {
//            try {
//                fileManager.copyFile(item, to);
//            } catch (FileException | IOException ex) {
//                //Si no se copio archivo actual
//            }
//        }
//        
//    }
    
    
    
    
    /**
     * Método que elimina un archivo o directorio
     * @param dir elemento a eliminar
     */
    public boolean deleteDirectory( File dir ) throws FileException{
        
        if( !dir.isDirectory() )
            throw new FileException("No es directorio");
        
        if( !dir.exists() )
            throw new FileException("No existe directorio");
        
        return deleteAllDirectory(dir);
        
            
    }
    
    
    
    //==========================
    //  PRIVATE METHODS
    //==========================
    
    
    
    /**
     * Método encargado de scanear completamente un directorio (archivos y subdirectorios)
     * de manera recursiva
     * @param dir directorio a escanear
     * @param fileList
     */
    private void scan( File dir, List<File> fileList ){
        
        //Agrega directorio actual
        fileList.add( dir );
        
        //Recorriendo de directorio
        for (File file : dir.listFiles() ) {
            //Si es directorio, se escanea recursivamente
            if( file.isDirectory() )
                scan( file, fileList );
            //Agregando a lista elemento actual dentro del directorio (si no esta agregado)
            if( !fileList.contains(file) )
                fileList.add( file );
        }
        
    }
    
    
    /**
     * Método que permite copiar un directorio de forma recursiva 
     * con su estructura completa (archivos) escaneando cada subdirectorio 
     * y copiando cada archivo individualmente
     * @param from directorio base
     * @param to directorio destino
     * @param onlyContent TRUE si se desea copiar sólo el contenido, FALSE si se quiere
     * copiar también el directorio de origen (base)
     * @return 
     */
    private boolean copyAllDirectory( File from, File to) throws FileException {
        
        //Se obtiene lista de elementos
        File[] elementos = getElements(from);
        
        for (File item : elementos) {
            //Si es un directorio, se crea
            if( item.isDirectory() ){
                //Directorio nuevo en base a elemento
                File dir = copyDirectoryName(item, to);
                //Se crea directorio
                try {
                    createDirectory( dir );
                } catch (FileException e) {
                    //Si no se crea directorio actual
                }
                //se repite proceso de forma recursiva para nuevo directorio
                copyAllDirectory(item, dir);
            }
            else
                try {
                    fileManager.copyFile(item, copyDirectoryName(item, to));
                } catch (FileException | IOException ex) {
                    //Si no se copio archivo actual
                }
        }
        
        
        return false;
    }
    
    
    
    /**
     * Método que elimina un directorio eliminando archivos dentro
     * recursivamente antes de eliminar directorio base.
     * Primero se obtiene lista del directorio, 
     * si la lista es null, se elimina directorio base.
     * Si la lista no es null, se recorre recursivamente eliminando archivos
     * Si se encuentra otro directorio, se reptite proceso.
     * Al final, cuando no quede nada, se elimina directorio base
     * @param dir directorio (direccion absoluta)
     * @return TRUE si se elimino, FALSE si no
     */
    private boolean deleteAllDirectory( File dir ) throws FileException{
        
        //Se obtiene lista
        File[] elementos = dir.listFiles();
        
        //Si la lista esta vacia (archivos eliminados) se elimina directorio
        if( elementos.length > 0 ){
            //Se recorre lista eliminando elementos
            for (File item : elementos) {
                //Si es archivo
                if( !item.isDirectory() ){
                    fileManager.deleteFile(item);
                }
                //Si es un directorio, se repire proceso
                else{
                    deleteAllDirectory( item );
                }
            }
        }
        
        //Se elimina directorio al final (cuando directorio ya esta vacio)
        return fileManager.deleteFile( dir );
    }
    
    
    
    
    
    /**
     * Crear un nuevo directorio con el direcorio actual y el directorio
     * al que se desea acceder
     * @param src directorio actual
     * @param to nuevo directorio
     * @return objeto FILE del nuevo directorio
     */
    private File copyDirectoryName(File src, File to){
        return new File( to.getAbsolutePath()+"/"+src.getName() );
    }
    
    
    /**
     * Regresa la lista de elemento de un directorio
     * @param dir directorio
     * @return Arreglo tipo File
     * @throws FileException Si no es directorio, si el directorio no existe
     */
    public List<File> getElementsList(File dir) throws FileException{
        if( !dir.isDirectory() )
            throw new FileException("No es directorio");
        
        if( !dir.exists() )
            throw new FileException("No existe directorio");
        
        return Arrays.asList( dir.listFiles() );
    }
    
    
    /**
     * Regresa un arreglo de elemento de un directorio
     * @param dir directorio
     * @return Arreglo tipo File
     * @throws FileException Si no es directorio, si el directorio no existe
     */
    public File[] getElements(File dir) throws FileException{
        if( !dir.isDirectory() )
            throw new FileException("No es directorio");
        
        if( !dir.exists() )
            throw new FileException("No existe directorio");
        
        return dir.listFiles();
    }
    
}
