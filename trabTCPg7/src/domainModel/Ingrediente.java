package domainModel;

/**
 * Classe Ingrediente.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class Ingrediente
{
	private String nome;

	/**
	 * Construtor da classe Ingrediente.
	 * 
	 * @param nome
	 *                Recebe um String para atribuir um nome ao ingrediente.
	 */
	public Ingrediente (String nome)
	{
		this.nome = nome;
	}

	/**
	 * Retorna o nome do ingrediente.
	 * 
	 * @return Retorna o nome.
	 */
	public String getNome ()
	{
		return nome;
	}

	/**
	 * Modifica o nome do Ingrediente.
	 * 
	 * @param nome
	 *                Recebe um String nome para efetuar a modificação.
	 */
	public void setNome (String nome)
	{
		this.nome = nome;
	}
}
