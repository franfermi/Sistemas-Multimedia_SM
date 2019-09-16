/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ffm.graficos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase propia que representa la forma de dibujo Linea o Punto.
 * Hereda de la clase abstracta intermedia MyPaintSinRelleno que se encargará de pintar la figura,
 * esta a su vez heredará de la super clase MyShape.
 * @author Francisco Fernández Millán
 */
public class MyShapeLinea extends MyPaintSinRelleno{
   
    /**
     * Constructor de la clase MyShapeLinea que inicializa el objeto de tipo formaDibujo 
     * de la superclase MyShape como Line2D.
     * @param p1, coordenada X e Y donde se crea la figura.
     * @param p2, coordenada X e Y hasta donde termina la figura. Para el caso del punto,
     * p1 y p2 serán iguales.
     * @param cTrazo, color del trazo de dibujo.
     * @param tPunteado, representa si está activado/desactivado el trazo punteado.
     * @param nGrosor, nivel de grosor del trazado.
     * @param transp, representa si está activado/desactivado el modo transparencia.
     * @param nTrasp, nivel de transparencia en caso de estar activa la transparencia. 
     * @param alisar, representa si está activado/desactivado el modo alisado.
     */
    public MyShapeLinea (Point2D p1, Point2D p2, Color cTrazo, boolean tPunteado,
            int nGrosor, boolean transp, float nTrasp, boolean alisar){
        
        this.formaDibujo = new Line2D.Float(p1, p2);
        
        this.setColorTrazo(cTrazo);
        this.setTrazoPunteado(tPunteado);
        this.setGrosor(nGrosor);
        this.setTransparencia(transp);
        this.setNivelTransparencia(nTrasp);
        this.setAlisar(alisar);
    }
    
    /**
     * Método abstracto que se encarga de modificar la diagonal de una figura, en este caso de Line2D.
     * Es usado en el evento Dragged del ratón para dibujar la figura mientras arrastramos.
     * @param p1, punto de inicio.
     * @param p2, punto de fin.
     */
    @Override
    public void setCoordenadas(Point2D p1, Point2D p2) {
        this.pEdit1 = p1;
        this.pEdit2 = p2;
        ((Line2D)this.formaDibujo).setLine(p1, p2);
    }
    
    /**
     * Método que se encarga de modificar la posición de una figura, en este caso de Line2D.
     * @param p1, coordenada X e Y del punto inicial.
     * @param p2, coordenada X e Y del punto final. 
     */  
    @Override
    public void setLocation(Point2D p1, Point2D p2) {
        double x1 = this.pEdit1.getX()+(p2.getX()-p1.getX());
        double y1 = this.pEdit1.getY()+(p2.getY()-p1.getY());
        double x2 = this.pEdit2.getX()+(p2.getX()-p1.getX());
        double y2 = this.pEdit2.getY()+(p2.getY()-p1.getY());
        
        ((Line2D)this.formaDibujo).setLine(x1, y1, x2, y2);
    }
}
