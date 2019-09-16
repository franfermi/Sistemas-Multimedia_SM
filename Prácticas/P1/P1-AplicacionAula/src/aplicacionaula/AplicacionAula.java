package aplicacionaula;

public class AplicacionAula {

    public static void main(String[] args) {
        Profesor jesus,joaquin;
        Alumno juan,beatriz;
        Persona per;
        Delegado arrayDelegados[]=new Delegado[10];

        jesus   = new Profesor("Jesus");
        joaquin = new Profesor("Joaquin","B7");
        juan  = new Alumno("Juan");
        beatriz = new Alumno("Beatriz",4,5);
        per = new Persona();

        per.altura=3.3F;
        System.out.println("\n"+jesus.toString());   // Mostramos a los profesores
        System.out.println(joaquin.toString());
        jesus.setDespacho("C7");                     // Cambiamos el despacho de Jesus
        System.out.println(jesus.toString()+"\n");   // Comprobamos la nueva informacion

        jesus.darClase();                               // Jesus comineza la clase
        jesus.habla();
        juan.pregunta("¿Que es un objeto?");             // El alumno juan pregunta
        jesus.responderPregunta("¿Que es un objeto?");   // Jesus contesta
        beatriz.pregunta("¿Es lo mismo clase y objeto?");
        jesus.responderPregunta("¿Es lo mismo clase y objeto?");
        jesus.pregunta("¿Os habeis enterado?");
        
        arrayDelegados[0] = new Delegado("Francisco");
        
        // Como podemos ver cuando Jesus modifica la variable static de telefono
        // también afecta a Joaquin, esto es debido a ser estática.
        System.out.println("Prueba variable static de Jesus " + jesus.telefono);
        jesus.setTelefono("45454545");
        System.out.println("\nPrueba variable static de Joaquin " + joaquin.telefono);
    }

}
