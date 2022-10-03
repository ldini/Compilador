package AccionesSemanticas;

import Compilador.*;
import util.*;;

public class GeneroError extends AccionSemantica{
    
    private String error;
    private AnalizadorLexico analizadorLexico;

    public GeneroError(String error, AnalizadorLexico analizadorLexico){
        this.error = error;
        this.analizadorLexico = analizadorLexico;
    }

    @Override
    public void ejecutarAccionSemantica(){
        Notificador.addError(analizadorLexico.getLineaActual(),error);
        analizadorLexico.getAutomata().reiniciarAutomata();
    }
}
