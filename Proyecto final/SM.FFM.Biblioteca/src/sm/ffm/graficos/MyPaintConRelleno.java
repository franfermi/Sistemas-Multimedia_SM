package sm.ffm.graficos;

import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

/**
 * Clase abstracta intermedia MyPaintConRelleno que hereda de MyRelleno y de ella
 * heredan las figuras que contienen el atributo relleno.
 * @author Francisco Fernández Millán
 */
public abstract class MyPaintConRelleno extends MyRelleno{
    
    /**
     * Método que se encarga de pintar la figura según los atributos activos.
     * La figura es dibujada según los atributos asignados como pueden ser el color, el relleno, la transparencia,
     * el grosor, el tipo de trazado y el alisado.
     * @param g2d, objeto de la clase abstracta Graphics. Se encargará de dibujar y modificar 
     * las propiedades de las figuras.
     */
    @Override
    public void paint(Graphics2D g2d) {
        this.setTipoTrazo();
        g2d.setStroke(this.trazo);
        g2d.setRenderingHints(this.render);
                
        if(this.getTransparencia()){
            this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)this.getNivelTransparencia()/10);
            g2d.setComposite(this.comp);
        }else{
            this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
            g2d.setComposite(this.comp);
        }
               
        if(this.getRelleno()){
            g2d.setPaint(this.colorRelleno);
            g2d.fill(formaDibujo);
        }else if(this.getDegradadoH()){
            GradientPaint horizontalGradient = new GradientPaint(this.formaDibujo.getBounds().x, this.formaDibujo.getBounds().y,
                                    this.getColorDegFrente(), this.formaDibujo.getBounds().x + this.formaDibujo.getBounds().width, 
                                    this.formaDibujo.getBounds().y, this.getColorDegFondo());
            g2d.setPaint(horizontalGradient);
            
            g2d.fill(formaDibujo);
        }else if(this.getDegradadoV()){
            GradientPaint verticalGradient = new GradientPaint(this.formaDibujo.getBounds().x, this.formaDibujo.getBounds().y,
                                    this.getColorDegFrente(), this.formaDibujo.getBounds().x, this.formaDibujo.getBounds().y + 
                                    this.formaDibujo.getBounds().height, this.getColorDegFondo());
            g2d.setPaint(verticalGradient);
            
            g2d.fill(formaDibujo);
        }else if(this.getDegradadoD()){
            GradientPaint diagonalGradient = new GradientPaint(this.formaDibujo.getBounds().x, this.formaDibujo.getBounds().y, this.getColorDegFrente(), 
                                    this.formaDibujo.getBounds().x  + this.formaDibujo.getBounds().width, this.formaDibujo.getBounds().y + 
                                    this.formaDibujo.getBounds().height, this.getColorDegFondo());
            g2d.setPaint(diagonalGradient);
            
            g2d.fill(formaDibujo);        
        }
        
        g2d.setColor(this.colorTrazo);
        g2d.draw(formaDibujo); 
    }
}
