package domainModel;

import java.util.ArrayList;
import java.util.Map;

public class Turno
{
	private double custo;
	private double lucro;
	private Map<Garcom, Setor> garcomSetor;
	private ArrayList<Pedido> pedidos;
	private Map<Funcionario, Double> gorjetas;
	
	public void addPedido (Pedido pedido)
	{
		;
	}
	public ArrayList<Pedido> getPedidos ()
	{
		;
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
		;
	}
	public void addGorjeta (Funcionario funcionario, double valor)
	{
		;
	}
	public ArrayList<Garcom> getGarcons ()
	{
		;
	}
	public void setGarconsSetor (Map<ArrayList<Garcom>, Setor> mapSetorGarcons)
	{
		;
	}
	public double getSomaLucro ()
	{
		;
	}
	public double getSomaCustos ()
	{
		;
	}
	public void alterarEstadoItensFinalizado (Pedido pedido)
	{
		;
	}
	public boolean todosItensFinalizados (Pedido pedido)
	{
		;
	}
	public void finalizarPedido (Pedido pedido)
	{
		;
	}
	public Map<Funcionario, Double> getFolhaPgto ()
	{
		;
	}
}

