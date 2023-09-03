package modelo.Comprador;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebMethod
@SOAPBinding(style = Style.RPC)
public interface Recibirfactura {

	@WebMethod
	String RecibirfacturaOperationReceive(String RecibirfacturaParameterIn);
}
