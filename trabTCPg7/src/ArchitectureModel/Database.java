package ArchitectureModel;

import java.util.ArrayList;

public class Database {
	
	private ArrayList<Turno> turnos;
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Item> cardapio;
	private ArrayList<Setor> setores;
	private Map<Ingrediente, Double> despensa;
	
	
	public boolean hasTurnoAtivo(){}
	public Turno getTurnoAtivo(){}
	public Funcionario getFuncionario(String id){}
	public ArrayList<Mesa> getMesas(Setor setor){}
	public ArrayList<Mesa> getMesas(){}
	public void atualizaDespensa(Map<Ingrediente,Double> itensARemover){}
	public ArrayList<Item> getCardapio(){}
	public ArrayList<Setor> getSetores(){}
	public ArrayList<Turno> getTurno(){}
	public void armazenaTurnoAtivo(Turno turno){}
	public ArrayList<Ingrediente> getFaltaEstoque(){}
	

}
