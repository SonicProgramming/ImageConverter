package imageconverter.conv;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 * @author Sonic
 */
public class Conv {
    
    public static final Map<ConvConstants, String> FORMATS;
    static {
        Map<ConvConstants, String> formats = new HashMap<>();
        formats.put(ConvConstants.FORMAT_BMP, ".bmp");
        formats.put(ConvConstants.FORMAT_GIF, ".gif");
        formats.put(ConvConstants.FORMAT_JPEG, ".jpg");
        formats.put(ConvConstants.FORMAT_PNG, ".png");
        formats.put(ConvConstants.FORMAT_WBMP, ".wbmp");
        FORMATS = Collections.unmodifiableMap(formats);
    }
    
    private File initialImage;
    private File outputImage;
    private ConvConstants currentFormat;
    
    private BufferedImage currentImage;
    
    private boolean ready = false;
    
    /**
     * @throws FileNotFoundException in case image could not be found.*
     * @throws UnsupportedImageFormatException if the image format could not be recognized *
     * @param image The image to associate this Conv with. Must be one of the formats from ConvConstants.*
     */
    
    public Conv(File image) throws FileNotFoundException, UnsupportedImageFormatException{
        if(image.exists()) openFile(image);
        else throw new FileNotFoundException();
    }
    
    /**
     * @throws FileNotFoundException in case image could not be found.*
     * @throws UnsupportedImageFormatException if the image format could not be recognized *
     * @param imagePath The image to associate this Conv with. Must be one of the formats from ConvConstants. *
     * 
     */
    public Conv(String imagePath) throws FileNotFoundException, UnsupportedImageFormatException{
        File f = new File(imagePath);
        if(f.exists()) openFile(f);
        else throw new FileNotFoundException();
        
    }
    
    /**
     * 
     * @param img The image to associate this Conv with *
     * @param format The image format *
     * With this constructor you can handle image opening process yourself. Useful if you use custom image readers.
     */
    public Conv(BufferedImage img, ConvConstants format){
        if(img != null && format != null)
            if(format != ConvConstants.FORMAT_UNKNOWN) {
                currentImage = img;
                currentFormat = format;
                ready = true;
            }
                
    }
    
    private void openFile(File f) throws FileNotFoundException, UnsupportedImageFormatException{
        try {
            initialImage = f;
            currentFormat = checkImageFormat(f);
            InputStream is = new FileInputStream(f);
            BufferedImage buf = ImageIO.read(is);
            if(buf != null) ready = true;
            else throw new IOException();
            currentImage = buf;
            ready = true;
            if(currentFormat == ConvConstants.FORMAT_JPEG) fixImage();
        } catch (IOException ex) {
            Logger.getLogger(Conv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ConvConstants checkImageFormat(File f) throws UnsupportedImageFormatException{
        String name = f.getName();
        ConvConstants cnst = ConvConstants.FORMAT_UNKNOWN;
        String tosw = name.substring(name.lastIndexOf(".")).toLowerCase();
        switch(tosw){
            case ".bmp":
                cnst = ConvConstants.FORMAT_BMP;
                break;
            case ".gif":
                cnst = ConvConstants.FORMAT_GIF;
                break;
            case ".jpg":
            case ".jpeg":
                cnst = ConvConstants.FORMAT_JPEG;
                break;
            case ".png":
                cnst = ConvConstants.FORMAT_PNG;
                break;
            case ".wbmp":
                cnst = ConvConstants.FORMAT_WBMP;
                break;
            default:
                throw new UnsupportedImageFormatException("Unknown format: "+tosw);
        }
        return cnst;
    }
    
    
    private void fixImage(){
        int type = currentImage.getType();
        System.out.println(type);
    }
    
    //Fix a BufferedImage bug, that causes the image to incorrectly apply red color filter to a correct image
    //This happens when converting 4-channel image to 4-channel JPEG, which is written with alpha channel while most apps see it as CMYK image
    //More details here: https://bugs.openjdk.java.net/browse/JDK-8041459
    private void fixImageOnSave(){
        BufferedImage bi = new BufferedImage(currentImage.getWidth(), currentImage.getHeight(), BufferedImage.TYPE_INT_RGB); //Create image of same size, but different type
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(currentImage, 0, 0, null);
        currentImage = bi;
    }
    
    /**
     * Resaves your image with a different format. Path and name are not changed.
     * @param format The image format you want to convert your image to. Note that in case it's the same as original it will be overwritten.
     * @see convert()
     */
    
    public void resave(ConvConstants format){
        if(currentFormat == ConvConstants.FORMAT_PNG && format == ConvConstants.FORMAT_JPEG) fixImageOnSave();
        String path = initialImage.getAbsolutePath();
        String b1 = path.substring(0, path.lastIndexOf("."));
        File savePath = new File(b1 + FORMATS.get(format));
        if(savePath.exists()) savePath.delete();
        try {
            //ImageOutputStream ios = new ImageOutputStream(savePath);
            ImageIO.write(currentImage, FORMATS.get(format).substring(1), savePath);
        } catch (IOException ex) {
            Logger.getLogger(Conv.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Converts the image associated with this Conv object to a new format.
     * @param format New image format
     */
    //Yeah, it says convert, while it just changes a variable
    public void convert(ConvConstants format){
        if(currentFormat == ConvConstants.FORMAT_PNG && format == ConvConstants.FORMAT_JPEG) fixImageOnSave();
        if(format != ConvConstants.FORMAT_UNKNOWN) currentFormat = format;
    }
    
    /**
     * Saves the image associated with this Conv object at the same place and with the same name as the given image.
     */
    public void save(){
        String path = initialImage.getAbsolutePath();
        String b1 = path.substring(0, path.lastIndexOf("."));
        File savePath = new File(b1 + FORMATS.get(currentFormat));
        if(savePath.exists()) savePath.delete();
        try {
            ImageIO.write(currentImage, FORMATS.get(currentFormat).substring(1), savePath);
        } catch (IOException ex) {
            Logger.getLogger(Conv.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Saves the image associated with this Conv object.
     * @param path Where to save the image.
     */
    public void save(File path){
        if(path.exists()) path.delete();
        try {
            System.out.println(FORMATS.get(currentFormat).substring(1));
            ImageIO.write(currentImage, FORMATS.get(currentFormat).substring(1), path);
        } catch (IOException ex) {
            Logger.getLogger(Conv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return BufferedImage, associated with this Conv object.
     */
    public BufferedImage getImage(){
        return currentImage;
    }
    
    /**
     * @return Image format of the image, associated with this Conv object.
     */
    public ConvConstants getImageFormat(){
        return currentFormat;
    }
}
