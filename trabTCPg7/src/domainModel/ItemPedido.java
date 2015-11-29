package domainModel;

/**
 * Uma classe para facilitar o rastreamento da preparação de itens em pedidos.
 * Guarda um item e seu estado de preparação, com getters e setters pra cada um
 * dos atributos.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class ItemPedido
{
	private Item item;
	private Estado estado;

	/**
	 * Construtor da classe ItemPedido. (NEW)
	 * 
	 * @param item
	 *            Recebe um objeto do tipo Item.
	 * @param estado
	 *            Recebe um estado.
	 */
	public ItemPedido (Item item, Estado estado)
	{
		this.item = item;
		this.estado = estado;
	}

	/**
	 * Retorna um objeto do tipo Item.
	 * 
	 * @return Retorna um objeto do tipo Item.
	 */
	public Item getItem ()
	{
		return item;
	}

	/**
	 * Retorna o estado de um item.
	 * 
	 * @return Retorna o estado de um item.
	 */
	public Estado getEstado ()
	{
		return estado;
	}

	/**
	 * Modifica um objeto item.
	 * 
	 * @param item
	 *            Recebe um objeto do tipo Item.
	 */
	public void setItem (Item item)
	{
		this.item = item;
	}

	/**
	 * Modifica o estado de um item.
	 * 
	 * @param estado
	 *            Recebe por parâmetro um objeto do tipo Estado para fazer a
	 *            modificação.
	 */
	public void setEstado (Estado estado)
	{
		this.estado = estado;
	}

}
