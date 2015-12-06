package UIModel;

import java.util.Scanner;

import ArchitectureModel.RestaurantInterface;
import ArchitectureModel.RestaurantOperationService;
import ArchitectureModel.RestaurantOperationServiceImpl;

/**
 * Classe abstrata UIAction. Superclasse de todas as a��es dispon�veis no
 * sistema do restaurante. Por ser uma classe abstrata, ela n�o pode ser
 * instanciada. MOD(1.1): Retiradas os m�todos getters e settters de interf e
 * operationService, j� que os mesmos n�o s�o acessados atrav�s dos mesmos pelas
 * classes herdeiras, visto que os atributos s�o protected.
 * 
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.1
 */
public abstract class UIAction
{
	protected static RestaurantInterface interf = new RestaurantInterface ();
	protected static RestaurantOperationService operationService = new RestaurantOperationServiceImpl ();
	protected static Scanner someInput = new Scanner (System.in);

	/**
	 * M�todo abstrata de executar uma determinada a��o. Este m�todo deve
	 * ser devidamente implementado nas classes que herdarem a classe
	 * UIAction.
	 */
	public abstract void execute ();
}
