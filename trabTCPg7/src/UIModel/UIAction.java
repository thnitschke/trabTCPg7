package UIModel;

import ArchitectureModel.RestaurantInterface;
import ArchitectureModel.RestaurantOperationService;

/**
 * Classe abstrata UIAction. Superclasse de todas as a��es dispon�veis no
 * sistema do restaurante. Por ser uma classe abstrata, ela n�o pode ser
 * instanciada.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public abstract class UIAction
{

	private RestaurantInterface interf;
	private RestaurantOperationService operationService;

	/**
	 * M�todo abstrata de executar uma determinada a��o. Este m�todo deve ser
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
		this.interf = interf;
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
		this.operationService = operationService;
	}
}
