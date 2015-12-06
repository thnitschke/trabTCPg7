package UIModel;

import java.util.Scanner;

import ArchitectureModel.RestaurantInterface;
import ArchitectureModel.RestaurantOperationService;
import ArchitectureModel.RestaurantOperationServiceImpl;

/**
 * Classe abstrata UIAction. Superclasse de todas as ações disponíveis no
 * sistema do restaurante. Por ser uma classe abstrata, ela não pode ser
 * instanciada. MOD(1.1): Retiradas os métodos getters e settters de interf e
 * operationService, já que os mesmos não são acessados através dos mesmos pelas
 * classes herdeiras, visto que os atributos são protected.
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
	 * Método abstrata de executar uma determinada ação. Este método deve
	 * ser devidamente implementado nas classes que herdarem a classe
	 * UIAction.
	 */
	public abstract void execute ();
}
