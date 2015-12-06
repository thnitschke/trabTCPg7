package UIModel;

import java.util.ArrayList;

import domainModel.Turno;

/**
 * Classe Action FazerRelatorioAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class FazerRelatorioAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de fazer um relat�rio de um determinado
	 * turno.
	 */
	@Override public void execute ()
	{
		ArrayList< Turno > listaTurnos = operationService.getTurno ();

		if (listaTurnos.isEmpty ())
		{
			System.out.println ("\n--- N�o existe nenhum hist�rico de turno armazenado no momento. ---");
		}
		else
		{
			System.out.println("\n >>--- Turnos encerrados ---<< \n");
			for (int i = 0; i < listaTurnos.size(); i++) {
				System.out.println("> Codigo do turno: "+listaTurnos.get(i).getTurnoCod() );
			}
			System.out.print ("> Escolha o turno desejado (c�digo do turno)");
			String turnoDesejado = someInput.next ();

			for (int turno = 0; turno < listaTurnos.size (); turno++)
			{
				Turno check = listaTurnos.get (turno);
				if (check.getTurnoCod ().equals (turnoDesejado))
				{
					System.out.println ("\n> Lucro obtido no turno: " + check.getSomaLucros ());
					System.out.println ("> Custo/Despesas obtido no turno: " + check.getSomaCustos ());
					return;
				}
			}

			System.out.println ("\n--- N�o foi encontrado o turno desejado. ---");
		}
	}
}
