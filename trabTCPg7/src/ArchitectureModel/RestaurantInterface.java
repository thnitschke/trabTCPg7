package ArchitectureModel;

import java.util.ArrayList;
import java.util.Scanner;

import UIModel.LoginAction;
import domainModel.AcaoInvalidaException;
import domainModel.Atendente;
import domainModel.AuxiliarCozinha;
import domainModel.Cozinheiro;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Gerente;


/**
 * Classe de exibição do programa. RestaurantInterface exibe as ações e toda execução 
 * de programa.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 *
 */
public class RestaurantInterface
{
	Scanner userOption = new Scanner(System.in);
	private int option;
	private boolean valid, logged;
	
	private static LoginAction idCheck = new LoginAction();
	private ArrayList< UIAction > acoes;
	private Funcionario funcionario;

	public static void main (String args[]){
		Database.getInstanciaUnica();
		idCheck.execute();
	}
	
	
	/**
	 * Recebe os dados de um funcionario e verifica o tipo de funcionario. 
	 * Caso null, significa que o funcionario não existe na database do restaurante.
	 * 
	 * @param funcionario Recebe um funcionario para login.
	 * @throws AcaoInvalidaException 
	 */
	public void login (Funcionario funcionario) throws AcaoInvalidaException
	{
		if (funcionario instanceof Atendente){
			logged = true;
			setAcoesAtendente();
		} 
		else if (funcionario instanceof AuxiliarCozinha){
			logged = true;
			setAcoesAuxiliar();
		}
		else if (funcionario instanceof Cozinheiro){
			logged = true;
			setAcoesCozinheiro();
		}
		else if (funcionario instanceof Garcom){
			logged = true;
			setAcoesGarcom();
		}
		else if (funcionario instanceof Gerente){
			logged = true;
			setAcoesGerente();
		}
		else {
			setWrongLogin();
		}
		
		 
	}

	
	
	/**
	 * Executa logout do sistema.
	 * 
	 */
	public void logout ()
	{
		 System.out.flush();
		 valid = false;
		 logged = false;
		 idCheck.execute();
	}

	
	
	/**
	 * Verifica se uma conta está logada.
	 * 
	 * @return Retorna true se for verdade e false caso contrário.
	 * 
	 */
	public boolean isLoggedIn ()
	{
		return logged;
	}

	
	
	/**
	 * Caso o dado enviado no login não exista, este método é executado.
	 * 
	 */
	protected void setWrongLogin ()
	{
		System.out.println("ID não identificado ou incorreto.");
		idCheck.execute();
	}

	
	
	/**
	 * Menu de ações do Garcom.
	 * 
	 * @throws AcaoInvalidaException
	 * 
	 */
	protected void setAcoesGarcom () throws AcaoInvalidaException
	{
		 System.out.flush();
		 System.out.println("Bem Vindo ao restaurante Garçom "+funcionario.getID()+"!!");
		 System.out.println("[1] - Abrir um pedido");
		 System.out.println("[2] - Atualizar pedido");
		 System.out.println("[3] - Fechar uma Mesa");
		 System.out.println("[4] - Encerrar sessão");
		 
		 while (valid == false){
			System.out.print("Informe a opção desejada: ");
			option = userOption.nextInt();
			
		 	switch(option){
		 	
		 		case 1: valid = true; break;
		 		case 2: break;
		 		case 3: break;
		 		case 4: logout();
		 				break;
		 		default: throw new AcaoInvalidaException("Opção inválida");
		 	} 
		 }
	}

	
	
	/**
	 * Menu de ações do cozinheiro.
	 * 
	 * @throws AcaoInvalidaException
	 * 
	 */
	protected void setAcoesCozinheiro () throws AcaoInvalidaException
	{
		 System.out.flush();
		 System.out.println("Bem Vindo ao restaurante Cozinheiro "+funcionario.getID()+"!!");
		 System.out.println("[1] - Iniciar preparacao de pedido");
		 System.out.println("[2] - Concluir preparacao do pedido");
		 System.out.println("[3] - Encerrar sessão");
		 
		 while (valid == false){
			System.out.print("Informe a opção desejada: ");
			option = userOption.nextInt();
			
		 	switch(option){
		 	
		 		case 1: valid = true; break;
		 		case 2: break;
		 		case 3: logout();
 						break;
		 		default: throw new AcaoInvalidaException("Opção inválida");
		 	} 
		 }
	}

	
	
	/**
	 * Menu de ações do Gerente.
	 * 
	 * @throws AcaoInvalidaException
	 * 
	 */
	protected void setAcoesGerente () throws AcaoInvalidaException
	{
		 System.out.flush();
		 System.out.println("Bem Vindo ao restaurante Gerente "+funcionario.getID()+"!!");
		 System.out.println("[1] - Iniciar turno");
		 System.out.println("[2] - Finalizar turno");
		 System.out.println("[3] - Gerar relatorio de gastos e ganhos de um turno");
		 System.out.println("[5] - Gerar folha de pagamentos de um turno");
		 System.out.println("[6] - Gerar relatorio de estoque");
		 System.out.println("[7] - Encerrar sessão");
		 
		 while (valid == false){
			System.out.print("Informe a opção desejada: ");
			option = userOption.nextInt();
			
		 	switch(option){
		 	
		 		case 1: valid = true; break;
		 		case 2: break;
		 		case 3: break;
		 		case 4: break;
		 		case 5: break;
		 		case 6: break;
		 		case 7: logout();
		 				break;
		 
		 		default: throw new AcaoInvalidaException("Opção inválida");
		 	} 
		 }
	}

	
	
	/**
	 * Menu de ações do Atendente.
	 * 
	 * @throws AcaoInvalidaException
	 * 
	 */
	protected void setAcoesAtendente () throws AcaoInvalidaException
	{
		 System.out.flush();
		 System.out.println("Bem Vindo ao restaurante Atendente "+funcionario.getID()+"!!");
		 System.out.println("[1] - Sentar um novo cliente");
		 System.out.println("[2] - Reservar uma mesa");
		 System.out.println("[3] - Cancelar uma reserva");
		 System.out.println("[4] - Encerrar sessão");
		 
		 while (valid == false){
			System.out.print("Informe a opção desejada: ");
			option = userOption.nextInt();
			
		 	switch(option){
		 	
		 		case 1: valid = true; break;
		 		case 2: break;
		 		case 3: break;
		 		case 4: logout();
		 				break;
		 		default: throw new AcaoInvalidaException("Opção inválida");
		 	} 
		 }
	}

	
	
	/**
	 * Menu de ações do Auxiliar.
	 * 
	 * @throws AcaoInvalidaException
	 * 
	 */
	protected void setAcoesAuxiliar () throws AcaoInvalidaException
	{
		 System.out.flush();
		 System.out.println("Bem Vindo ao restaurante Auxiliar "+funcionario.getID()+"!!");
		 System.out.println("[1] - Visualizar mesas para limpeza");
		 System.out.println("[2] - Liberar uma mesa para uso");
		 System.out.println("[3] - Encerrar sessão");
		 while (valid == false){
				System.out.print("Informe a opção desejada: ");
				option = userOption.nextInt();
				
			 	switch(option){
			 	
			 		case 1: valid = true; break;
			 		case 2: break;
			 		case 3: logout();
			 				break;
			 		default: throw new AcaoInvalidaException("Opção inválida");

			 	} 
		}
	}

}
