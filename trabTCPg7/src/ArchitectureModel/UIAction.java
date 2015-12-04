package ArchitectureModel;


  
 
public abstract class UIAction
{

	private RestaurantInterface interfaces;
	private RestaurantOperationService operationService;

	public abstract void execute ();
}

