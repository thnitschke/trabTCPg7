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
	 * Método de execução da action de Iniciar a preparação de uma mesa.
	 */
	@Override public void execute ()
	{
		ArrayList< Pedido > pedidosPendentes = operationService.getPedidosPendentes ();

		System.out.println ("Lista de pedidos pendentes, favor escolher pedido e inserir seu código abaixo:");
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
				System.out.println ("Nenhum pedido a ser preparado com este código de pedido existe. Favor inserir novamente.");
				continue;
			}
		}

		operationService.prepararItens (pedidoAserPreparado, pedidoAserPreparado.getListItems ());
	}
}
