package UIModel;

import java.util.ArrayList;

import domainModel.Mesa;
import domainModel.SemTurnoAtivoException;

/**
 * Classe Action SentaClienteAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class SentaClienteAction extends UIAction
{

	/**
	 * Método de execução da action de sentar um cliente em uma mesa.
	 */
	@Override public void execute ()
	{
		System.out.print ("> Informe a quantidade de pessoas: ");
		int quantity = someInput.nextInt ();

		try
		{
			ArrayList< Mesa > mesasParaXCapacidade = operationService.getMesasPara (quantity);
			if (mesasParaXCapacidade.isEmpty ())
			{
				System.out.println ("\n--- Nao possui mesas disponiveis ou com esta capacidade. ---");
			}
			else
			{
				System.out.println ("\n >>--- Mesas disponíveis com esta capacidade (Setor / CodMesa -- Capacidade) ---<< \n");
				for (int m = 0; m < mesasParaXCapacidade.size (); m++)
				{
					System.out.println (mesasParaXCapacidade.get (m).getSetor ().getNome () + " / " + mesasParaXCapacidade.get (m).getCodigoMesa () + " -- Capacidade: " + mesasParaXCapacidade.get (m).getCapacidade ());
				}

				System.out.println ("\n>> Infome o setor e codigo da Mesa da mesa que deseja ocupar:");
				System.out.print ("> Setor: ");
				String setor = someInput.next ();
				System.out.print ("> Mesa (codigo da mesa): ");
				String codMesa = someInput.next ();

				for (int mesa = 0; mesa < mesasParaXCapacidade.size (); mesa++)
				{
					Mesa m = mesasParaXCapacidade.get (mesa);
					if ( (m.getSetor ().getNome ().equals (setor)) && (m.getCodigoMesa ().equals (codMesa)))
					{

						operationService.sentaCliente (m);
						;
						System.out.println ("\n--- Mesa ocupada com sucesso! ---");
						return;
					}
				}

				System.out.println ("\n--- Mesa não encontrada! Certifique que escolheu uma mesa disponível. ---");
			}

		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}

	}

}
