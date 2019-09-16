/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaLand;

/**
 *
 * @author francisco
 */
public class EjemplosJavaLand {
    public static void main(String[] args) {
        double a = 3.7, a_abs, b_sqrt;
        int b = 37, d = 5;
        char c = 'A';
        String d_string;
        String cad = "Hola", sub_cad;
        String cad2 = "y adios", concatenar;
        
        a_abs = Math.abs(a);
        b_sqrt = Math.sqrt(b);
        d_string = Integer.toString(d);
        sub_cad = cad.substring(1, 2);
        concatenar = cad + " " + cad2;
        
        System.out.println("Valor absoluto: |"+a+"| = "+a_abs);
        System.out.println("Raiz cuadrada: "+b+" = "+b_sqrt); 
        
        if(Character.isUpperCase(c)){
            System.out.println("Mayúscula");
        }else{
            System.out.println("Minúscula");
        }

        System.out.println("Convertir a String: "+d+" = "+d_string); 
        System.out.println("Caracter de la cadena: "+cad+" = "+sub_cad); 
        System.out.println("Concatenar: "+cad+" "+cad2+" = "+concatenar); 
    }
}
