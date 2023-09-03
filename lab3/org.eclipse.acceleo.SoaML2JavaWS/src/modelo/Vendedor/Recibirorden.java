package modelo.Vendedor;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

public class Recibirorden {
} 

@WebMethod
@SOAPBinding(style = Style.RPC)
public interface Recibirorden {

	@WebMethod
	String RecibirordenOperationReceive(String RecibirordenParameterIn);
}
