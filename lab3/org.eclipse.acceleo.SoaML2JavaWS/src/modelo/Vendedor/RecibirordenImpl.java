package modelo.Vendedor;

import javax.jws.WebService;

@WebService(endpointInterface = "modelo.Vendedor.Recibirorden"
public class RecibirordenImpl implements Recibirorden {

	public String RecibirordenOperationReceive(String RecibirordenParameterIn) {
		return "Invocación WS" + RecibirordenParameterIn;
	}
}
