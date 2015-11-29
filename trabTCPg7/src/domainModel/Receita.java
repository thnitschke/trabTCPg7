package domainModel;

import java.util.HashMap;

public class Receita
{
	private HashMap< Ingrediente, Double > ingredientes;
	private String instrucoes;

	public HashMap< Ingrediente, Double > getIngredientes ()
	{
		return ingredientes;
	}

	public String getInstrucoes ()
	{
		return instrucoes;
	}
}
