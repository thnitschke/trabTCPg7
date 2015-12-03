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

/**
 * Interface RestaurantOperationService. Esta interface descreve todas os
 * possiveis métodos que podem ser executados no restaurante. Para melhor
 * entendimento de cada método, visualize a classe que implementa esta Interface
 * (RestaurantOperationServiceImpl).
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public interface RestaurantOperationService
{

	public Funcionario login (String id) ;

	public ArrayList< Mesa > getMesas ();

	public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom);

	public ArrayList< Mesa > getMesasPara (int pessoas);

	public ArrayList< Mesa > getMesasSujas ();

	public ArrayList< Mesa > getMesasAbertas (Garcom garcom);

	public ArrayList< Mesa > getMesasSemReserva ();

	public boolean verificaMesasLiberadas ();

	public void sentaCliente (Mesa mesa);

	public void reservaMesa (Mesa mesa, Time horario);

	public void cancelaReserva (Reserva reserva);

	public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa);

	public ArrayList< Pedido > getPedidosPendentes ();

	public void prepararItens (Pedido pedido, ArrayList< Item > itens);

	public ArrayList< Item > getCardapio ();

	public void criaPedido (Mesa mesa, Pedido pedido);

	public double getPrecoPedido (Mesa mesa);

	public void fechaMesa (Mesa mesa, boolean aceita);

	public ArrayList< Garcom > getGarcons ();

	public ArrayList< Setor > getSetores ();

	public void setGarconsSetor (
			HashMap< ArrayList< Garcom >, Setor > garcomSetor);

	public boolean verificaMesasFechadas ();

	public void retirarTurnoAtivo ();

	public void finalizarPedido (Pedido pedido);

	public ArrayList< Turno > getTurno ();

	public ArrayList< Ingrediente > getIngredientesEmFalta ();

}
