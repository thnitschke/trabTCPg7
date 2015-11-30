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
	 * Construtor da exce��o AcaoInvalidaException.
	 * 
	 * @param message
	 *            Recebe a mensagem que ser� exibida.
	 */
	public AcaoInvalidaException (final String message)
	{
		super (message);
	}
}
