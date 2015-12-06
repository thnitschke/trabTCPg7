package ArchitectureModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import domainModel.AuxiliarCozinha;
import domainModel.Estado;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Pedido;
import domainModel.SemTurnoAtivoException;
import domainModel.Setor;
import domainModel.Turno;

public class RestaurantOperationServiceImpl implements RestaurantOperationService
{

	private Database database;

	/**
	 * Construtor de RestaurantOperationServiceImpl: Apenas salva refer�ncia
	 * ao Database �nico para n�o haver chamadas excessivas do m�todo
	 * est�tico Database.getInstanciaUnica().
	 * 
	 * @author thnitschke
	 * @version 1.0
	 */
	public RestaurantOperationServiceImpl ()
	{
		database = Database.getInstanciaUnica ();
	}

	/**
	 * Executa o login de um usu�rio. Recebe um id e verifica se este id
	 * existe no banco de dados (Database). Caso positivo retorna o
	 * funcion�rio correspondente, e null caso contr�rio.
	 */
	@Override public Funcionario login (String id)
	{
		if (database.getFuncionario (id) != null)
		{
			return database.getFuncionario (id);
		}
		else
			return null;
	}

	/**
	 * Retorna a lista de mesas que possuem reserva. Pega a lista de mesas a
	 * partir da Database, e varre procurando por mesas com reserva. Caso a
	 * mesa n�o possua, ela � excluida da lista de mesas. Ap�s a varredura,
	 * � retornado uma lista apenas com as mesas que possuem reserva
	 * realizada.
	 */
	@Override public ArrayList< Mesa > getMesas ()
	{

		ArrayList< Mesa > mesasReservadas = database.getMesas ();
		ArrayList< Mesa > mesasSemReservas = new ArrayList< > ();

		for (int mesa = 0; mesa < mesasReservadas.size (); mesa++)
		{
			if (mesasReservadas.get (mesa).getReserva () == null)
			{
				mesasSemReservas.add (mesasReservadas.get (mesa));
			}
		}

		for (int mesaSemreserva = 0; mesaSemreserva < mesasSemReservas.size (); mesaSemreserva++)
		{
			for (int mesa = 0; mesa < mesasReservadas.size (); mesa++)
			{
				if (mesasReservadas.get (mesa).equals (mesasSemReservas.get (mesaSemreserva)))
				{
					mesasReservadas.remove (mesa);
				}
			}
		}

		return mesasReservadas;
	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom)
	{
		Turno ativo;
		ArrayList< Mesa > mesasFiltradas = null;
		try
		{
			ativo = database.getTurnoAtivo ();
			Setor setor = ativo.getSetor (garcom);
			ArrayList< Mesa > mesasSetor = database.getMesas (setor);
			mesasFiltradas = new ArrayList< Mesa > ();

			for (Iterator< Mesa > iterator = mesasSetor.iterator (); iterator.hasNext ();)
			{
				Mesa mesa = (Mesa) iterator.next ();
				if (mesa.isOcupada () && (mesa.getPedido () == null || mesa.getPedido ().getGarcom ().equals (garcom)))
					mesasFiltradas.add (mesa);
				else
					continue;
			}
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}

		return mesasFiltradas;
	}

	/**
	 * Retorna uma lista de mesas que se encontram dispon�veis para uma
	 * quantidade x de pessoas. Verifica se existe um turno ativo. Caso
	 * existir, o m�todo pega todas as mesas na Database, e filtra as mesas
	 * que est�o indisponiveis ou sem a capacidade desejada. Ap�s verificar,
	 * esses elementos ser�o eliminados da lista final. Ap�s todo o
	 * processo, a lista apenas com as mesas que atendem os requisitos �
	 * devolvida. Caso n�o houver um turno ativo, uma exce��o � jogada. Pois
	 * para ocupar uma mesa, � neces- s�rio que haja um turno ativo.
	 * 
	 * @throws SemTurnoAtivoException
	 * @return Retorna uma lista de mesa dispon�vel para a quantidade
	 *         passada por par�metro.
	 */
	@Override public ArrayList< Mesa > getMesasPara (int pessoas) throws SemTurnoAtivoException
	{
		if (database.hasTurnoAtivo ())
		{

			ArrayList< Mesa > mesasDisponParaX = database.getMesas ();
			ArrayList< Mesa > mesasIndispParaX = new ArrayList< > ();

			// Varre as mesas indisponiveis ou sem a capacidade
			// desejada
			// e p�e na lista de mesas mesasIndispParaX.
			for (int mesa = 0; mesa < mesasDisponParaX.size (); mesa++)
			{
				if ( (!mesasDisponParaX.get (mesa).isDisponivel ()) || (mesasDisponParaX.get (mesa).getCapacidade () < pessoas))
				{
					mesasIndispParaX.add (mesasDisponParaX.get (mesa));
				}
			}

			// Varre e filtra apenas as mesas que est�o dispon�veis
			// e com a capacidade desejada.
			for (int mesaIndisponivel = 0; mesaIndisponivel < mesasIndispParaX.size (); mesaIndisponivel++)
			{
				for (int mesa = 0; mesa < mesasDisponParaX.size (); mesa++)
				{
					if (mesasDisponParaX.get (mesa).equals (mesasIndispParaX.get (mesaIndisponivel)))
					{
						mesasDisponParaX.remove (mesa);
					}
				}
			}

			return mesasDisponParaX;
		}
		else
		{
			throw new SemTurnoAtivoException ("N�o existe turno ativo");
		}
	}

	/**
	 * Retorna uma lista contendo todas as mesas que se encontram sujas no
	 * Restaurante. Inicia inserindo em uma lista todas as mesas dispon�veis
	 * no restaurante. Ap�s a inser��o de todas as mesas, essa lista �
	 * varrida verificando todas as mesas sujas existentes. Caso a mesa
	 * esteja limpa, a mesa � exclu�da da lista de mesas, e mantida caso
	 * contr�rio. Ap�s a varredura, o m�todo retorna a lista de mesas sujas.
	 */
	@Override public ArrayList< Mesa > getMesasSujas ()
	{
		ArrayList< Mesa > mesasSujas = database.getMesas ();

		ArrayList< Mesa > mesasLimpas = new ArrayList< > ();

		// Varre as mesas limpas e p�e na lista de mesas limpas.
		for (int mesa = 0; mesa < mesasSujas.size (); mesa++)
		{
			if (mesasSujas.get (mesa).isLimpa ())
			{
				mesasLimpas.add (mesasSujas.get (mesa));
			}
		}

		for (int mesaLimpa = 0; mesaLimpa < mesasLimpas.size (); mesaLimpa++)
		{
			for (int mesa = 0; mesa < mesasSujas.size (); mesa++)
			{
				if (mesasSujas.get (mesa).equals (mesasLimpas.get (mesaLimpa)))
				{
					mesasSujas.remove (mesa);
				}
			}
		}

		return mesasSujas;
	}

	@Override public ArrayList< Mesa > getMesasAbertas (Garcom garcom)
	{
		Turno ativo;
		ArrayList< Mesa > mesasFiltradas = new ArrayList<>();
		try
		{
			ativo = database.getTurnoAtivo ();
			Setor setor = ativo.getSetor (garcom);
			ArrayList< Mesa > mesasSetor = database.getMesas (setor);
			mesasFiltradas = new ArrayList< Mesa > ();

			for (Iterator< Mesa > iterator = mesasSetor.iterator (); iterator.hasNext ();)
			{
				Mesa mesa = (Mesa) iterator.next ();
				if (mesa.isOcupada () && mesa.getPedido ().getGarcom ().equals (garcom))
					mesasFiltradas.add (mesa);
				else
					continue;
			}
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}

		return mesasFiltradas;
	}

	/**
	 * Retorna uma lista contendo todas as mesas que n�o possuem reserva.
	 * Inicia adquirindo todas as mesas do restaurante. E ap�s adquirir a
	 * lista, ela ser� varrida verificando quais mesas possuem reserva. Caso
	 * a mesa possuir, esta mesa � exclu�da da lista de mesas. Caso
	 * contr�rio, a mesa ser� mantida. Ap�s a exe- cu��o, a lista de mesas
	 * sem reserva � devolvida.
	 */
	@Override public ArrayList< Mesa > getMesasSemReserva ()
	{
		ArrayList< Mesa > mesasSemReserva = database.getMesas ();
		ArrayList< Mesa > mesasReservadas = new ArrayList< > ();

		for (int mesa = 0; mesa < mesasSemReserva.size (); mesa++)
		{
			if (mesasSemReserva.get (mesa).getReserva () != null)
			{
				mesasReservadas.add (mesasSemReserva.get (mesa));
			}
		}

		for (int reservadas = 0; reservadas < mesasReservadas.size (); reservadas++)
		{
			for (int mesa = 0; mesa < mesasSemReserva.size (); mesa++)
			{
				if (mesasSemReserva.get (mesa).equals (mesasReservadas.get (reservadas)))
				{
					mesasSemReserva.remove (mesa);
				}
			}
		}

		return mesasSemReserva;
	}

	/**
	 * @author thnitschke
	 */
	@Override public boolean verificaMesasLiberadas ()
	{
		ArrayList< Mesa > mesasSetor = database.getMesas ();

		for (Iterator< Mesa > iterator = mesasSetor.iterator (); iterator.hasNext ();)
		{
			Mesa mesa = (Mesa) iterator.next ();
			if (mesa.isDisponivel ())
				continue;
			else
				return false;
		}

		return true;
	}

	/**
	 * Ocupa uma mesa dispon�vel do restaurante.
	 * 
	 * @param mesa
	 *                Recebe uma mesa do restaurante.
	 */
	@Override public void sentaCliente (Mesa mesa)
	{
		mesa.ocupa ();

	}

	/**
	 * Reserva uma determinada mesa em um determinado horario.
	 */
	@Override public void reservaMesa (Mesa mesa, String horario)
	{
		mesa.setReserva (horario);
	}

	/**
	 * Cancela uma reserva feita anteriormente em uma mesa.
	 */
	@Override public void cancelaReserva (Mesa mesa)
	{
		mesa.setReserva (null);
	}

	/**
	 * Libera uma mesa para uso novamente. O m�todo recebe um funcionario e
	 * uma mesa, e caso a mesa esteja liberada e pronto para ser liberada, o
	 * auxiliar correspondente limpa a mesa e acessa o sistema informando
	 * que a mesa est� novamente disponivel. Ap�s a limpeza, uma gorjeta �
	 * acionada para o auxiliar que efetuou a limpeza. Para o m�todo
	 * executar corretamente, ele deve passar pela "LiberaMesaAction"
	 * (executado apenas pelo Auxiliar de Cozinha), que faz verifica��es
	 * antes de chamar este m�todo.
	 */
	@Override public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa)
	{
		Funcionario auxiliar = funcionario;
		mesa.limpa ();
		double gorjeta = mesa.getGorjetaLimpeza ();
		Turno ativo;
		try
		{
			ativo = database.getTurnoAtivo ();
			ativo.addGorjeta (auxiliar, gorjeta);
			mesa.setGorjetaLimpeza (0);
			System.out.println ("\n--- Mesa liberada e pronta pra uso! ---");
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}

	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Pedido > getPedidosPendentes ()
	{
		Turno ativo;
		ArrayList< Pedido > pedidosPendentes = null;

		try
		{
			ativo = database.getTurnoAtivo ();
			pedidosPendentes = new ArrayList< Pedido > ();
			ArrayList< Pedido > todosPedidos = ativo.getPedidos ();

			if (todosPedidos.isEmpty()){
				System.out.println("\n>>--- N�o existem pedidos pendentes ---<< \n");
			} else{
				for (Iterator< Pedido > iterator = todosPedidos.iterator (); iterator.hasNext ();)
				{
					Pedido pedido = (Pedido) iterator.next ();
					if (pedido.getEstado () == Estado.PENDENTE)
						pedidosPendentes.add (pedido);
					else
						continue;
				}
			}
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}

		return pedidosPendentes;
	}

	/**
	 * @author thnitschke
	 */
	public void prepararItens (Pedido pedido, ArrayList< Item > itens)
	{
		return;
	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Item > getCardapio ()
	{
		return database.getCardapio ();
	}

	/**
	 * @author thnitschke
	 */
	@Override public void criaPedido (Garcom garcom, Mesa mesa, ArrayList< Item > itensPedidos)
	{
		Turno ativo;
		Pedido pedido;
		try
		{
			ativo = database.getTurnoAtivo ();

			for (Iterator< Item > iterator = itensPedidos.iterator (); iterator.hasNext ();)
			{
				Item item = (Item) iterator.next ();
				if (item.getCategoria ().equalsIgnoreCase ("bebidas"))
				{
					ativo.addCusto (item.getCusto ());
					itensPedidos.remove (item);
				}
			}

			if (mesa.getPedido () == null)
			{
				pedido = new Pedido (garcom, mesa);
				mesa.setPedido (pedido);
				ativo.addPedido (pedido);
			}
			else
				pedido = mesa.getPedido ();

			pedido.addItens (itensPedidos);
			pedido.setEstado (Estado.PENDENTE);
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}
	}

	/**
	 * @author thnitschke
	 */
	@Override public double getPrecoPedido (Mesa mesa) throws SemTurnoAtivoException
	{
		return mesa.getPedido ().getPreco ();
	}

	/**
	 * @author thnitschke
	 */
	@Override public void fechaMesa (Mesa mesa, boolean aceita) throws SemTurnoAtivoException
	{
		Turno turnoAtivo = database.getTurnoAtivo ();
		double custoTotal = mesa.getPedido ().getCusto ();
		if (aceita)
		{
			double precoTotal = mesa.getPedido ().getPreco ();
			double gorjetas = 0.1 * precoTotal;
			double gorjetaGarcom = 0.7 * gorjetas;
			double gorjetaAuxiliar = 0.3 * gorjetas;
			double lucroTotal = precoTotal - custoTotal - gorjetas;

			turnoAtivo.addCusto (custoTotal);
			turnoAtivo.addLucro (lucroTotal);
			turnoAtivo.addGorjeta (mesa.getPedido ().getGarcom (), gorjetaGarcom);
			mesa.setGorjetaLimpeza (gorjetaAuxiliar);
		}
		else
		{
			turnoAtivo.addCusto (custoTotal);
		}
		mesa.desocupa ();
	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Garcom > getGarcons ()
	{
		Turno ativo;
		ArrayList< Garcom > garconsAtivos = null;
		try
		{
			ativo = database.getTurnoAtivo ();
			garconsAtivos = ativo.getGarcons ();
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}
		return garconsAtivos;
	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Setor > getSetores ()
	{
		return database.getSetores ();
	}

	@Override public void setGarconsSetor (HashMap< Garcom, Setor > disposicao)
	{
		Turno ativo;
		try
		{
			ativo = database.getTurnoAtivo ();
			ativo.setGarconsSetor (disposicao);
			System.out.println ("\n>>--- Gar�ons designados com sucesso! ---<< \n");
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}
	}

	/**
	 * Verifica se todas as mesas est�o fechadas. Caso positivo, retornar�
	 * true. Caso contr�rio, retornar� false.
	 */
	@Override public boolean verificaMesasFechadas ()
	{
		ArrayList< Mesa > todasMesas = database.getMesas ();

		for (int i = 0; i < todasMesas.size (); i++)
		{
			if ( (!todasMesas.get (i).isLimpa ()) || (todasMesas.get (i).isOcupada ()))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Retira um turno ativo e p�e na lista de turnos em database.
	 */
	@Override public void retirarTurnoAtivo ()
	{
		Turno turnoEncerrado;
		try
		{
			turnoEncerrado = database.getTurnoAtivo ();
			database.armazenaTurnoAtivo (turnoEncerrado);
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}
	}

	/**
	 * Finaliza um pedido.
	 */
	@Override public void finalizarPedido (Pedido pedido)
	{
		Turno turnoAtivo;
		try
		{
			turnoAtivo = database.getTurnoAtivo ();
			turnoAtivo.removePedido (pedido);
		}
		catch (SemTurnoAtivoException e)
		{
			System.out.println (e.getMessage ());
		}
	}

	/**
	 * Cria um turno.
	 */
	public void criaTurnoAtivo ()
	{
		database.criaTurnoAtivo ();
	}

	/**
	 * Retorna a lista de turnos.
	 */
	@Override public ArrayList< Turno > getTurno ()
	{
		return database.getTurno ();
	}

	/**
	 * @author thnitschke
	 */
	@Override public ArrayList< Ingrediente > getIngredientesEmFalta ()
	{
		return database.getFaltaEstoque ();
	}

}
