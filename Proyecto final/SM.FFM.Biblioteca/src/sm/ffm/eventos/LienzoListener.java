package sm.ffm.eventos;

import java.util.EventListener;

/**
 * Interface LienzoListener que deberán de cumplir los manejadores
 * de eventos.
 * En este caso de definirá el método encargado de manejar cuando 
 * se añade una nueva figura al lienzo.
 * @author Francisco Fernández Millán.
 */
public interface LienzoListener extends EventListener{
    public void shapeAdded(LienzoEvent evt);
}
