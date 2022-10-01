package AccionesSemanticas;
//agregado por lu 01/10

import Compilador.AnalizadorLexico;
import Compilador.Notificador;

public class GeneroError extends AccionSemantica{
    
    private String error;
    private AnalizadorLexico analizadorLexico;

    public GeneroWarning(String error, AnalizadorLexico analizadorLexico){
        this.error = error;
        this.analizadorLexico = analizadorLexico;
    }

    @Override
    public void ejecutarAccionSemantica(){
        Notificador.addError(analizadorLexico.getLineaActual(),error)
        analizadorLexico.getAutomata().reiniciarAutomata();
    }

}