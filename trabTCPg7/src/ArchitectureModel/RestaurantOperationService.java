package ArchitectureModel;

import java.sql.Time;
import java.util.ArrayList;

public interface RestaurantOperationService {

	public Funcionario login(String id);
	public ArrayList<Mesa> getMesas();
	public ArrayList<Mesa> getMesasParaAtendimento(Garcom garcom);
	public ArrayList<Mesa> getMesasPara(int pessoas);
	public ArrayList<Mesa> getMesasSujas();
	public ArrayList<Mesa> getMesasAbertas(Garcom garcom);
	public ArrayList<Mesa> getMesasSemReserva();
	public boolean verificaMesasLiberadas();
	public void sentaCliente(Mesa mesa);
	public void reservaMesa(Mesa mesa, Time horario);
	public void cancelaReserva(Reserva reserva);
	public void liberaMesa(AuxiliarCozinha funcionario, Mesa mesa);
	public ArrayList<Pedido> getPedidosPendentes();
	public void prepararItens(Pedido pedido, ArrayList<Item> itens);
	public ArrayList<Item> getCardapio();
	public void criaPedido(Mesa mesa, Pedido pedido);
	public double getPrecoPedido(Mesa mesa);
	public void fechaMesa(Mesa mesa, boolean aceita);
	public ArrayList<Garcom> getGarcons();
	public ArrayList<Setor> getSetores();
	public void setGarconsSetor(Map<ArrayList<Garcom>,Setor>);
	public boolean verificaMesasFechadas();
	public void retirarTurnoAtivo();
	public void finalizarPedido(Pedido pedido);
	public ArrayList<Turno> getTurno();
	public ArrayList<Ingrediente> getIngredientesEmFalta();
	
	
	
	
}
