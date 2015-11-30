package ArchitectureModel;

import java.util.ArrayList;
import java.util.HashMap;

import domainModel.Funcionario;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Setor;
import domainModel.Turno;

/**
 * A base de dados guarda todas as informa��es hist�ricas do restaurante, al�m
 * da despensa atual e outras informa��es est�veis do restaurante, como lista de
 * funcion�rios, card�pio e mesas.
 * 
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.0
 * 
 * OBS: Provavelmente precisa ser mudada. Uma Base de Dados s� pode em teoria
 * ter uma inst�ncia. Para garantir que isso aconte�a e que haja um modo de acessar
 * a classe globalmente, pode-se implent�-la com o padr�o de design chamado "Singleton".
 */
public class Database
{

	private ArrayList< Turno > listaTurnos;
	private ArrayList< Funcionario > listaFuncionarios;
	private ArrayList< Item > cardapio;
	private ArrayList< Setor > listaSetores;
	private ArrayList< Mesa > listaMesas; // NEW
	private HashMap< Ingrediente, Double > despensa;

	public boolean hasTurnoAtivo ()
	{
		return false;
	}

	public Turno getTurnoAtivo ()
	{
		return null;
	}

	/**
	 * Pesquisa se existe algum funcion�rio com o id recebido por par�metro.
	 * Caso positivo ele retorna o funcionario, caso contrario retorna null.
	 * 
	 * @param id
	 *                Recebe um id do tipo String
	 * @return Retorna o funcionario caso encontrar, e null caso contr�rio.
	 */
	public Funcionario getFuncionario (String id)
	{
		for (int funcionario = 0; funcionario < listaFuncionarios
				.size (); funcionario++)
		{
			Funcionario check = listaFuncionarios.get (funcionario);
			if (id.equals (check.getID ()))
			{
				return check;
			}
		}
		return null;
	}

	/**
	 * Retorna a lista de mesas de um determinado setor dado por par�metro.
	 * 
	 * @param setor
	 *                Recebe um setor.
	 * @return Retorna a lista de mesas deste setor.
	 */
	public ArrayList< Mesa > getMesas (Setor setor)
	{
		return setor.getMesas ();
	}

	/**
	 * Retorna a lista de mesas de todo o restaurante.
	 * 
	 * @return Retorna a lista de mesas.
	 */
	public ArrayList< Mesa > getMesas ()
	{
		return listaMesas;
	}

	public void atualizaDespensa (
			HashMap< Ingrediente, Double > itensARemover)
	{

	}

	/**
	 * Retorna a lista de card�pio do restaurante.
	 * 
	 * @return Retorna esta lista.
	 */
	public ArrayList< Item > getCardapio ()
	{
		return cardapio;
	}

	/**
	 * Retorna a lista de setores existentes.
	 * 
	 * @return Retorna a lista.
	 */
	public ArrayList< Setor > getSetores ()
	{
		return listaSetores;
	}

	/**
	 * Retorna a lista de turnos do restaurante.
	 * 
	 * @return Retorna esta lista.
	 */
	public ArrayList< Turno > getTurno ()
	{
		return listaTurnos;
	}

	public void armazenaTurnoAtivo (Turno turno)
	{
	}

	public ArrayList< Ingrediente > getFaltaEstoque ()
	{
		return null;
	}

}
