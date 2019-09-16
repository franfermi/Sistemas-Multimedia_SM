package sm.ffm.graficos;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

/**
 * Clase abstracta intermedia MyPaintSinRelleno que hereda de MyShape y de ella
 * heredan las figuras que no contienen el atributo relleno.
 * @author Francisco Fernández Millán
 */
public abstract class MyPaintSinRelleno extends MyShape{
    
    /**
     * Método que se encarga de pintar la figura según los atributos activos.
     * La figura es dibujada según los atributos asignados como pueden ser el color, la transparencia,
     * el grosor, el tipo de trazado y el alisado.
     * @param g2d, objeto de la clase abstracta Graphics. Se encargará de dibujar y modificar 
     * las propiedades de las figuras.
     */
    @Override
    public void paint(Graphics2D g2d){
        this.setTipoTrazo();
        g2d.setStroke(this.trazo);
        g2d.setColor(this.colorTrazo);
        
        if(this.getTransparencia()){
            this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)this.getNivelTransparencia()/10);
            g2d.setComposite(this.comp);
        }else{
            this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
            g2d.setComposite(this.comp);
        }
        
        if(this.getAlisar())
            g2d.setRenderingHints(this.render);
        
        g2d.draw(formaDibujo);        
    }
}
