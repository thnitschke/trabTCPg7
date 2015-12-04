package UIModel;

import java.util.ArrayList;
import java.util.Date;

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
	 * 
	 */
	@Override
	public void execute ()
	{
		ArrayList<Mesa> mesasSemReserva = operationService.getMesasSemReserva();
		
		if (mesasSemReserva.isEmpty()){
			System.out.println("Não possuem mesas disponíveis para reserva.");
		} else{
			System.out.println("Mesas disponíveis para reserva (Setor / CodMesa):");
			for (int mesa = 0; mesa < mesasSemReserva.size(); mesa++) {
				System.out.println(mesasSemReserva.get(mesa).getSetor().getNome()+" / "+
												mesasSemReserva.get(mesa).getCodigoMesa());
			}
			
			System.out.println("\n>> Infome o setor/codigoMesa e a hora da mesa desejada:");
			System.out.print("Setor: ");
			String setor = someInput.next(); 
			System.out.print("\nMesa (codigo da mesa): ");
			String codMesa = someInput.next();
			System.out.print("\nHorario (hh/mm): ");
			String hour = someInput.next();
			
			for (int mesa = 0; mesa < mesasSemReserva.size(); mesa++) {
				Mesa m = mesasSemReserva.get(mesa);
				if ( (m.getSetor().getNome().equals(setor)) 
								&& (m.getCodigoMesa().equals(codMesa)) ){
					
					operationService.reservaMesa(m,hour);		
					System.out.println("Mesa reservada com sucesso!");
					return;
				}
			}
			System.out.println("Mesa não encontrada. Certifique que informou os dados corretamente.");
			
		}

	}

}
