package ArchitectureModel;

import java.util.ArrayList;
import java.util.HashMap;

import domainModel.Atendente;
import domainModel.AuxiliarCozinha;
import domainModel.Cozinheiro;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Gerente;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Setor;
import domainModel.Turno;

/**
 * A base de dados guarda todas as informações históricas do restaurante, além
 * da despensa atual e outras informações estáveis do restaurante, como lista de
 * funcionários, cardápio e mesas.
 * 
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.1
 * 
 * MODIF: Uma Base de Dados só pode em teoria ter uma instância. Para garantir
 * que isso aconteça e que haja um modo de acessar a classe globalmente, foi
 *  implentada seguindo o padrão de design chamado "Singleton".
 */
public class Database
{
	private ArrayList< Turno > listaTurnos;
	private ArrayList< Funcionario > listaFuncionarios;
	private ArrayList< Item > cardapio;
	private ArrayList< Setor > listaSetores;
	private ArrayList< Mesa > listaMesas; // NEW
	private HashMap< Ingrediente, Double > despensa;

	/**
	 * Cria atributo estático que instancia objeto Database.
	 * 
	 * @author thnitschke
	 * @category Objeto Database estático.
	 */
	private static Database instanciaUnica = null;

	/**
	 * Inicializa-se a Database, pergutando
	 * Faz-se o construtor privado para que a classe não possa ser instanciada
	 * fora dela mesma.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @category Objeto Database estático.
	 */
	private Database ()
	{
		listaFuncionarios = new ArrayList<>();
		listaSetores = new ArrayList<>();
		loadRestaurantData();
	}
	
	
	
	/**
	 * Se for a primeira chamada a este método, cria a única instância de 
	 * Database chamando o seu Construtor e armazenando em instanciaUnica
	 * Sempre retorna o único objeto Database existente.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @return Único objeto Database.
	 */
	public static Database getInstanciaUnica ()
	{
		if (instanciaUnica == null)
		{
			instanciaUnica = new Database ();
		}
		return instanciaUnica;
	}

	public boolean hasTurnoAtivo ()
	{
		return false;
	}

	public Turno getTurnoAtivo ()
	{
		return null;
	}

	/**
	 * Pesquisa se existe algum funcionário com o id recebido por parâmetro.
	 * Caso positivo ele retorna o funcionario, caso contrario retorna null.
	 * 
	 * @param id
	 *                Recebe um id do tipo String
	 * @return Retorna o funcionario caso encontrar, e null caso contrário.
	 */
	public Funcionario getFuncionario (String id)
	{
		for (int funcionario = 0; funcionario < listaFuncionarios
				.size (); funcionario++)
		{
			Funcionario check = listaFuncionarios.get (funcionario);
			if (id.equals (check.getID ()))
			{
				return check;
			}
		}
		return null;
	}

	/**
	 * Retorna a lista de mesas de um determinado setor dado por parâmetro.
	 * 
	 * @param setor
	 *                Recebe um setor.
	 * @return Retorna a lista de mesas deste setor.
	 */
	public ArrayList< Mesa > getMesas (Setor setor)
	{
		return setor.getMesas ();
	}

	/**
	 * Retorna a lista de mesas de todo o restaurante.
	 * 
	 * @return Retorna a lista de mesas.
	 */
	public ArrayList< Mesa > getMesas ()
	{
		return listaMesas;
	}

	public void atualizaDespensa (
			HashMap< Ingrediente, Double > itensARemover)
	{

	}

	/**
	 * Retorna a lista de cardápio do restaurante.
	 * 
	 * @return Retorna esta lista.
	 */
	public ArrayList< Item > getCardapio ()
	{
		return cardapio;
	}

	/**
	 * Retorna a lista de setores existentes.
	 * 
	 * @return Retorna a lista.
	 */
	public ArrayList< Setor > getSetores ()
	{
		return listaSetores;
	}

	/**
	 * Retorna a lista de turnos do restaurante.
	 * 
	 * @return Retorna esta lista.
	 */
	public ArrayList< Turno > getTurno ()
	{
		return listaTurnos;
	}

	public void armazenaTurnoAtivo (Turno turno)
	{
	}

	public ArrayList< Ingrediente > getFaltaEstoque ()
	{
		return null;
	}
	
	
	/**
	 * INICIALIZA OS DADOS DO RESTAURANTE TODO.
	 */
	public void loadRestaurantData(){
		
		//INICIALIZANDO FUNCIONARIOS DO RESTAURANTE
		Funcionario gr1 = new Gerente("GR000");
		listaFuncionarios.add(gr1);
		Funcionario at1 = new Atendente("AT00");
		listaFuncionarios.add(at1);
		Funcionario aa1 = new AuxiliarCozinha("AC000");
		listaFuncionarios.add(aa1);
		Funcionario co1 = new Cozinheiro("C000");
		listaFuncionarios.add(co1);
		Funcionario g1 = new Garcom("G000");
		listaFuncionarios.add(g1);
		Funcionario g2 = new Garcom("G001");
		listaFuncionarios.add(g2);
		Funcionario g3 = new Garcom("G002");
		listaFuncionarios.add(g3);
		
		
		//INCIALIZANDO SETORES DO RESTAURANTE
		Setor se1 = new Setor("Azul");
		listaSetores.add(se1);
		Setor se2 = new Setor("Amarelo");
		listaSetores.add(se2);
		Setor se3 = new Setor("Vermelho");
		listaSetores.add(se3);
		Setor se4 = new Setor("Verde");
		listaSetores.add(se4);
		Setor se5 = new Setor("Rosa");
		listaSetores.add(se5);
		
		
		//INICIALIZANDO MESAS E ADICIONANDO CADA MESA 
		//NA LISTA DE SETOR CORRESPONDENTE
		Mesa m1s1 = new Mesa(se1, 5);
		se1.getMesas().add(m1s1);
		Mesa m2s1 = new Mesa(se1, 4);
		se1.getMesas().add(m2s1);
		Mesa m3s1 = new Mesa(se1, 4);
		se1.getMesas().add(m3s1);
		Mesa m4s1 = new Mesa(se1, 2);
		se1.getMesas().add(m4s1);
		Mesa m5s1 = new Mesa(se1, 2);
		se1.getMesas().add(m5s1);
		Mesa m6s1 = new Mesa(se1, 5);
		se1.getMesas().add(m6s1);
		
		
		Mesa m1s2 = new Mesa(se2, 3);
		se2.getMesas().add(m1s2);
		Mesa m2s2 = new Mesa(se2, 2);
		se2.getMesas().add(m2s2);
		Mesa m3s2 = new Mesa(se2, 2);
		se2.getMesas().add(m3s2);
		Mesa m4s2 = new Mesa(se2, 2);
		se2.getMesas().add(m4s2);

		
		Mesa m1s3 = new Mesa(se3, 8);
		se3.getMesas().add(m1s3);
		Mesa m2s3 = new Mesa(se3, 4);
		se3.getMesas().add(m2s3);
		Mesa m3s3 = new Mesa(se3, 4);
		se3.getMesas().add(m3s3);
		Mesa m4s3 = new Mesa(se3, 4);
		se3.getMesas().add(m4s3);
		Mesa m5s3 = new Mesa(se3, 4);
		se3.getMesas().add(m5s3);
		Mesa m6s3 = new Mesa(se3, 6);
		se3.getMesas().add(m6s3);
		Mesa m7s3 = new Mesa(se3, 6);
		se3.getMesas().add(m7s3);
		Mesa m8s3 = new Mesa(se3, 2);
		se3.getMesas().add(m8s3);
		Mesa m9s3 = new Mesa(se3, 2);
		se3.getMesas().add(m9s3);

		
		Mesa m1s4 = new Mesa(se4, 10);
		se4.getMesas().add(m1s4);
		Mesa m2s4 = new Mesa(se4, 3);
		se4.getMesas().add(m2s4);
		Mesa m3s4 = new Mesa(se4, 3);
		se4.getMesas().add(m3s4);
		Mesa m4s4 = new Mesa(se4, 3);
		se4.getMesas().add(m4s4);
		Mesa m5s4 = new Mesa(se4, 4);
		se4.getMesas().add(m5s4);
		Mesa m6s4 = new Mesa(se4, 4);
		se4.getMesas().add(m6s4);
		Mesa m7s4 = new Mesa(se4, 5);
		se4.getMesas().add(m7s4);
		Mesa m8s4 = new Mesa(se4, 5);
		se4.getMesas().add(m8s4);
		Mesa m9s4 = new Mesa(se4, 5);
		se4.getMesas().add(m9s4);
		Mesa m10s4 = new Mesa(se4, 6);
		se4.getMesas().add(m10s4);
		Mesa m11s4 = new Mesa(se4, 6);
		se4.getMesas().add(m11s4);
		
		Mesa m1s5 = new Mesa(se5, 5);
		se5.getMesas().add(m1s5);
		Mesa m2s5 = new Mesa(se5, 3);
		se5.getMesas().add(m2s5);
		Mesa m3s5 = new Mesa(se5, 3);
		se5.getMesas().add(m3s5);
		Mesa m4s5 = new Mesa(se5, 3);
		se5.getMesas().add(m4s5);
		Mesa m5s5 = new Mesa(se5, 3);
		se5.getMesas().add(m5s5);
		Mesa m6s5 = new Mesa(se5, 3);
		se5.getMesas().add(m6s5);

	}
}
