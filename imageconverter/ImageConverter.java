package imageconverter;

import imageconverter.ui.MainWindow;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 123
 */

public class ImageConverter {
    
    public static String WSTYLE_CURRENT = "Nimbus";
    public static MainWindow mw = null;
    
    public static void main(String args[]){
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/style"));
            WSTYLE_CURRENT = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainWindow.wmain(args);
    }
}
