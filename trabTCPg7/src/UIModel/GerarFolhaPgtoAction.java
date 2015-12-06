package UIModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import domainModel.Funcionario;
import domainModel.Turno;

/**
 * Classe Action GerarFolhaPgtoAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class GerarFolhaPgtoAction extends UIAction
{

	/**
	 * Método de execução da action de gerar a folha de pagamento.
	 */
	@Override public void execute ()
	{
		ArrayList< Turno > turnosFinalizados = operationService.getTurno ();

		System.out.println ("Lista de turnos para gerar pagamento, favor escolher turno e inserir seu código abaixo:");
		for (Iterator< Turno > iterator = turnosFinalizados.iterator (); iterator.hasNext ();)
		{
			Turno turno = (Turno) iterator.next ();
			System.out.println ("\t- Turno: " + turno.getTurnoCod ());
		}

		Turno turnoParaGerarPagamento = null;
		while (true)
		{
			String codTurno = someInput.next ();
			for (Iterator< Turno > iterator = turnosFinalizados.iterator (); iterator.hasNext ();)
			{
				Turno turno = (Turno) iterator.next ();
				if (turno.getTurnoCod ().equalsIgnoreCase (codTurno))
					turnoParaGerarPagamento = turno;
				else
					continue;
			}
			if (turnoParaGerarPagamento != null)
				break;
			else
			{
				System.out.println ("Nenhuma turno existente com este código de turno. Favor inserir novamente.");
				continue;
			}
		}

		System.out.println ("Folha de pagamento para o Turno selecionado (" + turnoParaGerarPagamento.getTurnoCod () + "):\n");
		HashMap< Funcionario, Double > salarios = turnoParaGerarPagamento.getFolhaPgto ();
		for (Iterator< Funcionario > iterator = salarios.keySet ().iterator (); iterator.hasNext ();)
		{
			Funcionario funcionario = (Funcionario) iterator.next ();
			System.out.println ("\t- CodFuncionario: " + funcionario.getID () + "\n\t\t- Salário: " + salarios.get (funcionario).toString () + "\n");
		}
	}
}
