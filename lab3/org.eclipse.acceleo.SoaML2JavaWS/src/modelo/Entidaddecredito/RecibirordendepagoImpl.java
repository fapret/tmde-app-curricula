package modelo.Entidaddecredito;

import javax.jws.WebService;

@WebService(endpointInterface = "modelo.Entidaddecredito.Recibirordendepago"
public class RecibirordendepagoImpl implements Recibirordendepago {

	public String RecibirordendepagoOperationReceive(String RecibirordendepagoParameterIn) {
		return "Invocación WS" + RecibirordendepagoParameterIn;
	}
}
