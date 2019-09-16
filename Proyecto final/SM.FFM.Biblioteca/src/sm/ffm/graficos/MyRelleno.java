package sm.ffm.graficos;

import java.awt.Color;

/**
 * Clase abstracta intermedia MyRelleno que hereda de MyShape, de ella hereda
 * la clase abstracta MyPaintConRelleno y de esta última las figuras que contienen el atributo relleno.
 * @author Francisco Fernández Millán
 */
public abstract class MyRelleno extends MyShape{
    
    boolean relleno;
    boolean degradadoV, degradadoH, degradadoD;
    Color colorRelleno;
    Color colorDegFrente, colorDegFondo;
    
    /**
     * Método que se encarga de devolver el color del relleno liso.
     * @return Color, color del relleno liso.
     */
    public Color getColorRelleno(){
        return this.colorRelleno;
    }
    
    /**
     * Método que se encarga de modificar el color del relleno liso.
     * @param color, nuevo color del relleno liso. 
     */
    public void setColorRelleno(Color color){
        this.colorRelleno = color;
    }
    
    /**
     * Método que se encarga de devolver el color de frente del degradado.
     * @return Color, color de frente del degradado.
     */
    public Color getColorDegFrente() {
        return colorDegFrente;
    }

    /**
     * Método que se encarga de modificar el color de frente del degradado.
     * @param colorDegFrente, nuevo color de frente del degradado. 
     */
    public void setColorDegFrente(Color colorDegFrente) {
        this.colorDegFrente = colorDegFrente;
    }

    /**
     * Método que se encarga de devolver el color de fondo del degradado.
     * @return Color, color de fondo del degradado.
     */
    public Color getColorDegFondo() {
        return colorDegFondo;
    }

    /**
     * Método que se encarga de modificar el color de fondo del degradado.
     * @param colorDegFondo, nuevo color de fondo del degradado. 
     */
    public void setColorDegFondo(Color colorDegFondo) {
        this.colorDegFondo = colorDegFondo;
    }
    
    /**
     * Método que se encarga de devolver el estado del relleno liso.
     * @return boolean, relleno liso activado/desactivado.
     */
    public boolean getRelleno(){
        return this.relleno;
    }
    
    /**
     * Método que se encarga de modificar el estado del relleno liso.
     * @param relleno, nuevo estado del relleno liso. 
     */
    public void setRelleno(boolean relleno){
        this.relleno = relleno;
    }
    
    /**
     * Método que se encarga de devolver el estado del degradado vertical.
     * @return boolean, degradado vertical activado/desactivado.
     */
    public boolean getDegradadoV() {
        return degradadoV;
    }

    /**
     * Método que se encarga de modificar el estado del degradado vertical. 
     * @param degradadoV, nuevo estado del degradado vertical. 
     */
    public void setDegradadoV(boolean degradadoV) {
        this.degradadoV = degradadoV;
    }

    /**
     * Método que se encarga de devolver el estado del degradado horizontal.
     * @return boolean, degradado horizontal activado/desactivado.
     */
    public boolean getDegradadoH() {
        return degradadoH;
    }

    /**
     * Método que se encarga de modificar el estado del degradado vertical. 
     * @param degradadoH, nuevo estado del degradado vertical. 
     */
    public void setDegradadoH(boolean degradadoH) {
        this.degradadoH = degradadoH;
    }
    
    /**
     * Método que se encarga de devolver el estado del degradado diagonal.
     * @return boolean, degradado diagonal activado/desactivado.
     */
    public boolean getDegradadoD() {
        return degradadoD;
    }

    /**
     * Método que se encarga de modificar el estado del degradado vertical. 
     * @param degradadoD, nuevo estado del degradado vertical. 
     */
    public void setDegradadoD(boolean degradadoD) {
        this.degradadoD = degradadoD;
    }
}
