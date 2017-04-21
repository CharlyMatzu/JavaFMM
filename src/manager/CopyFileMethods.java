package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;


class CopyFileMethods {

    public CopyFileMethods() {
    }
    
    
    
    public boolean copyFileUsingStream(File source, File dest) throws FileNotFoundException, IOException{
        InputStream is;
        OutputStream os;
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
        return true;
    }
    
    
    private  void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel;
        FileChannel destChannel;
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        destChannel.close();
    }
    
    
    private  void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }
    
    
    private  void copyFileUsingJava7Files(File source, File dest) throws IOException{
        Files.copy(source.toPath(), dest.toPath());
    }
    
    
    
}