package UIModel;

import java.util.ArrayList;
import domainModel.Mesa;

/**
 * Classe Action ReservarMesaAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class ReservarMesaAction extends UIAction
{

	/**
	 * Método de execução da action de reservar uma mesa.
	 */
	@Override public void execute ()
	{
		ArrayList< Mesa > mesasSemReserva = operationService.getMesasSemReserva ();

		if (mesasSemReserva.isEmpty ())
		{
			System.out.println ("\n >>--- Não possuem mesas disponíveis para reserva. ---<< \n");
		}
		else
		{
			System.out.println ("\n >>--- Mesas disponíveis para reserva (Setor / CodMesa) ---<< \n");
			for (int mesa = 0; mesa < mesasSemReserva.size (); mesa++)
			{
				System.out.println (mesasSemReserva.get (mesa).getSetor ().getNome () + " / " + mesasSemReserva.get (mesa).getCodigoMesa ());
			}

			System.out.println ("\n>> Infome o setor, o codigo da Mesa e a hora da mesa desejada:");
			System.out.print ("> Setor: ");
			String setor = someInput.next ();
			System.out.print ("> Mesa (codigo da mesa): ");
			String codMesa = someInput.next ();
			System.out.print ("> Horario (hh/mm): ");
			String hour = someInput.next ();

			for (int mesa = 0; mesa < mesasSemReserva.size (); mesa++)
			{
				Mesa m = mesasSemReserva.get (mesa);
				if ( (m.getSetor ().getNome ().equals (setor)) && (m.getCodigoMesa ().equals (codMesa)))
				{

					operationService.reservaMesa (m, hour);
					System.out.println ("\n--- Mesa reservada com sucesso! ---");
					return;
				}
			}
			System.out.println ("\n--- Mesa não encontrada. Certifique que informou os dados corretamente. ---");

		}
	}
}
