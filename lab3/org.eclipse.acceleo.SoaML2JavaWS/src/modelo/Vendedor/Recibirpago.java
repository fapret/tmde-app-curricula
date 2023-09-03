package modelo.Vendedor;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

public class Recibirpago {
} 

@WebMethod
@SOAPBinding(style = Style.RPC)
public interface Recibirpago {

	@WebMethod
	String RecibirpagoOperationReceive(String RecibirpagoParameterIn);
}
