package sm.ffm.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Operación de diseño propio aplicada componente a componente.
 * La clase hereda de BufferedImageOpAdapter y sobrecarga el método filter.
 * @author Francisco Fernández Millán
 */
public class MyOpPropia extends BufferedImageOpAdapter{
    
    private int tipoDisfunsionVisual;
    
    /**
     * Constructor de la operación propia que inicializa el tipo de 
     * disfunsión visual según el parámetro que pase en la llamada
     * a la clase.
     * @param tipo, tipo de disfunsión visual. 
     */
    public MyOpPropia(int tipo){
        this.tipoDisfunsionVisual = tipo;
    }

    /**
     * En este método nos encargaremos de recorrer componente a componente la imagen 
     * obteniendo el valor RGD de cada uno y en función del tipo de 
     * disfunsión que se quiera realizar, reduciremos el color en cuestión.
     * @param src, imagen fuente.
     * @param dest, imagen destino.
     * @return BufferedImage, devuelve la imagen modificada.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {    
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
               
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
               
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) { 
                Color c1 = new Color(src.getRGB(x, y));
                
                switch (this.tipoDisfunsionVisual) {
                    case 0:
                        {
                            int R = c1.getRed();
                            int G = (int) (c1.getGreen() * 0.8);
                            int B = c1.getBlue();
                            Color c2 = new Color(R, G, B);
                            src.setRGB(x, y, c2.getRGB());
                            break;
                        }
                    case 1:
                        {
                            int R = (int) (c1.getRed() * 0.8);
                            int G = c1.getGreen();
                            int B = c1.getBlue();
                            Color c2 = new Color(R, G, B);
                            src.setRGB(x, y, c2.getRGB());
                            break;
                        }
                    case 2:
                        {
                            int R = c1.getRed();
                            int G = c1.getGreen();
                            int B = (int) (c1.getBlue() * 0.8);
                            Color c2 = new Color(R, G, B);
                            src.setRGB(x, y, c2.getRGB());
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        return dest; 
    
    }    
}



///////////////////////////////////////////

//        WritableRaster srcRaster = src.getRaster();
//        WritableRaster destRaster = dest.getRaster();
//        int[] pixelComp = null;
//        
//        for (int x = 0; x < src.getWidth(); x++) {
//            for (int y = 0; y < src.getHeight(); y++) { 
//                pixelComp = srcRaster.getPixel(x, y, pixelComp);
//                
//                int sepiaR = (int) Math.min(255, 1*pixelComp[0] + 0*pixelComp[1] + 0*pixelComp[2]);
//                int sepiaG = (int) Math.min(255, 0*pixelComp[0] + 0.5*pixelComp[1] + 0*pixelComp[2]);
//                int sepiaB = (int) Math.min(255, 0*pixelComp[0] + 0*pixelComp[1] + 1*pixelComp[2]);
//
//                pixelComp[0] = sepiaR;
//                pixelComp[1] = sepiaG;
//                pixelComp[2] = sepiaB;
//                
//                destRaster.setPixel(x, y, pixelComp);
//            }
//        }
//        return dest; 
