package UIModel;

import java.util.ArrayList;
import java.util.Date;

import domainModel.Mesa;

/**
 * Classe Action CancelaReservaAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class CancelaReservaAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de cancelar reserva de uma mesa.
	 */
	@Override
	public void execute ()
	{
		ArrayList<Mesa> mesasComReserva = operationService.getMesas();
		
		if (mesasComReserva.isEmpty()){
			System.out.println("N�o possui nenhuma mesa reservada no momento.");
		} else{
			System.out.println("Mesas reservadas (Setor / CodMesa):");
			for (int mesa = 0; mesa < mesasComReserva.size(); mesa++) {
				System.out.println(mesasComReserva.get(mesa).getSetor().getNome()+" / "+
									mesasComReserva.get(mesa).getCodigoMesa());
			}
			
			System.out.println("\n>> Infome o setor/codigoMesa que deseja cancelar:");
			System.out.print("Setor: ");
			String setor = someInput.next(); 
			System.out.print("\nMesa (codigo da mesa): ");
			String codMesa = someInput.next();
			
			for (int mesa = 0; mesa < mesasComReserva.size(); mesa++) {
				Mesa m = mesasComReserva.get(mesa);
				if ( (m.getSetor().getNome().equals(setor)) 
								&& (m.getCodigoMesa().equals(codMesa)) ){
					
					operationService.cancelaReserva(m);		
					System.out.println("Reserva cancelada com sucesso!");
					return;
				}
			}
			System.out.println("Mesa n�o encontrada. Certifique que informou os dados corretamente.");
		}

	}
}
