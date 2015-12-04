package UIModel;

import java.util.ArrayList;

import domainModel.Mesa;

/**
 * Classe Action VisualizaMesasSujasAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class VisualizaMesasSujasAction extends UIAction
{

	
	/**
	 * Método de execução da action de visualizar as mesas sujas do restaurante.
	 * 
	 */
	@Override
	public void execute ()
	{
		ArrayList<Mesa> mesasSujas = operationService.getMesasSujas();
		
		if(mesasSujas.isEmpty()){
			System.out.println("Todas as mesas do restaurante se encontram limpas!");
		} else {
			System.out.println("Mesas sujas do restaurante (Setor / CodigoMesa):");
			for (int m = 0; m < mesasSujas.size(); m++) {
				System.out.println(mesasSujas.get(m).getSetor().getNome()+" / "+mesasSujas.get(m).getCodigoMesa());
				
			}
		}
		

	}

}
