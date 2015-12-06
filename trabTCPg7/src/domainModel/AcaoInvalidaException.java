package domainModel;

/**
 * Classe de exce��o AcaoInvalidaException. Extende a classe Exception.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class AcaoInvalidaException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7625711999472133662L;

	/**
	 * Construtor da exce��o AcaoInvalidaException.
	 * 
	 * @param message
	 *                Recebe a mensagem que ser� exibida.
	 */
	public AcaoInvalidaException (final String message)
	{
		super (message);
	}
}
