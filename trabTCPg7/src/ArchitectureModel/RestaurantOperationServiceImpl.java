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
import domainModel.SemTurnoAtivoException;
import domainModel.Setor;
import domainModel.Turno;

public class RestaurantOperationServiceImpl implements RestaurantOperationService
{

	private Database database;

	/**
	 * Executa o login de um usuário. Recebe um id e verifica se este id
	 * existe no banco de dados (Database). Caso positivo retorna o
	 * funcionário correspon- dente, e null caso contrário.
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
	 * mesa não possua, ela é excluida da lista de mesas. Após a varredura,
	 * é retornado uma lista apenas com as mesas que possuem reserva
	 * realizada.
	 */
	@Override public ArrayList< Mesa > getMesas ()
	{

		ArrayList<Mesa> mesasReservadas= Database.getInstanciaUnica().getMesas();
		ArrayList<Mesa> mesasSemReservas = new ArrayList<>();
		
		for (int mesa = 0; mesa < mesasReservadas.size(); mesa++)
		{
			if (mesasReservadas.get(mesa).getReserva() == null)
			{
				mesasSemReservas.add(mesasReservadas.get(mesa));
			}	
		}
		
		
		for (int mesaSemreserva = 0; mesaSemreserva < mesasSemReservas.size(); mesaSemreserva++) 
		{
			for (int mesa = 0; mesa < mesasReservadas.size(); mesa++) 
			{
				if (mesasReservadas.get(mesa).equals(mesasSemReservas.get(mesaSemreserva)))
				{
					mesasReservadas.remove(mesa);	
				}	
			}
		}
		
		
		return mesasReservadas;
	}

	@Override public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * Retorna uma lista de mesas que se encontram disponíveis para uma quantidade x de pessoas.
	 * Verifica se existe um turno ativo. Caso existir, o método pega todas as mesas na Database,
	 * e filtra as mesas que estão indisponiveis ou sem a capacidade desejada. Após verificar, 
	 * esses elementos serão eliminados da lista final. Após todo o processo, a lista apenas com 
	 * as mesas que atendem os requisitos é devolvida.
	 * Caso não houver um turno ativo, uma exceção é jogada. Pois para ocupar uma mesa, é neces-
	 * sário que haja um turno ativo.
	 * 
	 * @throws SemTurnoAtivoException 
	 * @return Retorna uma lista de mesa disponível para a quantidade passada por parâmetro.
	 * 
	 */
	@Override public ArrayList< Mesa > getMesasPara (int pessoas) throws SemTurnoAtivoException
	{
		if (Database.getInstanciaUnica().hasTurnoAtivo())
		{
			
			ArrayList<Mesa> mesasDisponParaX = Database.getInstanciaUnica().getMesas();
			ArrayList<Mesa> mesasIndispParaX = new ArrayList<>();
		
		
			//Varre as mesas indisponiveis ou sem a capacidade desejada
			//e põe na lista de mesas mesasIndispParaX.
			for (int mesa = 0; mesa < mesasDisponParaX.size(); mesa++) 
			{
				if ((!mesasDisponParaX.get(mesa).isDisponivel()) || 
					(mesasDisponParaX.get(mesa).getCapacidade() < pessoas))
				{
						mesasIndispParaX.add(mesasDisponParaX.get(mesa));	
				}	
			}
		
			//Varre e filtra apenas as mesas que estão disponíveis e com a capacidade desejada.
			for (int mesaIndisponivel = 0; mesaIndisponivel < mesasIndispParaX.size(); mesaIndisponivel++) 
			{
				for (int mesa = 0; mesa < mesasDisponParaX.size(); mesa++) 
				{
					if (mesasDisponParaX.get(mesa).equals(mesasIndispParaX.get(mesaIndisponivel)))
					{
						mesasDisponParaX.remove(mesa);	
					}	
				}
			}
			
			return mesasDisponParaX;
		} 
		else 
		{
			throw new SemTurnoAtivoException("Não existe turno ativo");
		}
	}

	/**
	 * Retorna uma lista contendo todas as mesas que se encontram sujas no
	 * Restaurante. Inicia inserindo em uma lista todas as mesas disponíveis
	 * no restaurante. Após a inserção de todas as mesas, essa lista é
	 * varrida verificando todas as mesas sujas existentes. Caso a mesa
	 * esteja limpa, a mesa é excluída da lista de mesas, e mantida caso
	 * contrário. Após a varredura, o método retorna a lista de mesas sujas.
	 */
	@Override public ArrayList< Mesa > getMesasSujas ()
	{
		ArrayList<Mesa> mesasSujas = Database.getInstanciaUnica().getMesas();
		
		ArrayList<Mesa> mesasLimpas = new ArrayList<>();
		
		
		//Varre as mesas limpas e põe na lista de mesas limpas.
		for (int mesa = 0; mesa < mesasSujas.size(); mesa++) 
		{
			if (mesasSujas.get(mesa).isLimpa())
			{
				mesasLimpas.add(mesasSujas.get(mesa));	
			}	
		}
		
		for (int mesaLimpa = 0; mesaLimpa < mesasLimpas.size(); mesaLimpa++)
		{
			for (int mesa = 0; mesa < mesasSujas.size(); mesa++)
			{
				if (mesasSujas.get(mesa).equals(mesasLimpas.get(mesaLimpa)))
				{
					mesasSujas.remove(mesa);	
				}	
			}
		}
		
		return mesasSujas;
	}

	
	@Override public ArrayList< Mesa > getMesasAbertas (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retorna uma lista contendo todas as mesas que não possuem reserva.
	 * Inicia adquirindo todas as mesas do restaurante. E após adquirir a
	 * lista, ela será varrida verificando quais mesas possuem reserva. Caso
	 * a mesa possuir, esta mesa é excluída da lista de mesas. Caso
	 * contrário, a mesa será mantida. Após a exe- cução, a lista de mesas
	 * sem reserva é devolvida.
	 */
	@Override public ArrayList< Mesa > getMesasSemReserva ()
	{
		ArrayList<Mesa> mesasSemReserva = Database.getInstanciaUnica().getMesas();
		ArrayList<Mesa> mesasReservadas = new ArrayList<>();
		
		for (int mesa = 0; mesa < mesasSemReserva.size(); mesa++) 
		{
			if (mesasSemReserva.get(mesa).getReserva() != null)
			{
				mesasReservadas.add(mesasSemReserva.get(mesa));
			}	
		}
		
		for (int reservadas = 0; reservadas < mesasReservadas.size(); reservadas++) 
		{
			for (int mesa = 0; mesa < mesasSemReserva.size(); mesa++) 
			{
				if (mesasSemReserva.get(mesa).equals(mesasSemReserva.get(reservadas)))
				{
					mesasSemReserva.remove(mesa);	
				}	
			}
		}
		
		return mesasSemReserva;
	}

	@Override public boolean verificaMesasLiberadas ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * Ocupa uma mesa disponível do restaurante.
	 * 
	 * @param mesa Recebe uma mesa do restaurante.
	 * 
	 */
	@Override public void sentaCliente (Mesa mesa)
	{
		mesa.ocupa();

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
	 * Libera uma mesa para uso novamente. O método recebe um funcionario
	 * e uma mesa, e caso a mesa esteja liberada e pronto para ser liberada,
	 * o auxiliar correspondente limpa a mesa e acessa o sistema informando 
	 * que a mesa está novamente disponivel. Após a limpeza, uma gorjeta é 
	 * acionada para o auxiliar que efetuou a limpeza. Para o método executar
	 * corretamente, ele deve passar pela "LiberaMesaAction" (executado apenas
	 * pelo Auxiliar de Cozinha), que faz verificações antes de chamar este método. 
	 * 
	 */
	@Override public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa)
	{
		Funcionario auxiliar = funcionario;
		mesa.limpa();
		double gorjeta = mesa.getGorjetaLimpeza();
		Turno ativo;
		try {
			ativo = Database.getInstanciaUnica().getTurnoAtivo();
			ativo.addGorjeta(auxiliar, gorjeta);
			mesa.setGorjetaLimpeza(0);
			System.out.println("\n--- Mesa liberada e pronto pra uso! ---");
		} catch (SemTurnoAtivoException e) {
			System.out.println(e.getMessage());
		}
	

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

	/**
	 * Verifica se todas as mesas estão fechadas. Caso positivo, retornará
	 * true. Caso contrário, retornará false.
	 * 
	 */
	@Override public boolean verificaMesasFechadas ()
	{
		ArrayList<Mesa> todasMesas = Database.getInstanciaUnica().getMesas();
		
		for (int i = 0; i < todasMesas.size(); i++) {
			if ((!todasMesas.get(i).isLimpa()) || (todasMesas.get(i).isOcupada())){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Retira um turno ativo e põe na lista de turnos em database.
	 * 
	 */
	@Override public void retirarTurnoAtivo ()
	{
		Turno turnoEncerrado;
		try {
			turnoEncerrado = Database.getInstanciaUnica().getTurnoAtivo();
			Database.getInstanciaUnica().armazenaTurnoAtivo(turnoEncerrado);
		} catch (SemTurnoAtivoException e) {
			System.out.println(e.getMessage());
		}
	

	}

	@Override public void finalizarPedido (Pedido pedido)
	{
		// TODO Auto-generated method stub

	}

	@Override public ArrayList< Turno > getTurno ()
	{
		return Database.getInstanciaUnica().getTurno();
	}

	@Override public ArrayList< Ingrediente > getIngredientesEmFalta ()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
