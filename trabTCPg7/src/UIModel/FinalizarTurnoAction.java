package UIModel;

/**
 * Classe Action FinalizarTurnoAction. Extende a classe abstrata UIAction.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class FinalizarTurnoAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de finalizar um turno.
	 */
	@Override
	public void execute ()
	{
		boolean mesasFechadas = operationService.verificaMesasFechadas();
		
		if (mesasFechadas == true){
			operationService.retirarTurnoAtivo();
		} else {
			System.out.println("\n--- Todas as mesas n�o est�o fechadas. N�o � possivel encerrar turno. ---");
		}

	}

}
