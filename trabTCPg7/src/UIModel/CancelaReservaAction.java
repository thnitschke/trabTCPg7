package UIModel;

import java.util.ArrayList;

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
	 * Método de execução da action de cancelar reserva de uma mesa.
	 */
	@Override
	public void execute ()
	{
		ArrayList<Mesa> mesasComReserva = operationService.getMesas();
		
		if (mesasComReserva.isEmpty()){
			System.out.println("Não possui nenhuma mesa reservada no momento.");
		} else{
			System.out.println("\n >>--- Mesas reservadas (Setor / CodMesa) ---<< \n");
			for (int mesa = 0; mesa < mesasComReserva.size(); mesa++) {
				System.out.println(mesasComReserva.get(mesa).getSetor().getNome()+" / "+
									mesasComReserva.get(mesa).getCodigoMesa()+
										" - Horário da Reserva: "+mesasComReserva.get(mesa).getReserva());
			}
			
			System.out.println("\n>> Infome o setor/codigoMesa que deseja cancelar:");
			System.out.print("> Setor: ");
			String setor = someInput.next(); 
			System.out.print("> Mesa (codigo da mesa): ");
			String codMesa = someInput.next();
			
			for (int mesa = 0; mesa < mesasComReserva.size(); mesa++) {
				Mesa m = mesasComReserva.get(mesa);
				if ( (m.getSetor().getNome().equals(setor)) 
								&& (m.getCodigoMesa().equals(codMesa)) ){
					
					operationService.cancelaReserva(m);		
					System.out.println("\n--- Reserva cancelada com sucesso! ---");
					return;
				}
			}
			System.out.println("\n--- ERRO: Mesa não encontrada. Certifique que informou os dados corretamente. ---");
		}

	}
}
