package ArchitectureModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import domainModel.Atendente;
import domainModel.AuxiliarCozinha;
import domainModel.Cozinheiro;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Gerente;
import domainModel.Ingrediente;
import domainModel.Item;
import domainModel.Mesa;
import domainModel.Receita;
import domainModel.Salario;
import domainModel.Setor;
import domainModel.Turno;
import domainModel.SemTurnoAtivoException;

/**
 * A base de dados guarda todas as informações históricas do restaurante, além
 * da despensa atual e outras informações estáveis do restaurante, como lista de
 * funcionários, cardápio e mesas.
 * 
 * @author Rodrigo Okido (trabTCPg7), thnitschke
 * @version 1.1 MODIF: Uma Base de Dados só pode em teoria ter uma instância.
 *          Para garantir que isso aconteça e que haja um modo de acessar a
 *          classe globalmente, foi implentada seguindo o padrão de design
 *          chamado "Singleton".
 */
public class Database
{
	private ArrayList< Turno > listaTurnos;
	private Turno turnoAtivo;
	private ArrayList< Funcionario > listaFuncionarios;
	private ArrayList< Item > cardapio;
	private ArrayList< Setor > listaSetores;
	private HashMap< Ingrediente, Double > despensa;
	private HashMap< String, Salario > salarios;

	/**
	 * Cria atributo estático que instancia objeto Database.
	 * 
	 * @author thnitschke
	 * @category Objeto Database estático.
	 */
	private static Database instanciaUnica = null;

	/**
	 * Inicializa-se a Database, pergutando Faz-se o construtor privado para
	 * que a classe não possa ser instanciada fora dela mesma.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @category Objeto Database estático.
	 */
	private Database ()
	{
		listaTurnos = new ArrayList< Turno > ();
		turnoAtivo = null;
		listaFuncionarios = new ArrayList< Funcionario > ();
		listaSetores = new ArrayList< Setor > ();
		loadRestaurantData ();
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
		if (turnoAtivo != null)
			return true;
		else
			return false;
	}

	public Turno getTurnoAtivo () throws SemTurnoAtivoException
	{
		if (turnoAtivo != null)
			return turnoAtivo;
		else
			throw new SemTurnoAtivoException ("Não há turno ativo!");
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
		for (int funcionario = 0; funcionario < listaFuncionarios.size (); funcionario++)
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
		ArrayList< Mesa > todasMesas = new ArrayList< > ();

		for (int setor = 0; setor < listaSetores.size (); setor++)
		{
			Setor s = listaSetores.get (setor);
			ArrayList< Mesa > mesasDoSetor = s.getMesas ();
			todasMesas.addAll (mesasDoSetor);
		}

		return todasMesas;
	}

	/**
	 * (NEW) Atualiza despensa, retirando a quantidade exata de Ingredientes
	 * da receita passada como parâmetro.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @param receita
	 */
	public void atualizaDespensa (Receita receita)
	{
		HashMap< Ingrediente, Double > ingredientes = receita.getIngredientes ();

		for (Iterator< Ingrediente > iterator = ingredientes.keySet ().iterator (); iterator.hasNext ();)
		{
			Ingrediente ingrediente = (Ingrediente) iterator.next ();
			Double quantidade = despensa.get (ingrediente);
			Double retirar = ingredientes.get (ingrediente);
			despensa.put (ingrediente, quantidade - retirar);
		}
	}

	/**
	 * (NEW) Consulta a despensa, verifica se há o suficiente de
	 * ingredientes para realizar-se a receita, se houver, retorna true;
	 * senão retorna false, e zera-se a quantidade de ingredientes daquele
	 * tipo em estoque.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @param receita
	 */
	public boolean existeSuficienteParaReceita (Receita receita)
	{
		boolean ehSuficiente = true;
		HashMap< Ingrediente, Double > ingredientes = receita.getIngredientes ();

		for (Iterator< Ingrediente > iterator = ingredientes.keySet ().iterator (); iterator.hasNext ();)
		{
			Ingrediente ingrediente = (Ingrediente) iterator.next ();
			Double quantidade = despensa.get (ingrediente);
			Double retirar = ingredientes.get (ingrediente);
			if (quantidade >= retirar)
				continue;
			else
			{
				/**
				 * Decisão de desenvolvedor: Se não há o
				 * suficiente para se cozinhar algo, zera-se a
				 * quantidade em estoque, para ser possível
				 * consultar ingredientes em falta de modo
				 * simples posteriormente.
				 */
				despensa.put (ingrediente, Double.valueOf (0));
				ehSuficiente = false;
			}
		}

		return ehSuficiente;
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

	/**
	 * Armazena um turno encerrado dentro da lista de turnos.
	 * 
	 * @param turno
	 *                Recebe um turno encerrado.
	 */
	public void armazenaTurnoAtivo (Turno turno)
	{
		listaTurnos.add (turno);
		turnoAtivo = null;
	}

	public ArrayList< Ingrediente > getFaltaEstoque ()
	{
		ArrayList< Ingrediente > ingredientesEmFalta = new ArrayList< Ingrediente > ();

		for (Iterator< Ingrediente > iterator = despensa.keySet ().iterator (); iterator.hasNext ();)
		{
			Ingrediente ingrediente = (Ingrediente) iterator.next ();
			if (getQuantidadeIngrediente (ingrediente) == 0)
				ingredientesEmFalta.add (ingrediente);
			else
				continue;
		}

		return ingredientesEmFalta;
	}

	/**
	 * (NEW) Retorna quantidade em double do Ingrediente passado como
	 * parâmetro.
	 * 
	 * @author thnitschke
	 * @version 1.0
	 * @param ingrediente
	 * @return quantidade do ingrediente em double
	 */
	public double getQuantidadeIngrediente (Ingrediente ingrediente)
	{
		return despensa.get (ingrediente).doubleValue ();
	}

	/**
	 * @author thnitschke
	 * @version 1.0
	 * @return Lista de Todos Funcionarios
	 */
	public ArrayList< Funcionario > getListaFuncionarios ()
	{
		return listaFuncionarios;
	}

	/**
	 * @author thnitschke
	 * @version 1.0
	 * @return Mapa de nome simples das classes de Funcionarios mapeados
	 *         para seus salários em Double.
	 */
	public HashMap< String, Salario > getSalarios ()
	{
		return salarios;
	}

	/**
	 * @author thnitschke
	 */
	public void criaTurnoAtivo ()
	{
		turnoAtivo = new Turno ();
	}

	/**
	 * INICIALIZA OS DADOS DO RESTAURANTE.
	 */
	public void loadRestaurantData ()
	{

		// INICIALIZANDO FUNCIONARIOS DO RESTAURANTE
		Funcionario gr1 = new Gerente ("GR000");
		listaFuncionarios.add (gr1);
		Funcionario at1 = new Atendente ("AT000");
		listaFuncionarios.add (at1);
		Funcionario aa1 = new AuxiliarCozinha ("AC000");
		listaFuncionarios.add (aa1);
		Funcionario co1 = new Cozinheiro ("C000");
		listaFuncionarios.add (co1);
		Funcionario g1 = new Garcom ("G000");
		listaFuncionarios.add (g1);
		Funcionario g2 = new Garcom ("G001");
		listaFuncionarios.add (g2);
		Funcionario g3 = new Garcom ("G002");
		listaFuncionarios.add (g3);

		// INCIALIZANDO SETORES DO RESTAURANTE
		Setor se1 = new Setor ("Azul");
		listaSetores.add (se1);
		Setor se2 = new Setor ("Amarelo");
		listaSetores.add (se2);
		Setor se3 = new Setor ("Vermelho");
		listaSetores.add (se3);
		Setor se4 = new Setor ("Verde");
		listaSetores.add (se4);
		Setor se5 = new Setor ("Rosa");
		listaSetores.add (se5);

		// INICIALIZANDO MESAS E ADICIONANDO CADA MESA
		// NA LISTA DE SETOR CORRESPONDENTE
		Mesa m1s1 = new Mesa (se1, 5, "S1M01");
		se1.addMesa (m1s1);
		Mesa m2s1 = new Mesa (se1, 4, "S1M02");
		se1.addMesa (m2s1);
		Mesa m3s1 = new Mesa (se1, 4, "S1M03");
		se1.addMesa (m3s1);
		Mesa m4s1 = new Mesa (se1, 2, "S1M04");
		se1.addMesa (m4s1);
		Mesa m5s1 = new Mesa (se1, 2, "S1M05");
		se1.addMesa (m5s1);
		Mesa m6s1 = new Mesa (se1, 5, "S1M06");
		se1.addMesa (m6s1);

		Mesa m1s2 = new Mesa (se2, 3, "S2M01");
		se2.addMesa (m1s2);
		Mesa m2s2 = new Mesa (se2, 2, "S2M02");
		se2.addMesa (m2s2);
		Mesa m3s2 = new Mesa (se2, 2, "S2M03");
		se2.addMesa (m3s2);
		Mesa m4s2 = new Mesa (se2, 2, "S2M04");
		se2.addMesa (m4s2);

		Mesa m1s3 = new Mesa (se3, 8, "S3M01");
		se3.addMesa (m1s3);
		Mesa m2s3 = new Mesa (se3, 4, "S3M02");
		se3.addMesa (m2s3);
		Mesa m3s3 = new Mesa (se3, 4, "S3M03");
		se3.addMesa (m3s3);
		Mesa m4s3 = new Mesa (se3, 4, "S3M04");
		se3.addMesa (m4s3);
		Mesa m5s3 = new Mesa (se3, 4, "S3M05");
		se3.addMesa (m5s3);
		Mesa m6s3 = new Mesa (se3, 6, "S3M06");
		se3.addMesa (m6s3);
		Mesa m7s3 = new Mesa (se3, 6, "S3M07");
		se3.addMesa (m7s3);
		Mesa m8s3 = new Mesa (se3, 2, "S3M08");
		se3.addMesa (m8s3);
		Mesa m9s3 = new Mesa (se3, 2, "S3M09");
		se3.addMesa (m9s3);

		Mesa m1s4 = new Mesa (se4, 10, "S4M01");
		se4.addMesa (m1s4);
		Mesa m2s4 = new Mesa (se4, 3, "S4M02");
		se4.addMesa (m2s4);
		Mesa m3s4 = new Mesa (se4, 3, "S4M03");
		se4.addMesa (m3s4);
		Mesa m4s4 = new Mesa (se4, 3, "S4M04");
		se4.addMesa (m4s4);
		Mesa m5s4 = new Mesa (se4, 4, "S4M05");
		se4.addMesa (m5s4);
		Mesa m6s4 = new Mesa (se4, 4, "S4M06");
		se4.addMesa (m6s4);
		Mesa m7s4 = new Mesa (se4, 5, "S4M07");
		se4.addMesa (m7s4);
		Mesa m8s4 = new Mesa (se4, 5, "S4M08");
		se4.addMesa (m8s4);
		Mesa m9s4 = new Mesa (se4, 5, "S4M09");
		se4.addMesa (m9s4);
		Mesa m10s4 = new Mesa (se4, 6, "S4M10");
		se4.addMesa (m10s4);
		Mesa m11s4 = new Mesa (se4, 6, "S4M11");
		se4.addMesa (m11s4);

		Mesa m1s5 = new Mesa (se5, 5, "S5M01");
		se5.addMesa (m1s5);
		Mesa m2s5 = new Mesa (se5, 3, "S5M02");
		se5.addMesa (m2s5);
		Mesa m3s5 = new Mesa (se5, 3, "S5M03");
		se5.addMesa (m3s5);
		Mesa m4s5 = new Mesa (se5, 3, "S5M04");
		se5.addMesa (m4s5);
		Mesa m5s5 = new Mesa (se5, 3, "S5M05");
		se5.addMesa (m5s5);
		Mesa m6s5 = new Mesa (se5, 3, "S5M06");
		se5.addMesa (m6s5);

	}
}
