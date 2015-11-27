package ArchitectureModel;

import java.sql.Time;

public class RestaurantOperationServiceImpl {
	
	private Database database;
	
	public Funcionario login(String id){}
	public ArrayList<Mesa> getMesasPara(int pessoas){}
	public void sentaCliente(Mesa mesa){}
	public ArrayList<Mesa> getMesasDisponiveis(Time horario){}
	public void reservaMesa(Mesa mesa, Time horario){}
}
