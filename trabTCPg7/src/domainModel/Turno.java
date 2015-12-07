package domainModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import ArchitectureModel.Database;

/**
 * (IMPL) Classe que guarda todas as informa��es relacionadas a um turno
 * espec�fico, como os pedidos de cada mesa, o dinheiro obtido por cada
 * funcion�rio, a distribui��o de gar�om por mesa, etc. Mant�m uma lista de
 * todos os pedidos feitos no turno, sem deletar pedidos que foram completos.
 * 
 * @author thnitschke
 * @version 1.0
 * @param custo
 *                Custo (gastos) parcial (ou total quando turno estiver fechado)
 *                do Turno.
 * @param lucro
 *                Lucro parcial (ou total quando turno estiver fechado) do
 *                Turno.
 * @param garcomSetor
 *                Mapa relacionando cada Garcom com o Setor � que foi associado
 *                neste Turno. @param, pedidos Lista de todos Pedidos do Turno.
 * @param gorjetas
 *                Mapa relacionando Funcionarios com suas gorjetas.
 */
public class Turno
{
	private String turnoCod;
	private double custo;
	private double lucro;
	private HashMap< Garcom, Setor > garcomSetor;
	private ArrayList< Pedido > pedidos;
	private HashMap< Funcionario, Double > gorjetas;

	Random number = new Random ();

	/**
	 * Construtor da classe Turno.
	 * 
	 * @author thnitschke
	 */
	public Turno ()
	{
		turnoCod = "T" + number.nextInt (1000000000);
		custo = 0;
		lucro = 0;
		garcomSetor = new HashMap< Garcom, Setor > ();
		pedidos = new ArrayList< Pedido > ();
		gorjetas = new HashMap< Funcionario, Double > ();
	}

	/**
	 * Insere o objeto Pedido passado como argumento na Lista de Pedidos
	 * pertencentes a este Turno.
	 * 
	 * @author thnitschke
	 */
	public void addPedido (Pedido pedido)
	{
		pedidos.add (pedido);
	}

	/**
	 * Retorna lista de todos Pedidos pertencentes a este Turno.
	 * 
	 * @author thnitschke
	 * @return Lista de todos Pedidos.
	 */
	public ArrayList< Pedido > getPedidos ()
	{
		return pedidos;
	}

	/**
	 * Adiciona custo ao total de custos.
	 * 
	 * @author thnitschke
	 */
	public void addCusto (double custo)
	{
		this.custo += custo;
	}

	/**
	 * Adiciona lucro ao total de lucros.
	 * 
	 * @author thnitschke
	 */
	public void addLucro (double lucro)
	{
		this.lucro += lucro;
	}

	/**
	 * Retorna objeto Setor ao qual este gar�om est� designado neste Turno.
	 * 
	 * @author thnitschke
	 * @return Objeto Setor.
	 */
	public Setor getSetor (Garcom garcom)
	{
		return garcomSetor.get (garcom);
	}

	/**
	 * Adiciona gorjeta a um objeto Funcionario. Se este Funcionario n�o
	 * houver recebido alguma gorjeta anteriormente, adiciona-se o mesmo ao
	 * Map de gorjetas, sen�o, inicializa-se o valor Double com o valor da
	 * gorjeta inicial, e ent�o adiciona-se o Funcionario ao Map.
	 * 
	 * @author thnitschke
	 */
	public void addGorjeta (Funcionario funcionario, double valor)
	{
		if (gorjetas.containsKey (funcionario))
		{
			double valorAnterior = gorjetas.get (funcionario);
			gorjetas.put (funcionario, Double.valueOf (valorAnterior + valor));
		}
		else
		{
			gorjetas.put (funcionario, new Double (valor));
		}
	}

	/**
	 * Retorna uma lista com os gar�ons deste turno.
	 * 
	 * @author thnitschke
	 * @return Lista com Gar�ons pertencentes a este Turno.
	 */
	public ArrayList< Garcom > getGarcons ()
	{
		ArrayList< Garcom > listaGarcons = new ArrayList< Garcom > (garcomSetor.size ());
		listaGarcons.addAll (garcomSetor.keySet ());

		return listaGarcons;
	}

	/**
	 * Solicita ao turno que defina os gar�ons para cada setor a partir de
	 * um dado mapa de gar�ons e setor.
	 * 
	 * @author thnitschke
	 * @version 1.1
	 * @param disposicao
	 */
	public void setGarconsSetor (HashMap< Garcom, Setor > disposicao)
	{
		garcomSetor.putAll (disposicao);
	}

	/**
	 * @author thnitschke
	 * @return Lucro Total.
	 */
	public double getSomaLucros ()
	{
		return lucro;
	}

	/**
	 * @author thnitschke
	 * @return Custo Total.
	 */
	public double getSomaCustos ()
	{
		return custo;
	}

	/**
	 * OBS: N�o est� definido na Documenta��o do Grupo. Provavelmente altera
	 * o estado de todos itens do Pedido passado como par�metro para
	 * finalizados (Estado.PRONTO).
	 * 
	 * @author thnitschke
	 * @param pedido
	 */
	public void alterarEstadoItensFinalizado (Pedido pedido)
	{
		pedido.setEstadoItens (pedido.getItens (), Estado.PRONTO);
	}

	/**
	 * OBS: N�o est� definido na Documenta��o do Grupo. Provavelmente checa
	 * se todos os itens do Pedido est�o finalizados.
	 * 
	 * @author thnitschke
	 * @param pedido
	 * @return True se todos est�o finalizados, sen�o False.
	 */
	public boolean todosItensFinalizados (Pedido pedido)
	{
		return pedido.todosItensFinalizados ();
	}

	/**
	 * OBS: N�o est� definido na Documenta��o do Grupo. Provavelmente muda o
	 * estado do Pedido passado como par�metro para finalizado..
	 * 
	 * @author thnitschke
	 * @param pedido
	 */
	public void finalizarPedido (Pedido pedido)
	{
		pedido.setEstado (Estado.PRONTO);
	}

	/**
	 * Essa fun��o, executada pelo gerente, devolve um mapa contendo um
	 * funcion�rio mais o seu respectivo sal�rio (sal�rio + gorjetas ganhas
	 * em seu turno).
	 * 
	 * @author thnitschke
	 * @return Map com o sal�rio final de todos funcion�rios participantes
	 *         do Turno.
	 */
	public HashMap< Funcionario, Double > getFolhaPgto ()
	{
		ArrayList< Funcionario > todosFuncionarios = Database.getInstanciaUnica ().getListaFuncionarios ();
		HashMap< Funcionario, Double > folhaPgto = new HashMap< Funcionario, Double > (gorjetas.size ());
		HashMap< String, Salario > todosSalarios = Database.getInstanciaUnica ().getSalarios ();
		Salario salario;

		for (Iterator< Funcionario > iterator = todosFuncionarios.iterator (); iterator.hasNext ();)
		{
			Funcionario funcionario = (Funcionario) iterator.next ();
			salario = todosSalarios.get (funcionario.getClass ().getSimpleName ());

			if (gorjetas.containsKey (funcionario))
			{
				Double gorjeta = gorjetas.get (funcionario);
				folhaPgto.put (funcionario, gorjeta + salario.getSalario () + salario.getPorcentagem () * this.getSomaLucros ());
			}
			else
			{
				folhaPgto.put (funcionario, salario.getSalario () + salario.getPorcentagem () * this.getSomaLucros ());
			}
		}

		return folhaPgto;
	}

	/**
	 * Retorna o c�digo do turno.
	 * 
	 * @return Retorna o c�digo.
	 */
	public String getTurnoCod ()
	{
		return turnoCod;
	}

	/**
	 * Troca o c�digo de um turno.
	 * 
	 * @param turnoCod
	 *                Recebe um String c�digo para atribuir um novo c�digo
	 *                para o Turno.
	 */
	public void setTurnoCod (String turnoCod)
	{
		this.turnoCod = turnoCod;
	}

	/**
	 * Remove pedido da lista de pedidos do turno.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @param pedido
	 */
	public void removePedido (Pedido pedido)
	{
		pedidos.remove (pedido);
	}
}
