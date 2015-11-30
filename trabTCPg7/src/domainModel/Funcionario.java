package domainModel;

/**
 * Classe que gerenciar� toda a parte dos funcion�rios do restaurante.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public abstract class Funcionario
{
	private String ID;

	public Funcionario (String id)
	{
		ID = id;
	}

	/**
	 * Retorna o ID de um funcion�rio.
	 * 
	 * @return Retorna o ID.
	 */
	public String getID ()
	{
		return ID;
	}
}
