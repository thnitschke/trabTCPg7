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

/**
 * Interface RestaurantOperationService. Esta interface descreve todas os
 * possiveis métodos que podem ser executados no restaurante. Para melhor
 * entendimento de cada método, visualize a classe que implementa esta Interface
 * (RestaurantOperationServiceImpl).
 * 
 * @see RestaurantOperationServiceImpl
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public interface RestaurantOperationService
{

	public Funcionario login (String id);

	public ArrayList< Mesa > getMesas ();

	public ArrayList< Mesa > getMesasParaAtendimento (Garcom garcom);

	public ArrayList< Mesa > getMesasPara (int pessoas) throws SemTurnoAtivoException;;

	public ArrayList< Mesa > getMesasSujas ();

	public ArrayList< Mesa > getMesasAbertas (Garcom garcom);

	public ArrayList< Mesa > getMesasSemReserva ();

	public boolean verificaMesasLiberadas ();

	public void sentaCliente (Mesa mesa);

	public void reservaMesa (Mesa mesa, String horario);

	public void cancelaReserva (Mesa mesa);

	public void liberaMesa (AuxiliarCozinha funcionario, Mesa mesa);

	public ArrayList< Pedido > getPedidosPendentes ();

	public void prepararItens (Pedido pedido, ArrayList< Item > itens);

	public ArrayList< Item > getCardapio ();

	public void criaPedido (Garcom garcom, Mesa mesa, ArrayList< Item > itensPedidos);

	public double getPrecoPedido (Mesa mesa) throws SemTurnoAtivoException;

	public void fechaMesa (Mesa mesa, boolean aceita) throws SemTurnoAtivoException;

	public ArrayList< Garcom > getGarcons ();

	public ArrayList< Setor > getSetores ();

	public void setGarconsSetor (HashMap< Garcom, Setor > disposicao);

	public boolean verificaMesasFechadas ();

	public void retirarTurnoAtivo ();

	public void finalizarPedido (Pedido pedido);

	public void criaTurnoAtivo ();

	public ArrayList< Turno > getTurno ();

	public ArrayList< Ingrediente > getIngredientesEmFalta ();

}
