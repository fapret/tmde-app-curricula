package modelo.Vendedor;

import javax.jws.WebService;

@WebService(endpointInterface = "modelo.Vendedor.Recibirpago"
public class RecibirpagoImpl implements Recibirpago {

	public String RecibirpagoOperationReceive(String RecibirpagoParameterIn) {
		return "Invocación WS" + RecibirpagoParameterIn;
	}
}
