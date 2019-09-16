package sm.ffm.iu;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import sm.ffm.eventos.LienzoEvent;
import sm.ffm.eventos.LienzoListener;
import sm.ffm.graficos.MyShape;
import sm.ffm.graficos.MyShapeArco;
import sm.ffm.graficos.MyShapeElipse;
import sm.ffm.graficos.MyShapeLinea;
import sm.ffm.graficos.MyShapeRectangulo;

/**
 * Clase lienzo que hereda de un JPanel y en el se mostrarán las figuras dibujadas
 * a partir de las clases propias creadas.
 * En ella a partir de eventos de ratón se crearán las figuras que se van a dibujar
 * con las propiedades que se encuentren activas en el lienzo. 
 * Para la hora de pintar, se recorrerá el vector de formas y se llamará para cada
 * figura, al paint de su clase correspondiente.
 * @author Francisco Fernández Millán
 */
public class Lienzo2D extends javax.swing.JPanel {
    public enum herramientasDibujo{LAPIZ, LINEA, RECTANGULO, ELIPSE, ARCO};
    private herramientasDibujo herrSelec;
    public final List<MyShape> vShape = new ArrayList();
    private MyShape s;
    private boolean relleno, alisar, editar, transparencia;
    private boolean punteado;
    private boolean degradadoV, degradadoH, degradadoD;
    public Stroke grosor;
    private int nivelGrosor;
    private float nivelTransparencia;
    private Point2D pInicio, pFin;
    private Shape clip;
    private Color colorTrazo, colorRelleno;
    private Color colorDegFrente, colorDegFondo;
    private MyShape formaDibujo;
    private final RenderingHints render;
    protected Composite comp;
    private Graphics2D g2d;
    private MyShapeLinea punto, linea;
    private MyShapeRectangulo rectangulo;
    private boolean recRedondeado;
    private MyShapeElipse elipse;
    private MyShapeArco arco;
    private List<MyShape> listaShapes;
    private Rectangle boundingbox = new Rectangle();
    public Stroke strokeBoundingbox;
    ArrayList<LienzoListener> lienzoEventListeners = new ArrayList();
    private Point pInsertarTexto;
    
    /**
     * Constructor de la clase lienzo, en el se inicializarán
     * las propiedades que contendrán las figuras.
     */
    public Lienzo2D() {
        initComponents();
        this.colorTrazo = Color.BLACK;
        this.colorRelleno = Color.BLACK;
        this.relleno = false;
        this.alisar = false;
        this.editar = false;
        this.transparencia = false;
        this.punteado = false;
        this.grosor = new BasicStroke(1.0f);
        this.nivelGrosor = 1;
        this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        render.put(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        this.strokeBoundingbox = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                                1f, new float[] {5, 5, 5, 5}, 2);
    }
      
    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo relleno liso.
     * @return boolean, representa el estado del modo de relleno liso.
     */
    public boolean getRelleno() {
        return this.relleno;
    }

    /**
     * Método que se encarga de modificar el estado del modo de relleno liso.
     * @param relleno, representa el nuevo estado del modo de relleno liso. 
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo relleno vertical.
     * @return boolean, representa el estado del modo de relleno vertical.
     */
    public boolean getDegradadoV() {
        return degradadoV;
    }

    /**
     * Método que se encarga de modificar el estado del modo de relleno vertical.
     * @param degradadoV, representa el nuevo estado del modo de relleno vertical. 
     */
    public void setDegradadoV(boolean degradadoV) {
        this.degradadoV = degradadoV;
    }

    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo relleno horizontal.
     * @return boolean, representa el estado del modo de relleno horizontal.
     */
    public boolean getDegradadoH() {
        return degradadoH;
    }

    /**
     * Método que se encarga de modificar el estado del modo de relleno horizontal.
     * @param degradadoH, representa el nuevo estado del modo de relleno horizontal. 
     */
    public void setDegradadoH(boolean degradadoH) {
        this.degradadoH = degradadoH;
    }
    
    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo relleno diagonal.
     * @return boolean, representa el estado del modo de relleno diagonal.
     */
    public boolean getDegradadoD() {
        return degradadoD;
    }

    /**
     * Método que se encarga de modificar el estado del modo de relleno diagonal.
     * @param degradadoD , representa el nuevo estado del modo de relleno diagonal. 
     */
    public void setDegradadoD(boolean degradadoD) {
        this.degradadoD = degradadoD;
    }
    
    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo alisado.
     * @return boolean, representa el estado del modo de alisado.
     */
    public boolean getAlisar() {
        return this.alisar;
    }

    /**
     * Método que se encarga de modificar el estado del modo de alisado.
     * @param alisar, representa el nuevo estado del modo de alisado. 
     */
    public void setAlisar(boolean alisar) {
        this.alisar = alisar;
    }

    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo edición.
     * @return boolean, representa el estado del modo de edición.
     */
    public boolean getEditar() {
        return this.editar;
    }

    /**
     * Método que se encarga de modificar el estado del modo de edición.
     * @param editar, representa el nuevo estado del modo de edición. 
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Método que se encarga de devolver si esta activado/desactivado el modo transparencia.
     * @return boolean, representa el estado del modo de transparencia.
     */
    public boolean getTransparencia() {
        return this.transparencia;
    }

    /**
     * Método que se encarga de modificar el estado del modo de transparencia.
     * @param transparencia , representa el nuevo estado del modo de transparencia. 
     */
    public void setTransparencia(boolean transparencia) {
        this.transparencia = transparencia;
    }

    /**
     * Método que se encarga de devolver el color del trazo.
     * @return Color, color del trazo.
     */
    public Color getColorTrazo() {
        return this.colorTrazo;
    }
    
    /**
     * Método que se encarga de modificar el color del trazo.
     * @param color, nuevo color del trazo. 
     */
    public void setColorTrazo(Color color) {
        this.colorTrazo = color;
        repaint();
    }
    
    /**
     * Método que se encarga de devolver el color del relleno liso.
     * @return Color, color del relleno liso.
     */
    public Color getColorRelleno() {
        return this.colorRelleno;
    }
    
    /**
     * Método que se encarga de modificar el color del relleno liso.
     * @param color, nuevo color del relleno liso. 
     */
    public void setColorRelleno(Color color) {
        this.colorRelleno = color;
        repaint();
    }

    /**
     * Método que se encarga de devolver el color del relleno de frente.
     * @return Color, color del relleno de frente.
     */    
    public Color getColorDegFrente() {
        return colorDegFrente;
    }

    /**
     * Método que se encarga de modificar el color del relleno de frente.
     * @param colorDegFrente, nuevo color del relleno de frente. 
     */
    public void setColorDegFrente(Color colorDegFrente) {
        this.colorDegFrente = colorDegFrente;
    }

    /**
     * Método que se encarga de devolver el color del relleno de fondo.
     * @return Color, color del relleno de fondo.
     */    
    public Color getColorDegFondo() {
        return colorDegFondo;
    }

    /**
     * Método que se encarga de modificar el color del relleno de fondo.
     * @param colorDegFondo , nuevo color del relleno de fondo. 
     */
    public void setColorDegFondo(Color colorDegFondo) {
        this.colorDegFondo = colorDegFondo;
    }
    
    /**
     * Método que se encarga de devolver el nivel de grosor.
     * @return int, representa el valor del nivel de grosor.
     */
    public int getNivelGrosor() {
        return this.nivelGrosor;
    }

    /**
     * Método que se encarga de modificar el valor del nivel de grosor.
     * @param nivelGrosor, nuevo valor del nivel de grosor. 
     */
    public void setNivelGrosor(int nivelGrosor) {
        this.nivelGrosor = nivelGrosor;
    }

    /**
     * Método que se encarga de devolver el nivel de transparencia.
     * @return float, representa el valor del nivel de transparencia.
     */
    public float getNivelTransparencia() {
        return nivelTransparencia;
    }

    /**
     * Método que se encarga de modificar el valor del nivel de transparencia.
     * @param nivelTransparencia, nuevo valor del nivel de transparencia. 
     */    
    public void setNivelTransparencia(float nivelTransparencia) {
        this.nivelTransparencia = nivelTransparencia;
    }
       
    /**
     * Método que se encarga de devolver el tipo de Stroke del grosor.
     * @return Stroke, objeto de la clase Stroke para el grosor.
     */
    public Stroke getGrosor() {
        return this.grosor;
    }
   
    /**
     * Método que se encarga de devolver el estado del tipo de trazado.
     * @return boolean, se devuelve el estado de la variable punteado que
     * representa si esta activdo/desactivado el modo trazo punteado.
     */
    public boolean getTrazo(){
        return this.punteado;
    }
    
    /**
     * Método que se encarga de modificar el estado del tipo de trazado.
     * @param trazo, nuevo valor para el tipo de trazado. 
     */
    public void setTrazo(boolean trazo){
        this.punteado = trazo;
    }
    
    /**
     * Método que se encarga de devolver la herramienta que se encuentra seleccionada.
     * @return herramientasDibujo, representa la herramienta seleccionada de 
     * la lista de enumerados.
     */
    public herramientasDibujo getHerrSelec() {
        return this.herrSelec;
    }

    /**
     * Método que se encarga de modificar la herramienta que se encuentra seleccionada.
     * @param herrSelec, nueva herramienta seleccionada. 
     */
    public void setHerrSelec(herramientasDibujo herrSelec) {
        this.herrSelec = herrSelec;
    }

    /**
     * Método que se encarga de devolver si se encuentra activa la opción
     * de rectangulo redondeado.
     * @return boolean, representa el estado de la variable.
     */
    public boolean getRecRedondeado() {
        return recRedondeado;
    }

    /**
     * Método que se encarga de modificar el estado de la variable que representa
     * la opción de rectángulo redondeado.
     * @param recRedondeado, nuevo valor de la variable. 
     */
    public void setRecRedondeado(boolean recRedondeado) {
        this.recRedondeado = recRedondeado;
    }
    
    /**
     * Método que se encarga de devolver la forma de dibujo como super clase propia.
     * @return MyShape, devuelve la figura perteneciente a la super clase propia.
     */
    public MyShape getFormaDibujo() {
        return this.formaDibujo;
    }

    /**
     * Método que se encarga de modificar la forma de dibujo como super clase propia.
     * @param formaDibujo, nueva figura perteneciente a la super clase propia.
     */
    public void setFormaDibujo(MyShape formaDibujo) {
        this.formaDibujo = formaDibujo;
    }

    /**
     * Método que se encarga de devolver el marco de selección de la figura.
     * @return Rectangle, forma de dibujo que encierra a la figura seleccionada.
     */
    public Rectangle getBoundingbox() {
        return boundingbox;
    }

    /**
     * Método que se encarga de modificar el marco de la selección de la figura.
     * @param boundingbox, nuevo valor para la forma de dibujo que encierra la figura. 
     */
    public void setBoundingbox(Rectangle boundingbox) {
        this.boundingbox = boundingbox;
    }
   
    /**
     * Método que se encarga de devolver la forma de dibujo que se encarga de
     * encerrar el lienzo en una zona de dibujo, de esta forma fuera de esos
     * límites no se puede dibujar.
     * @return Shape, forma de la figura que lo encierra.
     */
    public Shape getClip() {
        return clip;
    }

    /**
     * Método que se encarga de modificar la forma de dibujo que se encarga de
     * encerrar el lienzo en una zona de dibujo.
     * @param clip, nuevo valor de la figura que lo encierra. 
     */
    public void setClip(Shape clip) {
        this.clip = clip;
    }
    
    /**
     * Método que se encarga de según la figura que hay que seleccionar, 
     * crearle un marco que la encierre en función de sus dimensiones.
     * @param figura, figura seleccionada a la que encerrar en el marco. 
     */
    public void dibujarMarcoFigura(MyShape figura){
        this.boundingbox = figura.getBounds();
        this.boundingbox.setFrame(this.boundingbox.getX(), this.boundingbox.getY(), this.boundingbox.getWidth(), this.boundingbox.getHeight());
    }
          
    /**
     * Método que se encarga de devolver una lista con las figuras que se encuentran
     * en el vector de figuras.
     * @return List, lista de figuras. 
     */
    public List<MyShape> getvShape() {
        if(this.vShape.size()!=0){
            this.listaShapes = new ArrayList();
            for(MyShape s:vShape){
                this.listaShapes.add(s);
            }
        }
        return this.listaShapes;
    }
        
    /**
     * Método que se encarga de la cración de las figuras junto con sus propiedades
     * en función de la figura que se encuentre seleccionada.
     * Los objetos se inicializar mediante el constructor de las clases propias
     * con todos los atributos que posee cada forma de dibujo. Una vez creado,
     * se añade al vector de figuras y se lanza un evento de figura añadida.
     */
    private void createShape(){
         if (this.getHerrSelec() != null) {
            switch (this.getHerrSelec()){
                case LAPIZ:
                    punto = new MyShapeLinea(pInicio, pInicio, colorTrazo, punteado, nivelGrosor,
                            transparencia, nivelTransparencia, alisar);

                    this.vShape.add(punto);
                    notifyShapeAddedEvent(new LienzoEvent(this, punto));
                    break;

                case LINEA:
                    linea = new MyShapeLinea(pInicio, pFin, colorTrazo, punteado, nivelGrosor,
                            transparencia, nivelTransparencia, alisar);

                    this.vShape.add(linea);
                    notifyShapeAddedEvent(new LienzoEvent(this, linea));
                    break;

                case RECTANGULO:
                    rectangulo = new MyShapeRectangulo(pInicio, recRedondeado, colorTrazo, punteado, nivelGrosor,
                            transparencia, nivelTransparencia, alisar, colorDegFrente, colorDegFondo, degradadoV,
                            degradadoH, degradadoD, relleno, colorRelleno);

                    this.vShape.add(rectangulo);
                    notifyShapeAddedEvent(new LienzoEvent(this, rectangulo));
                    break;
                    
                case ELIPSE:
                    elipse = new MyShapeElipse(pInicio, colorTrazo, punteado, nivelGrosor,
                            transparencia, nivelTransparencia, alisar, colorDegFrente, colorDegFondo, degradadoV,
                            degradadoH, degradadoD, relleno, colorRelleno);

                    this.vShape.add(elipse);
                    notifyShapeAddedEvent(new LienzoEvent(this, elipse));
                    break;
                case ARCO:
                    arco = new MyShapeArco(pInicio, colorTrazo, punteado, nivelGrosor,
                            transparencia, nivelTransparencia, alisar, colorDegFrente, colorDegFondo, degradadoV,
                            degradadoH, degradadoD, relleno, colorRelleno);
                    this.vShape.add(arco);
                    notifyShapeAddedEvent(new LienzoEvent(this, arco));
                    break;
            }
        }
    }
    
    /**
     * Método que se encarga de actualizar las coordenadas de la figura tras 
     * su creación. 
     * Mediante el evento de ratón Dragged, se llamará a este método para
     * actualizar la posición de la figura en su creación.
     * Para la herramienta LAPIZ, no se llama a su método ya que se representa
     * como un único punto.
     */
    private void actualizarCoordShape(){
        if (this.getHerrSelec() != null) {
            switch (this.getHerrSelec()){
                case LAPIZ:
                    break;
                case LINEA:
                    linea.setCoordenadas(pInicio, pFin);
                   break;
                case RECTANGULO:
                    rectangulo.setCoordenadas(pInicio, pFin);
                    break;
                case ELIPSE:
                    elipse.setCoordenadas(pInicio, pFin);
                    break;
                case ARCO:
                    arco.setCoordenadas(pInicio, pFin);
            }
         }
    }
    
    
    public void addLienzoListener(LienzoListener listener){
        if(listener != null)
            lienzoEventListeners.add(listener);
    }
    
    private void notifyShapeAddedEvent(LienzoEvent evt){
        if(!lienzoEventListeners.isEmpty()){
            for(LienzoListener listener : lienzoEventListeners)
                listener.shapeAdded(evt);
        }
    }
       
    /**
     * Principal método del lienzo, este método se encargará de pintar cada 
     * una de las figuras que se encuentren en el vector de figuras.
     * Para pintar las figuras, se va recorriendo el vector de formas y cada 
     * una de ellas se encargará de llamar a su método Paint propio.
     * Además se añade el clip que se encargará de cerrar el lienzo en una zona
     * de dibujo y en caso de estar activo el modo edición, se dibujará el
     * boundingbox que mostrará la figura seleccionada con un marco a su alrededor.
     * @param g2d, , objeto de la clase abstracta Graphics. Se encargará de dibujar y modificar 
     * las propiedades de las figuras. 
     */
    @Override
    public void paint(Graphics g2d) {
        super.paint(g2d);
        this.g2d=((Graphics2D)g2d);
        
        if(this.getClip() != null){
            this.g2d.clip(clip);
        }
        
        if(this.getEditar()){
            this.g2d.setStroke(this.strokeBoundingbox);
            this.g2d.draw(boundingbox);
        }
        
        for(MyShape s:vShape){
            s.paint(this.g2d);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento de ratón que se activa al pulsar sobre el lienzo, este evento
     * se encargará de inicializar los puntos de inicio y fin, y en caso de
     * que no se encuentre activo el modo edición, llamar al método que se 
     * encarga de crear la figura que corresponda.
     * Esta figura se creará en los puntos donde se ha realizado el pressed.
     * @param evt, objeto de la clase evento de ratón. 
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pInicio = evt.getPoint();
        pFin = evt.getPoint();
        if(!this.getEditar())
            this.createShape();
    }//GEN-LAST:event_formMousePressed

    /**
     * Evento de ratón que se activa cuando tras pulsar, arrastramos, este método
     * en caso de no estar activo el modo de edición, actualizará el punto de fin
     * y llamará al método que se encarga de actualizar la figura con el nuevo
     * punto de fin.
     * Cambia el cursor en forma de dibujo.
     * @param evt, objeto de la clase evento de ratón.  
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(!this.getEditar()){
            this.pFin = evt.getPoint();
            this.actualizarCoordShape();
            this.repaint();
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } 
    }//GEN-LAST:event_formMouseDragged

    /**
     * Evento de ratón que se activa tras soltar, este método
     * llamará al evento anterior para el caso de tener que actualizar
     * la figura por el punto de fin.
     * Pone el cursor por defecto.
     * @param evt, objeto de la clase evento de ratón.  
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.formMouseDragged(evt);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
