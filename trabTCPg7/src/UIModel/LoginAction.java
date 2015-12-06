package UIModel;

import java.util.Scanner;

import domainModel.Funcionario;

/**
 * Classe Action LoginAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class LoginAction extends UIAction
{

	/**
	 * Método de execução da action de login.
	 */
	@Override public void execute ()
	{

		System.out.println ("\n **** BEM VINDO AO SISTEMA DO RESTAURANTE **** \n");
		@SuppressWarnings ("resource") Scanner typeID = new Scanner (System.in);
		System.out.println ("-- Para encerrar o programa digite EXIT como id.");
		System.out.print (">> Para acessar o sistema, informe o seu ID: ");
		String input = typeID.next ();
		if (input.equalsIgnoreCase ("EXIT"))
		{
			return; // Volta ao método main.
		}
		else
		{
			Funcionario empregado = operationService.login (input);
			interf.login (empregado);
		}

	}

}
