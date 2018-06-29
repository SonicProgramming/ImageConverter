package imageconverter.conv;

/**
 * @author 123
 */
//Not sure why
public final class UnsupportedImageFormatException extends Throwable{
    
    private String wtf = "no info given";
    
    public UnsupportedImageFormatException(String wtfinfo){
        wtf = wtfinfo;
    }
    
    public String getInfo(){
        return wtf;
    }
}
