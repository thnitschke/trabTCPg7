package ArchitectureModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import domainModel.AcaoInvalidaException;
import domainModel.AuxiliarCozinha;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Pedido;
import domainModel.Setor;
import domainModel.Turno;

public class RestaurantOperationServiceImpl
		implements RestaurantOperationService
{

	
	private Database database;

	
	@Override
	public Funcionario login (String id)
	{
		database = Database.getInstanciaUnica();
			if (Database.getInstanciaUnica().getFuncionario(id) != null){
				return database.getFuncionario(id);
			} else
				return null;
	}
	

	@Override
	public ArrayList< Mesa > getMesas ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Mesa > getMesasPara (int pessoas)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Mesa > getMesasSujas ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Mesa > getMesasAbertas (Garcom garcom)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Mesa > getMesasSemReserva ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificaMesasLiberadas ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sentaCliente (Mesa mesa)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void reservaMesa (Mesa mesa, Time horario)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelaReserva()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList< Pedido > getPedidosPendentes ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void prepararItens (Pedido pedido, ArrayList< Item > itens)
	{
		return;
	}

	@Override
	public ArrayList< Item > getCardapio ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criaPedido (Mesa mesa, Pedido pedido)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public double getPrecoPedido (Mesa mesa)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fechaMesa (Mesa mesa, boolean aceita)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList< Garcom > getGarcons ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Setor > getSetores ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGarconsSetor (
			HashMap< ArrayList< Garcom >, Setor > garcomSetor)
	{
		return;

	}

	@Override
	public boolean verificaMesasFechadas ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void retirarTurnoAtivo ()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizarPedido (Pedido pedido)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList< Turno > getTurno ()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList< Ingrediente > getIngredientesEmFalta ()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
