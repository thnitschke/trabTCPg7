package UIModel;

import java.util.ArrayList;
import java.util.Iterator;

import domainModel.Pedido;

/**
 * Classe Action IniciarPreparacaoAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class IniciarPreparacaoAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de Iniciar a prepara��o de uma mesa.
	 */
	@Override public void execute ()
	{
		ArrayList< Pedido > pedidosPendentes = operationService.getPedidosPendentes ();

		System.out.println ("Lista de pedidos pendentes, favor escolher pedido e inserir seu c�digo abaixo:");
		for (Iterator< Pedido > iterator = pedidosPendentes.iterator (); iterator.hasNext ();)
		{
			Pedido pedido = (Pedido) iterator.next ();
			System.out.println ("\t- Pedido: " + pedido.getCod ());
		}

		Pedido pedidoAserPreparado = null;
		while (true)
		{
			String codPedido = someInput.next ();
			for (Iterator< Pedido > iterator = pedidosPendentes.iterator (); iterator.hasNext ();)
			{
				Pedido pedido = (Pedido) iterator.next ();
				if (pedido.getCod ().equalsIgnoreCase (codPedido))
					pedidoAserPreparado = pedido;
				else
					continue;
			}
			if (pedidoAserPreparado != null)
				break;
			else
			{
				System.out.println ("Nenhum pedido a ser preparado com este c�digo de pedido existe. Favor inserir novamente.");
				continue;
			}
		}

		operationService.prepararItens (pedidoAserPreparado, pedidoAserPreparado.getListItems ());
	}
}
