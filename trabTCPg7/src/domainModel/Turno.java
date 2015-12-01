package domainModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * (IMPL) Classe que guarda todas as informações relacionadas a um turno
 * específico, como os pedidos de cada mesa, o dinheiro obtido por cada
 * funcionário, a distribuição de garçom por mesa, etc.
 * Mantém uma lista de todos os pedidos feitos no turno, sem deletar pedidos
 * que foram completos.
 * 
 * @author thnitschke
 * @version 1.0
 * @param custo
 * 	Custo (gastos) parcial (ou total quando turno estiver fechado) do Turno.
 * @param lucro
 * 	Lucro parcial (ou total quando turno estiver fechado) do Turno.
 * @param garcomSetor
 * 	Mapa relacionando cada Garcom com o Setor à que foi associado neste
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
	 * Retorna uma lista com os garçons deste turno.
	 * 
	 * @author thnitschke
	 * @return Lista com Garçons pertencentes a este Turno.
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
	 * 	OBS: Parâmetros não fazem sentido. MODIFICAR.
	 * 	OBS2: Não há como acessar Database para resgatar todos os Garcons
	 * 	existentes. Considerar a implementação do padrão de design Singleton
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
