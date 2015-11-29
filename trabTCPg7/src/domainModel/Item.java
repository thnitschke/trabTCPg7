package domainModel;

import java.util.Map;



/**
 * Guarda todas as informações relativas a um item do cardápio. Elas são: categoria, preço, custo e
 * tempo de preparação e a receita do item.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 * 
 */
public class Item
{
	private double preco;
	private String categoria;
	private double custo;
	private double tempo;
	
	
	
	/** (NEW)
	 * Construtor de um objeto Item.
	 * 
	 * @param preco Define o preço do item (double).
	 * @param categ Define a categoria do item (String).
	 * @param custo Define o custo do item (double).
	 * @param tempo Define o tempo de preparação (double).
	 */
	public Item (double preco, String categ, double custo, double tempo ){
		this.preco = preco;
		categoria = categ;
		this.custo = custo;
		this.tempo = tempo;
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
	public String getCategoria() 
	{
		return categoria;
	}
	
	
	
	/**
	 * Modifica a categoria de um item.
	 * 
	 * @param categoria Recebe uma String categoria para modificar o atributo "categoria".
	 */
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	
	
	
	/**
	 * Retorna o custo do Item.
	 * 
	 * @return Retorna o custo.
	 */
	public double getCusto() 
	{
		return custo;
	}
	
	
	
	/**
	 * Modifica o custo de um item.
	 * 
	 * @param custo Recebe um double custo para modificar o atributo "custo".
	 */
	public void setCusto(double custo) 
	{
		this.custo = custo;
	}
	
	
	
	/**
	 * Retorna o tempo de preparação de um item.
	 * 
	 * @return Retorna o tempo.
	 */
	public double getTempo() 
	{
		return tempo;
	}
	
	
	
	/**
	 * Modifica o tempo de preparação de um item.
	 * 
	 * @param tempo Recebe um double tempo para modificar o atributo "tempo".
	 */
	public void setTempo(double tempo) 
	{
		this.tempo = tempo;
	}
	
	
	
	/**
	 * Modifica o preço de um item.
	 * 
	 * @param preco Recebe um double preco para modificar o atributo "preco".
	 */
	public void setPreco(double preco) 
	{
		this.preco = preco;
	}
	
	
	
	///// FAZER
	public Map<Ingrediente, Double> getIngredientes ()
	{
		return null;
	}
}
