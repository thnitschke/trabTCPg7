package UIModel;


import java.util.Scanner;

import ArchitectureModel.RestaurantInterface;
import ArchitectureModel.RestaurantOperationService;
import ArchitectureModel.RestaurantOperationServiceImpl;
import domainModel.AcaoInvalidaException;
import domainModel.Funcionario;

/**
 * Classe Action LoginAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class LoginAction extends UIAction
{
	
	private RestaurantInterface interfaces;
	private RestaurantOperationService operationService;
	
	/**
	 * Método de execução da action de login.
	 */
	@Override
	public void execute ()
	{
		System.out.println("**** BEM VINDO AO SISTEMA DO RESTAURANTE **** \n");
		Scanner typeID = new Scanner (System.in);
		System.out.println("Informe o seu ID para acesso: ");
		String loginID = typeID.next();
		Funcionario empregado = operationService.login(loginID);
		try {
			interfaces.login(empregado);
		} catch (AcaoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
