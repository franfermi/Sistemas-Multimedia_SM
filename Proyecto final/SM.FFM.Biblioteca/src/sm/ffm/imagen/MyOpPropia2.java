package sm.ffm.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Operación de diseño propio aplicada pixel a pixel.
 * La clase hereda de BufferedImageOpAdapter y sobrecarga el método filter.
 * @author Francisco Fernández Millán
 */
public class MyOpPropia2 extends BufferedImageOpAdapter{

    /**
     * En este método nos encargamos de recorrer pixel a pixel la imagen
     * obteniendo mediente raster los componentes del pixel ya que en el
     * resultado final influirán todos los componentes y son necesarios los
     * valores de cada uno de ellos en cada componente.
     * Se basa en una matriz de tonos grisaceos/azulados que irán modificando
     * pixel a pixel su tonalidad para obtener un resultado final de un filtro
     * frío/congelado.
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
                                
                int congR = (int) Math.min(255, 0.54*pixelComp[0] + 0.43*pixelComp[1] + 0.50*pixelComp[2]);
                int congG = (int) Math.min(255, 0.69*pixelComp[0] + 0.65*pixelComp[1] + 0.55*pixelComp[2]);
                int congB = (int) Math.min(255, 0.93*pixelComp[0] + 0.94*pixelComp[1] + 0.92*pixelComp[2]);

                pixelComp[0] = congR;
                pixelComp[1] = congG;
                pixelComp[2] = congB;
                
                destRaster.setPixel(x, y, pixelComp);
            }
        }
        return dest; 
    }
}
