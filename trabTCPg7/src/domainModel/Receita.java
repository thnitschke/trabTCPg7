package domainModel;

import java.util.Map;

public class Receita
{
	private Map<Ingrediente, Double> ingredientes;
	private String instrucoes;
	
	public Map<Ingrediente, Double> getIngredientes()
	{
		return ingredientes;
	}
	public String getInstrucoes()
	{
		return instrucoes;
	}
}
