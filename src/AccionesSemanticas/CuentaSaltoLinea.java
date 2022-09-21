package AccionesSemanticas;

public class CuentaSaltoLinea extends AccionSemantica{
	
	private int cantidadLineas = 1;
	
	public CuentaSaltoLinea(){}
	
	@Override
	public void ejecutarAccionSemantica(){
		cantidadLineas++;
	}
	
	public int getCantidadLineas(){
		return this.cantidadLineas;
	}
}
