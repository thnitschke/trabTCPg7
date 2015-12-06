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
	 * Método de execução da action de finalizar uma preparação.
	 */
	@Override public void execute ()
	{
		ArrayList< Pedido > pedidos = operationService.getPedidosPendentes ();
		
		if(pedidos.isEmpty()){
			System.out.println("...");
		} else{
			System.out.println ("Lista de pedidos para serem finalizados, favor escolher pedido e inserir seu código abaixo:");
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
				System.out.println ("Nenhum pedido a ser finalizado com este código de pedido existe. Favor inserir novamente.");
				continue;
			}
		}

		operationService.finalizarPedido (pedidoAserFinalizado);
	}}
}
