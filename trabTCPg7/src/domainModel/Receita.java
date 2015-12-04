package domainModel;

import java.util.HashMap;

/**
 * Classe Receita.
 * 
 * @author rodri
 * @version 1.0
 */
public class Receita
{
	private HashMap< Ingrediente, Double > ingredientes;
	private String instrucoes;

	
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
	 * 
	 */
	public String getInstrucoes ()
	{
		return instrucoes;
	}
}
