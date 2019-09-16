package sm.ffm.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Operación de filtro sepia.
 * La clase hereda de BufferedImageOpAdapter y sobrecarga el método filter.
 * @author Francisco Fernández Millán
 */
public class SepiaOp extends BufferedImageOpAdapter{

    /**
     * En este método nos encargamos de recorrer pixel a pixel la imagen
     * obteniendo mediente raster los componentes del pixel ya que en el
     * resultado final influirán todos los componentes y son necesarios los
     * valores de cada uno de ellos en cada componente.
     * Se basa en una matriz de tonos marrones que irán modificando
     * pixel a pixel su tonalidad para obtener un resultado final de un filtro
     * como si de una imagen vieja se tratara.
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
        
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = null;
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) { 
                pixelComp = srcRaster.getPixel(x, y, pixelComp);
                
                int sepiaR = (int) Math.min(255, 0.393*pixelComp[0] + 0.769*pixelComp[1] + 0.189*pixelComp[2]);
                int sepiaG = (int) Math.min(255, 0.349*pixelComp[0] + 0.686*pixelComp[1] + 0.168*pixelComp[2]);
                int sepiaB = (int) Math.min(255, 0.272*pixelComp[0] + 0.534*pixelComp[1] + 0.131*pixelComp[2]);

                pixelComp[0] = sepiaR;
                pixelComp[1] = sepiaG;
                pixelComp[2] = sepiaB;
                
                destRaster.setPixel(x, y, pixelComp);
            }
        }
        return dest; 
    }
}