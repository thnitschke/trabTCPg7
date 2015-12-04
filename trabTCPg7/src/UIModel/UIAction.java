package UIModel;

import java.util.Scanner;

import ArchitectureModel.RestaurantInterface;
import ArchitectureModel.RestaurantOperationService;
import ArchitectureModel.RestaurantOperationServiceImpl;


/**
 * Classe abstrata UIAction. Superclasse de todas as ações disponíveis no
 * sistema do restaurante. Por ser uma classe abstrata, ela não pode ser
 * instanciada.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 * 
 */
public abstract class UIAction
{

	protected static RestaurantInterface interf = new RestaurantInterface ();
	protected static RestaurantOperationService operationService = new RestaurantOperationServiceImpl() ;
	protected static Scanner someInput = new Scanner(System.in);
	
	/**
	 * Método abstrata de executar uma determinada ação. Este método deve ser
	 * devidamente implementado nas classes que herdarem a classe UIAction.
	 */
	public abstract void execute ();

	
	
	/**
	 * @return the interf
	 */
	public RestaurantInterface getInterf() {
		return interf;
	}

	
	
	/**
	 * @param interf the interf to set
	 */
	public void setInterf(RestaurantInterface interf) {
		UIAction.interf = interf;
	}

	
	
	/**
	 * @return the operationService
	 */
	public RestaurantOperationService getOperationService() {
		return operationService;
	}

	
	
	/**
	 * @param operationService the operationService to set
	 */
	public void setOperationService(RestaurantOperationService operationService) {
		UIAction.operationService = operationService;
	}
}
