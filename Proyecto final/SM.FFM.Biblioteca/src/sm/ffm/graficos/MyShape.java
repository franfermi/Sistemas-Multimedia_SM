package sm.ffm.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 * Superclase abstracta MyShape de la cual heredan las distintas formas de dibujo.
 * @author Francisco Fernández Millán
 */
public abstract class MyShape{
    
    Color colorTrazo;
    int nivelGrosor;
    float nivelTransparencia;
    boolean transparencia, alisar;
    boolean trazoPunteado;
    boolean editar;
    Stroke trazo;
    Composite comp;
    RenderingHints render =  new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    Shape formaDibujo;
    Point2D pEdit1, pEdit2;
    String txtInsertar;
    Point2D pInsertarTexto;

    /**
     * Método que se encarga de devolver el color del trazo de la figura.
     * @return Color, color del trazo.
     */
    public Color getColorTrazo(){
        return this.colorTrazo;
    }
    
    /**
     * Método que se encarga de modificar el color del trazo de la figura.
     * @param color, nuevo color del trazo. 
     */
    public void setColorTrazo(Color color){
        this.colorTrazo = color;
    }
         
    /**
     * Método que se encarga de devolver el nivel de grosor de la figura.
     * @return int, entero que representa el nivel de grosor.
     */
    public int getGrosor(){
        return this.nivelGrosor;
    }
    
    /**
     * Método que se encarga de modificar la propiedad Stroke del trazo de la figura.
     * @param grosor, nuevo Stroke del grosor. 
     */
    public void setGrosorTrazo(Stroke grosor){
        this.trazo = grosor;
    }
    
    /**
     * Método que se encarga de modificar el nivel de grosor de la figura.
     * @param grosor, nuevo nivel de grosor. 
     */
    public void setGrosor(int grosor){
        this.nivelGrosor = grosor;
    }

    /**
     * Método que se encarga de devolver el nivel de transparencia de la figura.
     * @return float, flotante que representa el nivel de transparencia.
     */
    public float getNivelTransparencia() {
        return nivelTransparencia;
    }

    /**
     * Método que se encarga de modificar el nivel de transparencia de la figura.
     * @param nivelTransparencia, nuevo nivel de transparencia.
     */
    public void setNivelTransparencia(float nivelTransparencia) {
        this.nivelTransparencia = nivelTransparencia;
    }
       
    /**
     * Método que se encarga de devolver si está activada o no la transparencia de la figura.
     * @return true, para transparencia activa. false, para transparencia desactivada.
     */
    public boolean getTransparencia(){
        return this.transparencia;
    }
    
    /**
     * Método que se encarga de modificar el estado activado/desactivado de la transparencia.
     * @param transparencia, nuevo estado de la transparencia. 
     */
    public void setTransparencia(boolean transparencia){
        this.transparencia = transparencia;
    }

    /**
     * Método que se encarga de devolver si está activada o no el alisado de la figura.
     * @return true, para alisado activo. false, para alisado desactivado.
     */
    public boolean getAlisar() {
        return alisar;
    }
    
    /**
     * Método que se encarga de modificar el estado activado/desactivado del alisado.
     * En caso de estar activo, se inicializa con los valores adecuados para el alisado.
     * En caso de estar desactivado, se borra la configuración del objeto. 
     * @param alisar, nuevo estado del alisado. 
     */
    public void setAlisar(boolean alisar) {
        this.alisar = alisar;
        if(this.alisar)
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            render.clear();
    }
    
    /**
     * Método que se encarga de devolver si está activado o no el trazo discontinuo de la figura.
     * @return true, para discontinuidad activa. false, para discontinuidad desactivada.
     */
    public boolean getTazoPunteado(){
        return this.trazoPunteado;
    }
    
    /**
     * Método que se encarga de modificar el estado activado/desactivado del trazo discontinuo.
     * @param trazo, nuevo valor del trazo discontinuo. 
     */
    public void setTrazoPunteado(boolean trazo){
        this.trazoPunteado = trazo;
    }
    
    /**
     * Método que se encarga de inicializar el objeto Stroke del trazado según si 
     * se encuentra o no activado el trazo continuo o discontinuo.
     */
    public void setTipoTrazo(){
        if(this.getTazoPunteado()){
            trazo = new BasicStroke(this.getGrosor(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                                1f, new float[] {5, 5, 5, 5}, 2);
        }else
            trazo = new BasicStroke(this.nivelGrosor);
    }
    
    /**
     * Método que se encarga de devolver el rectangulo que encierra la figura, posteriormente se mostrará como el
     * marco de selección de cada figura.
     * @return Rectangle, rectángulo que encienta la figura.
     */
    public Rectangle getBounds() {
        Rectangle bounds = formaDibujo.getBounds();
        bounds.setRect(bounds.getX() - 10, bounds.getY() - 10, bounds.getWidth() + 20, bounds.getHeight() + 20);
        return bounds;
    }
    
    /**
     * Método abstracto que se encarga de modificar la diagonal de una figura.
     * Es usado en el evento Dragged del ratón para dibujar la figura mientras arrastramos.
     * @param p1, punto de inicio.
     * @param p2, punto de fin.
     */
    public abstract void setCoordenadas(Point2D p1, Point2D p2);
        
    /**
     * Método abstracto que se encarga de modificar la posición de una figura.
     * @param p1, coordenada X e Y del punto inicial.
     * @param p2, coordenada X e Y del punto final. 
     */   
    public abstract void setLocation(Point2D p1, Point2D p2);
    
    /**
     * Método abstracto que se encarga de pintar la figura según los atributos activos.
     * Se ha considerado abstracto porque no es común para todas las figuras.
     * @param g2d, objeto de la clase abstracta Graphics. Se encargará de dibujar y modificar 
     * las propiedades de las figuras.
     */
    public abstract void paint(Graphics2D g2d);
}
