package domainModel;

import java.util.ArrayList;

/**
 * Classe Setor. Define os setores do restaurante.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class Setor
{

	private ArrayList< Mesa > mesas;
	private String nome;

	/**
	 * Construtor da classe Setor.
	 * 
	 * @param nome
	 *                Recebe um String para ser o nome do setor.
	 */
	public Setor (String nome)
	{
		this.setNome (nome);
		mesas = new ArrayList< > ();
	}

	/**
	 * Retorna a lista de mesas.
	 * 
	 * @return Retorna a lista de mesas.
	 */
	public ArrayList< Mesa > getMesas ()
	{
		return mesas;
	}

	/**
	 * (NEW) M�todo para adicionar objeto Mesa ao Setor durante a cria��o do
	 * mesmo. Caso contr�rio n�o se poderia adicionar objetos Mesa de modo
	 * que n�o ferisse o padr�o da POO.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @param Objeto
	 *                Mesa a ser adicionado ao Setor.
	 */
	public void addMesa (Mesa mesa)
	{
		mesas.add (mesa);
	}

	/**
	 * Retorna o nome do setor.
	 * 
	 * @return O nome.
	 */
	public String getNome ()
	{
		return nome;
	}

	/**
	 * Modifica o nome de um setor.
	 * 
	 * @param nome
	 *                Recebe o nome do setor.
	 */
	public void setNome (String nome)
	{
		this.nome = nome;
	}

}
