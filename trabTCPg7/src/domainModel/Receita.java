package domainModel;

import java.util.HashMap;

/**
 * Classe Receita.
 * 
 * @author RodrigoOkido, thnitschke
 * @version 1.1
 */
public class Receita
{
	private HashMap< Ingrediente, Double > ingredientes;
	private String instrucoes;

	/**
	 * (NEW) Construtor de Receita
	 * 
	 * @author thnitschke
	 */
	public Receita (HashMap< Ingrediente, Double > indreditentes, String intrucoes)
	{
		this.ingredientes = new HashMap< Ingrediente, Double > (indreditentes);
		this.instrucoes = new String (intrucoes);
	}

	/**
	 * Retorna a quantidade de ingrediente.
	 * 
	 * @return Retorna a quantidade.
	 */
	public HashMap< Ingrediente, Double > getIngredientes ()
	{
		return ingredientes;
	}

	/**
	 * Retorna como fazer a receita.
	 * 
	 * @return Retorna uma String "instrucoes" orientando a receita.
	 */
	public String getInstrucoes ()
	{
		return instrucoes;
	}
}
