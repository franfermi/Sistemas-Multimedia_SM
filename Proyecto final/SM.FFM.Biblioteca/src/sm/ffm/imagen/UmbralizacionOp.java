/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ffm.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Operación de filtro umbralización.
 * La clase hereda de BufferedImageOpAdapter y sobrecarga el método filter.
 * @author Francisco Fernández Millán
 */
public class UmbralizacionOp extends BufferedImageOpAdapter{
    private int umbral;

    /**
     * Constructor de la operación propia que inicializa el umbral
     * según el parámetro que pase en la llamada a la clase.
     * @param umbral, umbral que se aplicará al filtro. 
     */
    public UmbralizacionOp(int umbral) {
        this.umbral = umbral;
    }   
    
    /**
     * En este método nos encargaremos de recorrer componente a componente la imagen 
     * obteniendo el valor RGD de cada uno, a partir del valor obtenido, se crea un
     * nuevo color para posteriormente calcular la media a partir de cada una de
     * sus componentes de color.
     * Según el umbral establecido, para valores de media superiores al umbral,
     * se modificará el color a tonos blancos y para valores por debajo del
     * umbral, con tonos negros.
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
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) { 
                int RGB = src.getRGB(x, y);
                Color c = new Color(RGB);
                float mediaComp = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                
                if(mediaComp >= umbral){
                    c = new Color(Color.WHITE.getRGB());
                    dest.setRGB(x, y, c.getRGB());
                }else if(mediaComp < umbral){
                    c = new Color(Color.BLACK.getRGB());
                    dest.setRGB(x, y, c.getRGB());
                }                
            }
        }
        return dest; 
    } 
}
