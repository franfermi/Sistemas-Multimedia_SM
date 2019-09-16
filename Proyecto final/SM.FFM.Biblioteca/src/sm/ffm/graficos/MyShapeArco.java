package sm.ffm.graficos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase propia que representa la forma de dibujo Arco.
 * Hereda de la clase abstracta intermedia MyPaintConRelleno que se encargará de pintar la figura,
 * esta a su vez heredará de MyRelleno que contendrá las propiedades del relleno y esta última
 * de la super clase MyShape.
 * @author Francisco Fernández Millán
 */
public class MyShapeArco extends MyPaintConRelleno{

    /**
     * Constructor de la clase MyShapeArco que inicializa el objeto de tipo formaDibujo 
     * de la superclase MyShape como Arc2D.
     * @param p1, coordenada X e Y donde se crea la figura.
     * @param cTrazo, color del trazo de dibujo.
     * @param tPunteado, representa si está activado/desactivado el trazo punteado.
     * @param nGrosor, nivel de grosor del trazado.
     * @param transp, representa si está activado/desactivado el modo transparencia.
     * @param nTrasp, nivel de transparencia en caso de estar activa la transparencia. 
     * @param alisar, representa si está activado/desactivado el modo alisado.
     * @param cDegFr, color del degradado de frente.
     * @param cDegFo, color del degradado de fondo.
     * @param degV, representa si está activado/desactivado el degradado vertical.
     * @param degH, representa si está activado/desactivado el degradado horizontal.
     * @param degD, representa si está activado/desactivado el degradado diagonal.
     * @param relleno, representa si está activado/desactivado el relleno liso.
     * @param cRelleno, color del relleno liso. 
     */
    public MyShapeArco(Point2D p1, Color cTrazo, boolean tPunteado,
            int nGrosor, boolean transp, float nTrasp, boolean alisar, Color cDegFr, Color cDegFo,
            boolean degV, boolean degH, boolean degD, boolean relleno, Color cRelleno){
        
        this.formaDibujo = new Arc2D.Double(p1.getX(), p1.getY(), 0.0D, 0.0D, 90.0D, 135.0D, Arc2D.CHORD);
        
        this.setColorTrazo(cTrazo);
        this.setTrazoPunteado(tPunteado);
        this.setGrosor(nGrosor);
        this.setTransparencia(transp);
        this.setNivelTransparencia(nTrasp);
        this.setAlisar(alisar);
        this.setColorDegFrente(cDegFr);
        this.setColorDegFondo(cDegFo);
        this.setDegradadoV(degV);
        this.setDegradadoH(degH);
        this.setDegradadoD(degD);
        this.setRelleno(relleno);
        this.setColorRelleno(cRelleno);
    }
    
    /**
     * Método que se encarga de modificar la diagonal de una figura, en este caso de Arc2D.
     * Es usado en el evento Dragged del ratón para dibujar la figura mientras arrastramos.
     * @param p1, punto de inicio.
     * @param p2, punto de fin.
     */
    @Override
    public void setCoordenadas(Point2D p1, Point2D p2) {
        this.pEdit1 = p1;
        this.pEdit2 = p2;
        ((Arc2D)this.formaDibujo).setFrame(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Método que se encarga de modificar la posición de una figura, en este caso de Arc2D.
     * @param p1, coordenada X e Y del punto inicial.
     * @param p2, coordenada X e Y del punto final. 
     */  
    @Override
    public void setLocation(Point2D p1, Point2D p2) {
        double x1 = this.pEdit1.getX()+(p2.getX()-p1.getX());
        double y1 = this.pEdit1.getY()+(p2.getY()-p1.getY());
        double x2 = this.pEdit2.getX()+(p2.getX()-p1.getX());
        double y2 = this.pEdit2.getY()+(p2.getY()-p1.getY());
        
        ((Arc2D)this.formaDibujo).setFrame(x1, y1, x2, y2);
    }    
}
