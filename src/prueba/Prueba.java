
package prueba;

import exepciones.FileException;
import interfaz.IManager;
import manager.FManager;
import java.io.File;


class Prueba {
    
    private static void main(String[] args) {
        
        IManager man = new FManager();
        File origen = new File("D:/img");

        try {
            for (File file : man.escanearDirectorio(origen)) {
                if( file.isFile() )
                    System.out.print("\t");
                System.out.println( file );
            }
        } catch (FileException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
