package ArchitectureModel;

import java.util.ArrayList;

public class RestaurantInterface {
	
	private ArrayList<UIAction> acoes;
	private Funcionario funcionario;
	

	public void login (Funcionario funcionario){}
	public void logout(){}
	public boolean isLoggedIn(){}
	protected void setWrongLogin(){}
	protected void setAcoesGarcom(){}
	protected void setAcoesCozinheiro(){}
	protected void setAcoesGerente(){}
	protected void setAcoesAtendente(){}
	protected void setAcoesAuxiliar(){}

}
