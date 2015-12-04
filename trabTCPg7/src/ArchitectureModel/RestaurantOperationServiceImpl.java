package ArchitectureModel;

import java.util.ArrayList;
import java.util.HashMap;

import domainModel.AuxiliarCozinha;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Pedido;
import domainModel.Setor;
import domainModel.Turno;

public class RestaurantOperationServiceImpl implements RestaurantOperationService
{

	private Database database;

	/**
	 * Executa o login de um usu�rio. Recebe um id e verifica se este id
	 * existe no banco de dados (Database). Caso positivo retorna o
	 * funcion�rio correspon- dente, e null caso contr�rio.
	 */
	@Override public Funcionario login (String id)
	{
		database = Database.getInstanciaUnica ();

		if (Database.getInstanciaUnica ().getFuncionario (id) != null)
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

		ArrayList< Mesa > mesasReservadas = Database.getInstanciaUnica ().getMesas ();
		for (int mesa = 0; mesa < mesasReservadas.size (); mesa++)
		{
			if (mesasReservadas.get (mesa).getReserva () == null)
			{
				mesasReservadas.remove (mesa);
			}
		}

		return mesasReservadas;
	}

	@Override public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override public ArrayList< Mesa > getMesasPara (int pessoas)
	{
		// TODO Auto-generated method stub
		return null;
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
		ArrayList< Mesa > todasMesasRes = Database.getInstanciaUnica ().getMesas ();
		for (int mesa = 0; mesa < todasMesasRes.size (); mesa++)
		{
			System.out.println (mesa);
			System.out.println (todasMesasRes.get (mesa));
		}

		for (int mesa = 0; mesa < todasMesasRes.size (); mesa++)
		{
			if (todasMesasRes.get (mesa).isLimpa ())
			{
				todasMesasRes.remove (mesa);
			}
		}
		return todasMesasRes;
	}

	@Override public ArrayList< Mesa > getMesasAbertas (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
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
		ArrayList< Mesa > mesasSemReserva = Database.getInstanciaUnica ().getMesas ();
		for (int mesa = 0; mesa < mesasSemReserva.size (); mesa++)
		{
			if (mesasSemReserva.get (mesa).getReserva () != null)
			{
				mesasSemReserva.remove (mesa);
			}
		}
		return mesasSemReserva;
	}

	@Override public boolean verificaMesasLiberadas ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override public void sentaCliente (Mesa mesa)
	{
		// TODO Auto-generated method stub

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

	@Override public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa)
	{
		// TODO Auto-generated method stub

	}

	@Override public ArrayList< Pedido > getPedidosPendentes ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void prepararItens (Pedido pedido, ArrayList< Item > itens)
	{
		return;
	}

	@Override public ArrayList< Item > getCardapio ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override public void criaPedido (Mesa mesa, Pedido pedido)
	{
		// TODO Auto-generated method stub

	}

	@Override public double getPrecoPedido (Mesa mesa)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override public void fechaMesa (Mesa mesa, boolean aceita)
	{
		// TODO Auto-generated method stub

	}

	@Override public ArrayList< Garcom > getGarcons ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override public ArrayList< Setor > getSetores ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override public void setGarconsSetor (HashMap< ArrayList< Garcom >, Setor > garcomSetor)
	{
		return;

	}

	@Override public boolean verificaMesasFechadas ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override public void retirarTurnoAtivo ()
	{
		// TODO Auto-generated method stub

	}

	@Override public void finalizarPedido (Pedido pedido)
	{
		// TODO Auto-generated method stub

	}

	@Override public ArrayList< Turno > getTurno ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override public ArrayList< Ingrediente > getIngredientesEmFalta ()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
