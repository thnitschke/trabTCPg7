package domainModel;



/**
 * Classe de exce��o SemTurnoAtivoException. Extende a classe Exception.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 *
 */
public class SemTurnoAtivoException extends Exception
{
	
	/**
	 * Construtor da exce��o SemTurnoAtivoException.
	 * 
	 * @param message Recebe a mensagem que ser� exibida.
	 */
	public SemTurnoAtivoException (final String message) {
		super(message);
	}
}