package UIModel;

import java.util.ArrayList;

import domainModel.AuxiliarCozinha;
import domainModel.Funcionario;
import domainModel.Mesa;

/**
 * Classe Action LiberaMesaAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class LiberaMesaAction extends UIAction
{

	/**
	 * Método de execução da action de liberar uma mesa.
	 */
	@Override public void execute ()
	{
		ArrayList< Mesa > liberaMesa = operationService.getMesasSujas ();

		if (liberaMesa.isEmpty ())
		{
			System.out.println ("\n--- Nao possui mesa(s) para serem liberada no momento. ---");
		}
		else
		{
			System.out.println ("\n >>--- Mesas para liberar (Setor / CodMesa) ---<< \n");
			for (int m = 0; m < liberaMesa.size (); m++)
			{
				System.out.println (liberaMesa.get (m).getSetor ().getNome () + " / " + liberaMesa.get (m).getCodigoMesa ());
			}

			System.out.println ("\n>> Infome o setor e codigo da Mesa da mesa que deseja liberar:");
			System.out.print ("> Setor: ");
			String setor = someInput.next ();
			System.out.print ("> Mesa (codigo da mesa): ");
			String codMesa = someInput.next ();

			for (int mesa = 0; mesa < liberaMesa.size (); mesa++)
			{
				Mesa mesaParaLiberar = liberaMesa.get (mesa);
				if ( (mesaParaLiberar.getSetor ().getNome ().equals (setor)) && (mesaParaLiberar.getCodigoMesa ().equals (codMesa)))
				{

					if (!mesaParaLiberar.isOcupada ())
					{
						System.out.println ("A mesa se encontra ocupada ainda. Não pode ser liberada enquanto nao fechar.");
						return;
					}

					Funcionario func = interf.getFuncionario ();
					operationService.liberaMesa ((AuxiliarCozinha) func, mesaParaLiberar);

					return;
				}
			}
			System.out.println ("\n--- Mesa não encontrada! Certifique que informou corretamente a mesa. ---");
		}
	}
}
