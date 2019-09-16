package sm.ffm.eventos;

import java.util.EventObject;
import sm.ffm.graficos.MyShape;

/**
 * Clase de eventos propia, tenemos la clase LienzoEvent que representará
 * un evento lanzado por un objeto lienzo, para ello hereda de EventObject.
 * Se ha definido el objeto forma, para que almacene la información sobre
 * el objeto que se ha dibujado que ha activado el evento.
 * @author Francisco Fernández Millán
 */
public class LienzoEvent extends EventObject{
    
    private MyShape forma;
    
    /**
     * Constructor de la clase que llama a su super clase object y que
     * actualiza la forma según el objeto que ha activado el evento.
     * @param source, objeto fuente.
     * @param forma, forma de dibujo. 
     */
    public LienzoEvent(Object source, MyShape forma) {
        super(source);
        this.forma = forma;
    }

    /**
     * Método que se encarga de devolver la forma de dibujo.
     * @return MyShape, forma de la super clase propia. 
     */
    public MyShape getForma() {
        return forma;
    } 
}
