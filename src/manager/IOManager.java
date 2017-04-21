
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


class IOManager {
    
    //Variables de lectura/escritura
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    //Archivo asingado
    private File file;

    public IOManager() {}

    public IOManager(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
    //============================= MÉTODOS
    
    /**
     * Método que escribe en un archivo
     * @return
     */
    public boolean writeFile(String text){
        if( file.exists() ){
            try {
                fw = new FileWriter( file );
                pw = new PrintWriter( fw );
                //Escribe
                pw.println(text);
                
            } catch (IOException ex) {
                mensaje("ERROR", "No se encontro el archivo", JOptionPane.ERROR_MESSAGE);
            }finally{
                closeFileWriter();
            }
            
        }
        return false;
    }
    
    
    /**
     * Método que lee un archivo
     * @return String de la lectura del archivo
     */
    public String readFile(){
        String read = null;
        String line;
        
        if( file.exists() ){
            try {
                fr = new FileReader ( getFile() );
                br = new BufferedReader(fr);
                while( (line = br.readLine() ) != null ){
                    read += line;
                }
            } 
            catch (FileNotFoundException ex) {
                mensaje("ERROR", "No se encontro el archivo", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                mensaje("ERROR", "No se pudo leer archivo", JOptionPane.ERROR_MESSAGE);
            }finally{
                closeFileReader();
            }
            
        }
        
        return read;
    }
    
    /**
     * Cierre de los metodos de escritura
     */
    private void closeFileWriter(){
        try {
            fw.close();
            bw.close();
        } catch (IOException ex) {
            mensaje("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE );
        }
    }
    
    /**
     * Cierre de los metodos de lectura
     */
    private void closeFileReader(){
        try {
            fr.close();
            br.close();
        } catch (IOException ex) {
            mensaje("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE );
        }
    }
    
    
    
    private void mensaje(String titulo, String mensaje, int tipo){
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
    }
    
}
