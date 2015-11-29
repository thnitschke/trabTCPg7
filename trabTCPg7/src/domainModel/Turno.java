package domainModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author thnitschke
 *
 */
public class Turno
{
	private double custo;
	private double lucro;
	private HashMap< Garcom, Setor > garcomSetor;
	private ArrayList< Pedido > pedidos;
	private HashMap< Funcionario, Double > gorjetas;

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

	public ArrayList< Garcom > getGarcons ()
	{
		return null;
	}

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
