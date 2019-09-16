/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ffm.iu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Clase lienzo imagen que hereda de lienzo y ofrece la posibilidad de poder
 * añadir imágenes al JPanel del lienzo al que hereda.
 * Esta clase se encargará de la representación de imágenes en la clase lienzo.
 * @author Francisco Fernández Millán
 */
public class LienzoImagen2D extends Lienzo2D{
    
    private BufferedImage img, imgEditada;
    
    /**
     * Método que se encarga de modificar la imagen en el lienzo,
     * la imagen que se añada contendrá sus propias dimensiones.
     * @param img, nueva imagen. 
     */
    public void setImage(BufferedImage img){
        this.img = img;
        if(img!=null) 
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
    }
    
    /**
     * Método que se encarga de devolver la imagen que se encuentra 
     * mostrada en el lienzo.
     * @return BufferedImage, imagen que se devuelve.
     */
    public BufferedImage getImage(){
        return img;
    }
    
    /**
     * Método que se encarga de devolver la imagen al igual que el anterior
     * método pero con el añadido de que se han podido dibujar sobre ella
     * las distintas formas de dibujo.
     * Es usado en el método guardar de la ventana principal para que se 
     * guarde la imagen junto con los dibujos realizados en ella.
     * @param drawShape, variable de estado que indicará que se va a guardar
     * la imagen con las figuras dibujas en ella.
     * @return BufferedImage, imagen con su edición.
     */
    public BufferedImage getImage(boolean drawShape){
        if (drawShape){
            imgEditada = new BufferedImage(this.img.getWidth(), this.img.getHeight(), this.img.getType());
            paint(imgEditada.createGraphics());
            return imgEditada;
        }
            return getImage();
    }
    
    /**
     * Sobrecarga de método paintComponent que se encargará de visualizar la imagen
     * y el vector de formas.
     * @param g, objeto Graphics. 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(img!=null) g.drawImage(img,0,0,this);
    }
}
