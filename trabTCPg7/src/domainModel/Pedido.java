package domainModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Guarda as informações sobre o pedido de uma mesa. Uma mesa não pode conter
 * mais de um pedido. Ele contém um mapa relacionando seus itens com o estado de
 * preparação de cada um. Além disso, ele possui um atributo indicando seu
 * estado de preparação e o garçom responsável pelo pedido.
 *
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.0
 */
public class Pedido
{
	private Mesa mesa;
	private ArrayList< ItemPedido > itens;
	private Estado estado;
	private Garcom garcom;
	// Remoção do atributo listItens, não é preciso.

	/**
	 * Construtor de Pedido. MODIFICADO!
	 * 
	 * @author thnitschke
	 * @version 1.1
	 * @param garcom
	 *                Recebe um garçom.
	 * @param mesa
	 *                Recebe uma lista de Itens.
	 */
	public Pedido (Garcom garcom, Mesa mesa)
	{
		this.garcom = garcom;
		this.mesa = mesa;
		estado = Estado.PENDENTE;
	}

	/**
	 * Retorna o estado do Pedido.
	 * 
	 * @return Retorna o estado do pedido.
	 */
	public Estado getEstado ()
	{
		return estado;
	}

	/**
	 * Insere um estado para o pedido.
	 * 
	 * @param estado
	 *                Recebe um estado por parâmetro para associar ao
	 *                pedido.
	 */
	public void setEstado (Estado estado)
	{
		this.estado = estado;
	}

	/**
	 * Modifica todos os estados de uma lista de itens, para um determinado
	 * estado.
	 * 
	 * @param itens
	 *                Recebe uma lista de objetos do tipo ItemPedido.
	 * @param estado
	 *                Recebe um estado.
	 */
	public void setEstadoItens (ArrayList< ItemPedido > itens, Estado umEstado)
	{

		for (int item = 0; item < itens.size (); item++)
		{
			itens.get (item).setEstado (umEstado);
		}
	}

	/**
	 * (NEW) Getter da lista de ItemPedido itens.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 */
	ArrayList< ItemPedido > getItens ()
	{
		return itens;
	}

	/**
	 * (NEW) Pega a lista de itens do pedido.
	 * 
	 * @author thnitschke
	 * @version 1.1
	 * @return retorna a lista de itens.
	 */
	public ArrayList< Item > getListItems ()
	{
		ArrayList< Item > listItens = new ArrayList< Item > (itens.size ());
		for (Iterator< ItemPedido > iterator = itens.iterator (); iterator.hasNext ();)
		{
			ItemPedido item = (ItemPedido) iterator.next ();
			listItens.add (item.getItem ());
		}
		return listItens;
	}

	/**
	 * Retorna o garçom.
	 * 
	 * @return Retorna o garcom.
	 */
	public Garcom getGarcom ()
	{
		return garcom;
	}

	/**
	 * Retorna o preço total de todos os pedidos solicitados pelo cliente.
	 * 
	 * @return Retorna o preço total dos pedidos.
	 */
	public double getPreco ()
	{
		double custoTotal = 0;

		// Pega cada objeto do tipo item. Verifica seu custo e soma
		// ao valor total
		for (int i = 0; i < itens.size (); i++)
		{
			ItemPedido pedido = itens.get (i);
			Item prato = pedido.getItem ();
			custoTotal = custoTotal + prato.getPreco ();
		}

		return custoTotal;
	}

	/**
	 * Adiciona uma lista de pedidos a partir de um pedido.
	 * 
	 * @param pedido
	 *                Recebe um pedido por parâmetro.
	 */
	public void addItens (Pedido pedido)
	{
		ArrayList< Item > list = pedido.getListItems ();
		for (int item = 0; item < list.size (); item++)
		{
			adicionaItem (list.get (item));
		}

	}

	/**
	 * (Mudança do nome para adicionaItem - seguido Relatório) Adiciona um
	 * pedido do cliente para a lista de pedidos.
	 * 
	 * @param item
	 *                Recebe um objeto do tipo Item.
	 */
	public void adicionaItem (Item item)
	{
		ItemPedido novoPedido = new ItemPedido (item, Estado.PENDENTE);
		boolean added = false;
		// Se vazio apenas adiciona o item. Caso contrário deve
		// verificar a
		// lista
		// e ordenar de acordo com a categoria do pedido.
		if (itens.isEmpty ())
		{
			itens.add (novoPedido);
		}
		else
		{
			for (int itemPedido = 0; itemPedido < itens.size (); itemPedido++)
			{
				ItemPedido pedido = itens.get (itemPedido);
				Item prato = pedido.getItem ();
				if (prato.getCategoria () == item.getCategoria ())
				{
					itens.add (itemPedido + 1, novoPedido);
					added = true;
					break;
				}
			}
			if (added == false)
			{
				itens.add (novoPedido);
			}
		}

	}

	public Mesa getMesa ()
	{
		return mesa;
	}

	/**
	 * Checa se todos ItemPedido na lista itens estão prontos (finalizados).
	 * 
	 * @author thnitschke
	 * @version 1.0
	 */
	public boolean todosItensFinalizados ()
	{
		for (Iterator< ItemPedido > iterator = itens.iterator (); iterator.hasNext ();)
		{
			ItemPedido itemPedido = (ItemPedido) iterator.next ();
			if (itemPedido.getEstado () != Estado.PRONTO)
			{
				return false;
			}
		}

		return true;
	}
}
