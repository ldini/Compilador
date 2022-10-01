package AccionesSemanticas;
//agregado por lu 01/10

import Compilador.AnalizadorLexico;
import Compilador.Notificador;

public class GeneroWarning extends AccionSemantica{
    
    private String warning;
    private AnalizadorLexico analizadorLexico;

    public GeneroWarning(String warning, AnalizadorLexico analizadorLexico){
        this.warning = warning;
        this.analizadorLexico = analizadorLexico;
    }

    @Override
    public void ejecutarAccionSemantica(){
        Notificador.addWarning(analizadorLexico.getLineaActual(),warning)
    }

}