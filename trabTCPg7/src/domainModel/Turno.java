package domainModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * (IMPL) Classe que guarda todas as informa��es relacionadas a um turno
 * espec�fico, como os pedidos de cada mesa, o dinheiro obtido por cada
 * funcion�rio, a distribui��o de gar�om por mesa, etc.
 * Mant�m uma lista de todos os pedidos feitos no turno, sem deletar pedidos
 * que foram completos.
 * 
 * @author thnitschke
 * @version 1.0
 * @param custo
 * 	Custo (gastos) parcial (ou total quando turno estiver fechado) do Turno.
 * @param lucro
 * 	Lucro parcial (ou total quando turno estiver fechado) do Turno.
 * @param garcomSetor
 * 	Mapa relacionando cada Garcom com o Setor � que foi associado neste
 * Turno.
 * @param, pedidos
 * 	Lista de todos Pedidos do Turno.
 * @param gorjetas
 * 	Mapa relacionando Funcionarios com suas gorjetas.
 */
public class Turno
{
	private double custo;
	private double lucro;
	private HashMap< Garcom, Setor > garcomSetor;
	private ArrayList< Pedido > pedidos;
	private HashMap< Funcionario, Double > gorjetas;

	public Turno() 
	{
		custo = 0;
		lucro = 0;
		garcomSetor = new HashMap< Garcom, Setor > ();
		pedidos = new ArrayList< Pedido > ();
		gorjetas = new HashMap< Funcionario, Double > ();
	}
	
	public void addPedido (Pedido pedido)
	{
		;
	}

	public ArrayList< Pedido > getPedidos ()
	{
		return pedidos;
	}

	public void addCusto (double custo)
	{
		;
	}

	public void addLucro (double lucro)
	{
		;
	}

	public Setor getSetor (Garcom garcom)
	{
		return null;
	}

	public void addGorjeta (Funcionario funcionario, double valor)
	{
		;
	}

	/**
	 * Retorna uma lista com os gar�ons deste turno.
	 * 
	 * @author thnitschke
	 * @return Lista com Gar�ons pertencentes a este Turno.
	 */
	public ArrayList< Garcom > getGarcons ()
	{
		ArrayList< Garcom > listaGarcons = new ArrayList< Garcom > (
				garcomSetor.size ());
		listaGarcons.addAll (garcomSetor.keySet ());

		return listaGarcons;
	}
	
	/**
	 * 
	 * @param mapSetorGarcons
	 * 	OBS: Par�metros n�o fazem sentido. MODIFICAR.
	 * 	OBS2: N�o h� como acessar Database para resgatar todos os Garcons
	 * 	existentes. Considerar a implementa��o do padr�o de design Singleton
	 * 	para a classe Database.
	 */
	public void setGarconsSetor (
			HashMap< ArrayList< Garcom >, Setor > mapSetorGarcons)
	{
		;
	}

	public double getSomaLucro ()
	{
		return custo;
	}

	public double getSomaCustos ()
	{
		return custo;
	}

	public void alterarEstadoItensFinalizado (Pedido pedido)
	{
		;
	}

	public boolean todosItensFinalizados (Pedido pedido)
	{
		return false;
	}

	public void finalizarPedido (Pedido pedido)
	{
		;
	}

	public HashMap< Funcionario, Double > getFolhaPgto ()
	{
		return gorjetas;
	}
}
