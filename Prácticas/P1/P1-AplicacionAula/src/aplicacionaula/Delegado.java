/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionaula;

/**
 *
 * @author francisco
 */
public class Delegado extends Alumno{
    
    public Delegado(String nom) {
        super(nom);
    }

    public Delegado(String nom, int cur, int na) {
        super(nom, cur, na);
    }
    
    public void pregunta(String grupo){
        System.out.println(nombre+" delega al grupo:  "+grupo);
  }
}
