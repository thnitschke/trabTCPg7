package UIModel;

import java.util.ArrayList;
import java.util.Iterator;

import domainModel.Pedido;

/**
 * Classe Action FinalizarPreparacaoAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class FinalizarPreparacaoAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de finalizar uma prepara��o.
	 */
	@Override public void execute ()
	{
		ArrayList< Pedido > pedidos = operationService.getPedidosPendentes ();
		
		if(pedidos.isEmpty()){
			System.out.println("...");
		} else{
			System.out.println ("Lista de pedidos para serem finalizados, favor escolher pedido e inserir seu c�digo abaixo:");
			for (Iterator< Pedido > iterator = pedidos.iterator (); iterator.hasNext ();)
			{
				Pedido pedido = (Pedido) iterator.next ();
				System.out.println ("\t- Pedido: " + pedido.getCod ());
			}

			Pedido pedidoAserFinalizado = null;
			while (true)
			{
				String codPedido = someInput.next ();
				for (Iterator< Pedido > iterator = pedidos.iterator (); iterator.hasNext ();)
				{
					Pedido pedido = (Pedido) iterator.next ();
					if (pedido.getCod ().equalsIgnoreCase (codPedido))
						pedidoAserFinalizado = pedido;
					else
						continue;
				}
			if (pedidoAserFinalizado != null)
				break;
			else
			{
				System.out.println ("Nenhum pedido a ser finalizado com este c�digo de pedido existe. Favor inserir novamente.");
				continue;
			}
		}

		operationService.finalizarPedido (pedidoAserFinalizado);
	}}
}
