package domainModel;

/**
 * Classe que gerenciará toda a parte dos funcionários do restaurante.
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
	 * Retorna o ID de um funcionário.
	 * 
	 * @return Retorna o ID.
	 */
	public String getID ()
	{
		return ID;
	}
}
