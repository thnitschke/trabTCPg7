package domainModel;

import java.util.HashMap;

/**
 * Guarda todas as informações relativas a um item do cardápio. Elas são:
 * categoria, preço, custo e tempo de preparação e a receita do item.
 * 
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.1
 */
public class Item
{
	private double preco;
	private String categoria;
	private double custo;
	private double tempo;
	private Receita receita;

	/**
	 * (MOD) Construtor de um objeto Item.
	 * MODIF: Inserido parâmetro receita, para poder-se determinar qual a
	 * classe Receita deste tipo de Item.
	 * 
	 * @author RodrigoOkido, thnitschke
	 * @version 1.1
	 * @param preco
	 *                Define o preço do item (double).
	 * @param categ
	 *                Define a categoria do item (String).
	 * @param custo
	 *                Define o custo do item (double).
	 * @param tempo
	 *                Define o tempo de preparação (double).
	 * @param receita
	 *                (NEW) Define a receita do item (Receita).
	 */
	public Item (double preco, String categ, double custo, double tempo,
			Receita receita)
	{
		this.preco = preco;
		categoria = categ;
		this.custo = custo;
		this.tempo = tempo;
		this.setReceita (receita);	// (NEW)
	}

	/**
	 * Retorna o preço do item.
	 * 
	 * @return Retorna o preço.
	 */
	public double getPreco ()
	{
		return preco;
	}

	/**
	 * Retorna a categoria do item.
	 * 
	 * @return Retorna a categoria.
	 */
	public String getCategoria ()
	{
		return categoria;
	}

	/**
	 * Modifica a categoria de um item.
	 * 
	 * @param categoria
	 *                Recebe uma String categoria para modificar o atributo
	 *                "categoria".
	 */
	public void setCategoria (String categoria)
	{
		this.categoria = categoria;
	}

	/**
	 * Retorna o custo do Item.
	 * 
	 * @return Retorna o custo.
	 */
	public double getCusto ()
	{
		return custo;
	}

	/**
	 * Modifica o custo de um item.
	 * 
	 * @param custo
	 *                Recebe um double custo para modificar o atributo
	 *                "custo".
	 */
	public void setCusto (double custo)
	{
		this.custo = custo;
	}

	/**
	 * Retorna o tempo de preparação de um item.
	 * 
	 * @return Retorna o tempo.
	 */
	public double getTempo ()
	{
		return tempo;
	}

	/**
	 * Modifica o tempo de preparação de um item.
	 * 
	 * @param tempo
	 *                Recebe um double tempo para modificar o atributo
	 *                "tempo".
	 */
	public void setTempo (double tempo)
	{
		this.tempo = tempo;
	}

	/**
	 * Modifica o preço de um item.
	 * 
	 * @param preco
	 *                Recebe um double preco para modificar o atributo
	 *                "preco".
	 */
	public void setPreco (double preco)
	{
		this.preco = preco;
	}

	/**
	 * @return receita
	 */
	public Receita getReceita ()
	{
		return receita;
	}

	/**
	 * @param receita
	 *                do item OBS: Não seria preciso modificar a Receita de
	 *                um Item após o mesmo já ter sido criado.
	 */
	public void setReceita (Receita receita)
	{
		this.receita = receita;
	}

	/**
	 * (MOD) Método getIngredientes: Coleta do atributo receita os
	 * Ingredientes que compõem o Item da classe, e retorna um "mapa" com os
	 * Ingredientes do mesmo e suas quantidades.
	 * 
	 * MODIFICAÇÃO: Map é apenas interface, trocou-se o mesmo para HashMap,
	 * que é a mais apropriada das classes que implementam Map para a
	 * função que estamos utilizando.
	 * 
	 * @see HashMap
	 * @return HashMap com Ingredientes mapeados para sua quantidade.
	 * @author thnitschke
	 * @version 1.0
	 */
	public HashMap< Ingrediente, Double > getIngredientes ()
	{
		return receita.getIngredientes ();
	}
}
