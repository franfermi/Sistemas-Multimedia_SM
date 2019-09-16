package sm.ffm.iu;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Clase Colores que representará el listado de colores para la hora de pintar
 * las figuras. 
 * Hereda de un JPanel que será el encargado de mostrar los colores disponibles
 * para dibujar. Realizaremos un imaplementación de la clase ListCellRenderer
 * que nos proporciona el método getListCellRendererComponent en el que
 * configuraremos en este caso los valores que tomará el botón de color añadido
 * el JPanel.
 * @author Francisco Fernández Millán
 */
public class Colores extends javax.swing.JPanel implements ListCellRenderer{

    /**
     * Creates new form listaColores
     */
    public Colores() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnColor = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        btnColor.setBackground(java.awt.Color.white);
        add(btnColor, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * En función del valor de value que se establezca, se encargará de 
     * modificar el color de fondo de nuevo botón.
     * @param list, lista de los componentes.
     * @param value, valor devuelto por list.getModel.getElementAt(index).
     * @param index, índece de las celdas.
     * @param isSelected, devuelve true si la celda está seleccionada.
     * @param cellHasFocus, devuelve si la celda tiene el foco.
     * @return Component, devuelve el componente.
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.btnColor.setText(" ");
        this.btnColor.setBackground((Color) value);
        return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColor;
    // End of variables declaration//GEN-END:variables
}