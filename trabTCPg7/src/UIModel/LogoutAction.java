package UIModel;

/**
 * Classe Action LogoutAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class LogoutAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de logout.
	 */
	@Override
	public void execute ()
	{
		interf.logout();
	}

}
