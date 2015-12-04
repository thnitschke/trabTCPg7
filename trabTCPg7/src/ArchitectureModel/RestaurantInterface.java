package ArchitectureModel;

import java.util.ArrayList;
import java.util.Scanner;

import UIModel.CancelaReservaAction;
import UIModel.LoginAction;
import UIModel.LogoutAction;
import UIModel.ReservarMesaAction;
import UIModel.UIAction;
import UIModel.VisualizaMesasSujasAction;
import domainModel.Atendente;
import domainModel.AuxiliarCozinha;
import domainModel.Cozinheiro;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Gerente;

/**
 * Classe de exibição do programa. RestaurantInterface exibe as ações e toda
 * execução de programa.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class RestaurantInterface
{
	Scanner userOption = new Scanner (System.in);
	private int option;
	private boolean valid, logged;
	private static UIAction loginAction = new LoginAction ();
	private static UIAction logoutAction = new LogoutAction ();
	private static UIAction verMesasSujasAction = new VisualizaMesasSujasAction ();
	private static UIAction reservarMesa = new ReservarMesaAction ();
	private static UIAction cancelarReserva = new CancelaReservaAction ();

	private ArrayList< UIAction > acoes;
	private Funcionario funcionario;

	/**
	 * Método main. Inicializa o programa e realiza todas as funções
	 * desejadas do restaurante.
	 * 
	 * @param args
	 */
	public static void main (String args[])
	{
		Database.getInstanciaUnica ();
		loginAction.execute ();
	}

	/**
	 * Recebe os dados de um funcionario e verifica o tipo de funcionario.
	 * Caso null, significa que o funcionario não existe na database do
	 * restaurante.
	 * 
	 * @param funcionario
	 *                Recebe um funcionario para login.
	 */
	public void login (Funcionario funcionario)
	{
		if (funcionario instanceof Atendente)
		{
			this.funcionario = funcionario;
			logged = true;
			setAcoesAtendente ();
		}
		else if (funcionario instanceof AuxiliarCozinha)
		{
			this.funcionario = funcionario;
			logged = true;
			setAcoesAuxiliar ();
		}
		else if (funcionario instanceof Cozinheiro)
		{
			this.funcionario = funcionario;
			logged = true;
			setAcoesCozinheiro ();
		}
		else if (funcionario instanceof Garcom)
		{
			this.funcionario = funcionario;
			logged = true;
			setAcoesGarcom ();
		}
		else if (funcionario instanceof Gerente)
		{
			this.funcionario = funcionario;
			logged = true;
			setAcoesGerente ();
		}
		else
		{
			setWrongLogin ();
		}

	}

	/**
	 * Executa logout do sistema.
	 */
	public void logout ()
	{
		valid = false;
		logged = false;
		loginAction.execute ();
	}

	/**
	 * Verifica se uma conta está logada.
	 * 
	 * @return Retorna true se for verdade e false caso contrário.
	 */
	public boolean isLoggedIn ()
	{
		return logged;
	}

	/**
	 * Caso o dado enviado no login não exista, este método é executado.
	 */
	protected void setWrongLogin ()
	{
		System.out.println ("ID não identificado ou incorreto.");
		loginAction.execute ();
	}

	/**
	 * Menu de ações do Garcom.
	 */
	protected void setAcoesGarcom ()
	{
		System.out.println ("Bem Vindo ao restaurante Garçom " + funcionario.getID () + "!!");
		System.out.println ("Selecione uma das opcoes abaixo:");
		System.out.println ("[1] - Abrir um pedido");
		System.out.println ("[2] - Atualizar pedido");
		System.out.println ("[3] - Fechar uma Mesa");
		System.out.println ("[4] - Encerrar sessão");

		while (valid == false)
		{
			System.out.print ("Informe a opção desejada: ");
			option = userOption.nextInt ();

			switch (option)
			{

				case 1:
					break;
				case 2:
					break;
				case 3:
					break;

				case 4:
					logoutAction.execute ();
					break;
				default:
					System.out.println ("Opcao invalida. Utilize um dos numeros disponiveis no menu.");
			}
		}
	}

	/**
	 * Menu de ações do cozinheiro.
	 */
	protected void setAcoesCozinheiro ()
	{
		System.out.println ("Bem Vindo ao restaurante Cozinheiro " + funcionario.getID () + "!!");
		System.out.println ("Selecione uma das opcoes abaixo:");
		System.out.println ("[1] - Iniciar preparacao de pedido");
		System.out.println ("[2] - Concluir preparacao do pedido");
		System.out.println ("[3] - Encerrar sessão");

		while (valid == false)
		{
			System.out.print ("Informe a opção desejada: ");
			option = userOption.nextInt ();

			switch (option)
			{

				case 1:
					break;
				case 2:
					break;

				case 3:
					logoutAction.execute ();
					break;
				default:
					System.out.println ("Opcao invalida. Utilize um dos numeros disponiveis no menu.");

			}
		}
	}

	/**
	 * Menu de ações do Gerente.
	 */
	protected void setAcoesGerente ()
	{
		System.out.println ("Bem Vindo ao restaurante Gerente " + funcionario.getID () + "!!");
		System.out.println ("Selecione uma das opcoes abaixo:");
		System.out.println ("[1] - Iniciar turno");
		System.out.println ("[2] - Finalizar turno");
		System.out.println ("[3] - Gerar relatorio de gastos e ganhos de um turno");
		System.out.println ("[5] - Gerar folha de pagamentos de um turno");
		System.out.println ("[6] - Gerar relatorio de estoque");
		System.out.println ("[7] - Encerrar sessão");

		while (valid == false)
		{
			System.out.print ("Informe a opção desejada: ");
			option = userOption.nextInt ();

			switch (option)
			{

				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;

				case 7:
					logout ();
					break;

				default:
					System.out.println ("Opcao invalida. Utilize um dos numeros disponiveis no menu.");
			}
		}
	}

	/**
	 * Menu de ações do Atendente.
	 */
	protected void setAcoesAtendente ()
	{
		while (option != 4)
		{
			System.out.println ("Bem Vindo ao restaurante Atendente " + funcionario.getID () + "!!");
			System.out.println ("Selecione uma das opcoes abaixo:");
			System.out.println ("[1] - Sentar um novo cliente");
			System.out.println ("[2] - Reservar uma mesa");
			System.out.println ("[3] - Cancelar uma reserva");
			System.out.println ("[4] - Encerrar sessão");
			System.out.print ("Informe a opção desejada: ");
			option = userOption.nextInt ();

			switch (option)
			{

				case 1:
					break;

				case 2:
					reservarMesa.execute ();
					break;

				case 3:
					cancelarReserva.execute ();
					break;

				case 4:
					logoutAction.execute ();
					break;
				default:
					System.out.println ("Opcao invalida. Utilize um dos numeros disponiveis no menu.");
			}
		}
	}

	/**
	 * Menu de ações do Auxiliar.
	 */
	protected void setAcoesAuxiliar ()
	{
		System.out.println ("Bem Vindo ao restaurante Auxiliar de Cozinha " + funcionario.getID () + "!!");
		System.out.println ("Selecione uma das opcoes abaixo:");
		System.out.println ("[1] - Visualizar mesas para limpeza");
		System.out.println ("[2] - Liberar uma mesa para uso");
		System.out.println ("[3] - Encerrar sessão");
		while (valid == false)
		{
			System.out.print ("Informe a opção desejada: ");
			option = userOption.nextInt ();

			switch (option)
			{

				case 1:
					verMesasSujasAction.execute ();
					break;

				case 2:
					break;

				case 3:
					logoutAction.execute ();
					break;

				default:
					System.out.println ("Opcao invalida. Utilize um dos numeros disponiveis no menu.");

			}
		}
	}

}
