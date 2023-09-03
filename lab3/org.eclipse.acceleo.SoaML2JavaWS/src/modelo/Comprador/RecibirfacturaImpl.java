package modelo.Comprador;

import javax.jws.WebService;

@WebService(endpointInterface = "modelo.Comprador.Recibirfactura"
public class RecibirfacturaImpl implements Recibirfactura {

	public String RecibirfacturaOperationReceive(String RecibirfacturaParameterIn) {
		return "Invocación WS" + RecibirfacturaParameterIn;
	}
}
