
package manager;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


class ChooserManager {
    
    private static JFileChooser chooser;
    private static FileNameExtensionFilter filter;
    private static final boolean ACCEPT_MULTI_SELECTION = true;
    private static final boolean NO_ACCEPT_MULTI_SELECTION = false;
    private static final File ROOT_DIRECTORY = new File(".");

    public ChooserManager() {
    }
    
    
    
    
//    private int save(){
//        if( chooser != null )
//            return chooser.showSaveDialog(chooser);
//
//    }
    
    
    /**
     * Método encargado de abrir el JFileChooser para seleccionar el/los elementos
     * deseados
     * @param location localizacion donde se abrira el JFileChooser
     * @param title titulo de la ventana 
     * @param selectionMode modo de seleccion, solo directorios, archivos o ambos
     * @see JFileChooser#DIRECTORIES_ONLY
     * @see JFileChooser#FILES_ONLY
     * @see JFileChooser#FILES_AND_DIRECTORIES
     * @param multiSelectionMode
     * @see #ACCEPT_MULTI_SELECTION
     * @see #NO_ACCEPT_MULTI_SELECTION
     * @param filteres filtros para archivos, Ejemplo:
     * <ul>
     *  <li>.mp3</li>
     *  <li>.jpg</li>
     *  <li>.png</li>
     * </ul>
     * @return 
     */
    private File openChooser( File location, String title, int selectionMode, 
            boolean multiSelectionMode, String... filteres ){
        
        
        
        chooser = new JFileChooser(); 
        chooser.setDialogTitle( title );
        //Si se selecciono un directorio
        if( location != null && location.isDirectory() )
            chooser.setCurrentDirectory( location );
        else
            chooser.setCurrentDirectory( ROOT_DIRECTORY );
        
        //Excepciones & Filtros
        chooser.setFileSelectionMode( selectionMode );
        //Miltiseleccion
        //chooser.setMultiSelectionEnabled( multiSelectionMode );
        
        //Filtro solo si esta definido
        if( filteres != null ){
            filter = new FileNameExtensionFilter("Archivos permitidos", filteres);
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed( true );
        }
        else
            chooser.setAcceptAllFileFilterUsed( false );
        
        //Comprobar respuesta (Se cerro ventana o seleccionó directorio)
        File file = null;
        if( chooser.showOpenDialog( chooser ) == JFileChooser.APPROVE_OPTION ){
            //Directorio seleccionado (si no, directorio actual)
            file = chooser.getSelectedFile();
        }
        else
            System.out.println("No se selecciono directorio");
        
        return file;
    }
    
    
    public File saveChooser( String title ){
        
        chooser = new JFileChooser(); 
        chooser.setDialogTitle( title );
        //Si se selecciono un directorio
        chooser.setCurrentDirectory( ROOT_DIRECTORY );
        
        //Excepciones & Filtros
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
        
        //Comprobar respuesta (Se cerro ventana o seleccionó directorio)
        File file = null;
        if( chooser.showOpenDialog( chooser ) == JFileChooser.APPROVE_OPTION ){
            //Directorio seleccionado (si no, directorio actual)
            file = chooser.getSelectedFile();
        }
        else
            System.out.println("No se selecciono directorio");
        
        return file;
    }
    
    
    
    /**
     * Permite seleccionar un directorio
     * @param location ubicacion del chooser
     * @param title titulo del chooser
     * @return FILE del directorio, NULL si no se selecciono
     */
    public File selectDirectory(File location, String title){
        return openChooser(location, title, JFileChooser.DIRECTORIES_ONLY, NO_ACCEPT_MULTI_SELECTION, (String[]) null);
    }
    
    /**
     * Método que permite selecciona un archivo
     * @param location ubicacion del chooser
     * @param title titulo de la ventana
     * @return Archivo seleccionado, null si no hay seleccion
     */
    public File selectFile( File location, String title ){
        return openChooser(location, title, JFileChooser.FILES_ONLY, NO_ACCEPT_MULTI_SELECTION, (String[]) null);
    }
    
    
    
}
