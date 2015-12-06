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
		despensa = new HashMap<>();
		listaTurnos = new ArrayList< Turno > ();
		turnoAtivo = new Turno();
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

	
	/**
	 * Retorna true ou false verificando se tem turno ativo.
	 * 
	 * @return Retorna true se houver turno ativo, e false caso contrario.
	 */
	public boolean hasTurnoAtivo ()
	{
		if (turnoAtivo != null)
			return true;
		else
			return false;
	}

	
	/**
	 * Retorna o turno ativo.
	 * 
	 * @return Retorna este turno.
	 * @throws SemTurnoAtivoException Joga a exceção SemTurnoAtivoException caso não haja um turno ativo.
	 */
	public Turno getTurnoAtivo () throws SemTurnoAtivoException
	{
		if (turnoAtivo != null)
			return turnoAtivo;
		else
			throw new SemTurnoAtivoException ("\n >>--- Não há turno ativo! ---<< \n");
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

	
	/**
	 * Retorna a quantidade de Ingredientes que faltam no estoque.
	 * 
	 * @return Retorna a lista de ingredientes.
	 */
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
		Funcionario g4 = new Garcom ("G003");
		listaFuncionarios.add (g4);
		Funcionario g5 = new Garcom ("G004");
		listaFuncionarios.add (g5);
		Funcionario g6 = new Garcom ("G005");
		listaFuncionarios.add (g6);

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
		
		
		//INGREDIENTES 
		Ingrediente linguica = new Ingrediente ("Linguiça");
		Ingrediente cebola = new Ingrediente ("Cebola");
		Ingrediente pao = new Ingrediente ("Pão");
		Ingrediente jilo = new Ingrediente ("Jilo");
		Ingrediente queijoCanastra = new Ingrediente ("Queijo Canastra");
		Ingrediente camarao = new Ingrediente ("Camarao");
		Ingrediente folhasVerdes = new Ingrediente ("FolhasVerdes");
		Ingrediente queijoCamem = new Ingrediente ("Queijo Camembert");
		Ingrediente molhoFolhasmix = new Ingrediente ("Molho Folhas mix");
		Ingrediente amoras = new Ingrediente ("Amoras");
		Ingrediente file = new Ingrediente ("File Mignon");
		Ingrediente queijoGrana = new Ingrediente ("Queijo Grana Padano");
		Ingrediente crostine = new Ingrediente ("Crostine");
		Ingrediente molhoRucula = new Ingrediente ("Molho de Rucula");
		Ingrediente tomateItalinano = new Ingrediente ("Tomate Italiano");
		Ingrediente mozarelaBufala = new Ingrediente ("Mozarela de búfala");
		Ingrediente molhoManjericao = new Ingrediente ("Molho de Manjericao");
		Ingrediente molhoalfacemimosa = new Ingrediente ("Molho de alface mimosa");
		Ingrediente molhorucula = new Ingrediente ("Molho de rucula");
		Ingrediente nozes = new Ingrediente ("Nozes");
		Ingrediente figos = new Ingrediente ("Figos Secos");
		Ingrediente cebolaRoxa = new Ingrediente ("Confit de cebola roxa");
		Ingrediente queijoBrie = new Ingrediente ("Queijo Brie");
		Ingrediente misso = new Ingrediente ("Misso");
		Ingrediente pernilSuico = new Ingrediente ("Pernil Suico");
		Ingrediente champignon = new Ingrediente ("Champignon");
		Ingrediente cenoura = new Ingrediente ("Cenoura");
		Ingrediente molhoAcelga = new Ingrediente ("Molho Acelga");
		Ingrediente macarrao = new Ingrediente ("Macarrao");
		Ingrediente couveFlor = new Ingrediente ("Couve-Flor");
		Ingrediente batata = new Ingrediente ("Batata");
		Ingrediente ervilha = new Ingrediente ("Ervilha");
		Ingrediente vagem = new Ingrediente ("Vagem");
		Ingrediente cremeDeMesa = new Ingrediente ("Creme de mesa");
		Ingrediente caldoDeCenoura = new Ingrediente ("Caldo de cenoura");
		Ingrediente batataSalsa = new Ingrediente ("Batatas salsa");
		Ingrediente caldoDeCarne = new Ingrediente ("Caldo de carne");
		Ingrediente costelaDePorco = new Ingrediente ("Costela de Porco");
		Ingrediente arroz = new Ingrediente ("Arroz");
		Ingrediente feijao = new Ingrediente ("Feijao");
		Ingrediente mandioca = new Ingrediente ("Mandioca");
		Ingrediente molhoDeCouve = new Ingrediente ("Molho de Couve");
		Ingrediente lomboDePorco = new Ingrediente ("Lombo de Porco");
		Ingrediente farinhaDeMandioca = new Ingrediente ("Farinha de Mandioca");
		Ingrediente sobrecoxaDeFrango = new Ingrediente ("Sobrecoxa de Frango");
		Ingrediente cremeDeLeite = new Ingrediente ("Creme de Leite");
		Ingrediente milhoVerde = new Ingrediente ("Milho Verde");
		Ingrediente coxinhaDeAsa = new Ingrediente ("Coxinha de asa");
		Ingrediente fubaDeMilho = new Ingrediente ("Fubá de milho");
		Ingrediente porcaoDeQuiabo = new Ingrediente ("Porcao de quiabo");
		Ingrediente chuchu = new Ingrediente ("Chuchu");
		Ingrediente penne = new Ingrediente ("Penne");
		Ingrediente queijoGruyere = new Ingrediente ("Queijo gruyere");
		Ingrediente nata = new Ingrediente ("Nata");
		Ingrediente fileDeCostela = new Ingrediente ("File de costela");
		Ingrediente perdiz = new Ingrediente ("Perdiz");
		Ingrediente linguicaCalabresa = new Ingrediente ("Linguica Calabresa");
		Ingrediente cachaca = new Ingrediente ("Cachaça");
		Ingrediente manteigaDeFoieGras = new Ingrediente ("Manteiga de foie gras");
		Ingrediente fileDeRobalo = new Ingrediente ("Filé de robalo");
		Ingrediente batatasDoces = new Ingrediente ("Batatas doces");
		Ingrediente abobora = new Ingrediente ("Abóbora");
		Ingrediente coco = new Ingrediente ("Coco");
		Ingrediente acucar = new Ingrediente ("Açúcar");
		Ingrediente margarina = new Ingrediente ("Margarina");
		Ingrediente leiteCondensado = new Ingrediente ("Leite condensado");
		Ingrediente biscoitoMaizena = new Ingrediente ("Biscoito maizena");
		Ingrediente chocolate = new Ingrediente ("Chocolate");
		Ingrediente farinhaDeTrigo = new Ingrediente ("Farinha de trigo");
		Ingrediente sorveteDeCreme = new Ingrediente ("Sorvete de creme");
		Ingrediente biscoitoChampagne = new Ingrediente ("Biscoito champagne");
		Ingrediente cafe = new Ingrediente ("Café");
		Ingrediente queijoMascarpone = new Ingrediente ("Queijo mascarpone");
		Ingrediente cacau = new Ingrediente ("Cacau");
		Ingrediente chocolateAmargo = new Ingrediente ("Chocolate amargo");
		Ingrediente castanha = new Ingrediente ("Castanha");
		Ingrediente leite = new Ingrediente ("Leite");
		Ingrediente limoes = new Ingrediente ("Limões");
		Ingrediente abacaxi = new Ingrediente ("Abacaxi");
		Ingrediente gengibre = new Ingrediente ("Gengibre");
		
		Ingrediente aguaGas = new Ingrediente ("Agua com gas");
		Ingrediente aguaTonica = new Ingrediente ("Agua Tonica");
		Ingrediente cerveja = new Ingrediente ("Cerveja");
		Ingrediente refrigerante = new Ingrediente ("Refrigerante");
		Ingrediente vinhoTinto = new Ingrediente ("Vinho Tinto");
		Ingrediente vinhoBranco = new Ingrediente ("Vinho Branco");
		Ingrediente espumante = new Ingrediente ("Espumante");

		despensa.put(linguica, (double) 50);
		despensa.put(cebola, (double) 50);
		despensa.put(pao, (double) 50);
		despensa.put(jilo, (double) 50);
		despensa.put(queijoCanastra, (double) 50);
		despensa.put(camarao, (double) 50);
		despensa.put(folhasVerdes, (double) 50);
		despensa.put(queijoCamem, (double) 50);
		despensa.put(molhoFolhasmix, (double) 50);
		despensa.put(amoras, (double) 50);
		despensa.put(file, (double) 50);
		despensa.put(queijoGrana, (double) 50);
		despensa.put(crostine, (double) 50);
		despensa.put(molhoRucula, (double) 50);
		despensa.put(tomateItalinano, (double) 50);
		despensa.put(mozarelaBufala, (double) 50);
		despensa.put(molhoManjericao, (double) 0);
		despensa.put(molhoalfacemimosa, (double) 50);
		despensa.put(molhorucula, (double) 50);
		despensa.put(nozes, (double) 50);
		despensa.put(figos, (double) 50);
		despensa.put(cebolaRoxa, (double) 50);
		despensa.put(queijoBrie, (double) 50);
		despensa.put(misso, (double) 50);
		despensa.put(pernilSuico, (double) 50);
		despensa.put(champignon, (double) 50);
		despensa.put(cenoura, (double) 50);
		despensa.put(molhoAcelga, (double) 50);
		despensa.put(macarrao, (double) 50);
		despensa.put(couveFlor, (double) 50);
		despensa.put(batata, (double) 50);
		despensa.put(ervilha, (double) 50);
		despensa.put(vagem, (double) 50);
		despensa.put(cremeDeMesa, (double) 50);
		despensa.put(caldoDeCenoura, (double) 50);
		despensa.put(batataSalsa, (double) 50);
		despensa.put(caldoDeCarne, (double) 50);
		despensa.put(costelaDePorco, (double) 0);
		despensa.put(arroz, (double) 50);
		despensa.put(feijao, (double) 50);
		despensa.put(mandioca, (double) 50);
		despensa.put(molhoDeCouve, (double) 50);
		despensa.put(lomboDePorco , (double) 50);
		despensa.put(farinhaDeMandioca , (double) 50);
		despensa.put(sobrecoxaDeFrango , (double) 50);
		despensa.put(cremeDeLeite , (double) 50);
		despensa.put(milhoVerde , (double) 50);
		despensa.put(coxinhaDeAsa , (double) 50);
		despensa.put(fubaDeMilho , (double) 50);
		despensa.put(porcaoDeQuiabo , (double) 50);
		despensa.put(chuchu , (double) 50);
		despensa.put(penne , (double) 50);
		despensa.put(queijoGruyere , (double) 50);
		despensa.put(nata, (double) 50);
		despensa.put(fileDeCostela , (double) 50);
		despensa.put(perdiz , (double) 50);
		despensa.put(linguicaCalabresa , (double) 0);
		despensa.put(cachaca , (double) 50);
		despensa.put(manteigaDeFoieGras , (double) 50);
		despensa.put(fileDeRobalo , (double) 50);
		despensa.put(batatasDoces , (double) 50);
		despensa.put(abobora , (double) 50);
		despensa.put(coco , (double) 50);
		despensa.put(acucar , (double) 50);
		despensa.put(margarina , (double) 50);
		despensa.put(leiteCondensado , (double) 50);
		despensa.put(biscoitoMaizena , (double) 50);
		despensa.put(chocolate , (double) 50);
		despensa.put(farinhaDeTrigo , (double) 50);
		despensa.put(sorveteDeCreme , (double) 50);
		despensa.put(biscoitoChampagne , (double) 50);
		despensa.put(cafe , (double) 50);
		despensa.put(queijoMascarpone , (double) 50);
		despensa.put(cacau , (double) 0);
		despensa.put(chocolateAmargo , (double) 50);
		despensa.put(castanha , (double) 50);
		despensa.put(leite , (double) 50);
		despensa.put(limoes , (double) 50);
		despensa.put(abacaxi , (double) 50);
		despensa.put(gengibre , (double) 50);
		despensa.put(aguaGas , (double) 50);
		despensa.put(aguaTonica , (double) 50);
		despensa.put(cerveja , (double) 50);
		despensa.put(refrigerante , (double) 50);
		despensa.put(vinhoTinto , (double) 50);
		despensa.put(vinhoBranco , (double) 50);
		despensa.put(espumante , (double) 0);

		Item item1 = new Item ("Linguica com Cebola", 17, "Entrada", 13, 10,(new Receita(despensa, "1 cebola, 2 linguiças, 2 pães" )));
		Item item2 = new Item ("Jiló com queijo", 12, "Entrada", 9, 12,(new Receita(despensa, "5 jilós, 50g de queijo canastra, 2 pães" )));
		Item item3 = new Item ("Camarões Médios de Cativeiro", 26, "Entrada", 20, 13,(new Receita(despensa, "6 camarões médios, 1 molho de folhas verdes" )));
		Item item4 = new Item ("Camembert Empanado, Mix de Folhas e Coulie de Amora", 20, "Entrada", 15, 10,(new Receita(despensa, "50g de queijo camembert, 1 molho de folhas mix, 50g de amoras" )));
		Item item5 = new Item ("Carpaccio de File Mignon com Azeite e Grana Padano", 32.50, "Entrada", 25, 8,(new Receita(despensa, "100g de filé mignon, 30g de queijo grana padano, 100g de crostine, 1 molho de rúcula " )));
		
		Item item6 = new Item ("Salada Caprese", 20, "Saladas", 15, 9,(new Receita(despensa, "1 tomate italiano, 2 mozarela de búfala, 1 molho de manjericão, 30g de queijo grana padano, 2 molhos de folhas mix" )));
		Item item7 = new Item ("Salada de Folhas, Queijos e Figos Secos", 17, "Saladas", 13, 8,(new Receita(despensa, "1 molho de alface mimosa, 1 molho de rúcula, 30g de queijo grana padano, 10g de nozes, 20g de figos secos, 1 mozarela de búfala" )));
		Item item8 = new Item ("Salada de Rúculas Novas", 13, "Saladas", 10, 8,(new Receita(despensa, "2 molhos de rúcula, 1 porção de confit de cebola roxa, 30g de queijo brie, 10g de nozes " )));
	
		Item item9 = new Item ("Chinesa ", 13, "Sopas", 10, 15,(new Receita(despensa, "100g de missô, 100g de pernil suíno, 50g de champignon, 1 cenoura, 1 molho de acelga, 100g de macarrão" )));
		Item item10 = new Item ("Finlandesa ", 11, "Sopas", 8, 11,(new Receita(despensa, "1 porção de couve-flor, 1 batata, 1 cenoura, 50g de ervilha, 100g de vagem, 100ml de creme de mesa" )));
		Item item11 = new Item ("Sopa de cenoura", 6.5, "Sopas", 5, 5,(new Receita(despensa, "250ml de caldo de cenoura " )));
		Item item12 = new Item ("Sopa de Batata Salsa ", 10, "Sopas", 7, 10,(new Receita(despensa, "2 batatas salsa, 250ml de caldo de carne" )));
	
		Item item13 = new Item ("Costelinha da Sinhá", 71.50, "Pratos Principais", 55, 20,(new Receita(despensa, "200g de costela de porco, 100g de arroz, 100g de feijão, 1 mandioca, 1 molho de couve" )));
		Item item14 = new Item ("Mexidão Metido a Besta", 78, "Pratos Principais", 60, 13,(new Receita(despensa, "150g de arroz, 150g de feijão, 200g de file mignon" )));
		Item item15 = new Item ("Lombo Assado da Panela", 58.5, "Pratos Principais", 45, 20,(new Receita(despensa, "150g de arroz, 150g de feijão, 50g de farinha de mandioca, 1 molho de couve, 2 batatas" )));
		Item item16 = new Item ("Frango Jeca", 52, "Pratos Principais", 40, 17,(new Receita(despensa, "2 sobrecoxas de frango, 100ml de creme de leite, 100g de milho verde" )));
		Item item17 = new Item ("Frango com Quiabo", 65, "Pratos Principais", 50, 14,(new Receita(despensa, "2 sobrecoxas de frango, 2 coxinhas de asa, 150g de arroz, 150g de feijão, 100g de fubá de milho, 1 porção de quiabo, 1 molho de couve, 1 chuchu" )));
		Item item18 = new Item ("Filé Grelhado com Penne Rigatte e Molho de queijo", 63, "Pratos Principais", 48, 10,(new Receita(despensa, "150g de filé mignon, 200g de penne, 100g de queijo gruyere, 100ml de nata, 50g de queijo grana padano" )));
		Item item19 = new Item ("Entrecot Premium com Batatas Perfumadas", 75.5, "Pratos Principais", 58, 12,(new Receita(despensa, "350g de filé da costela, 3 batatas" )));
		Item item20 = new Item ("Carreteiro de Perdiz com Arroz Negro e Manteiga de Foie Gras", 91, "Pratos Principais", 70, 17,(new Receita(despensa, "150g de arroz negro, 1 perdiz, 1 linguiça calabresa, 100ml de cachaça, 50g de manteiga de foie gras " )));
		Item item21 = new Item ("Robalo Oliva e Ervas", 78, "Pratos Principais", 60, 15,(new Receita(despensa, "1 filé de robalo, 2 cenouras, 3 batatas doces" )));
		
		
		Item item22 = new Item ("Doce de abóbora com coco", 10.5, "Sobremesas ", 8, 6,(new Receita(despensa, "1 abóbora, 100g de coco, 50g de açúcar" )));
		Item item23 = new Item ("Torta da Tia Vilica", 65, "Sobremesas ", 15, 10,(new Receita(despensa, "50g de margarina, 80g de açúcar, 100ml de creme de leite, 20 ml de leite condensado, 100g de biscoito maizena, 50g de chocolate" )));
		Item item24 = new Item ("Petit Gateau de Chocolate", 13, "Sobremesas ", 10, 7,(new Receita(despensa, "100g de chocolate, 50g de farinha de trigo, 100ml de sorvete de creme" )));
		Item item25 = new Item ("Torta Tiramissu", 16, "Sobremesas ", 12, 8,(new Receita(despensa, "200g de biscoito champagne, 100ml de café, 100g de queijo mascarpone, 50g de cacau" )));
		Item item26 = new Item ("Sorvete de Creme com Calda de Chocolate", 11, "Sobremesas ", 8, 2,(new Receita(despensa, "100ml de sorvete de creme, 50g de chocolateo amargo, 30g de castanha" )));
		
		
		Item item27 = new Item ("Limonada refrescante", 9, "Bebidas", 13, 0,(new Receita(despensa, "3 limões, 50ml de leite" )));
		Item item28 = new Item ("Suco de abacaxi com gengibre" , 8, "Bebidas", 13, 0,(new Receita(despensa, "1 abacaxi, 30g de gengibre" )));
		Item item29 = new Item ("Água com gás ", 3.50, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item30 = new Item ("Água tônica", 5, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item31 = new Item ("Cerveja", 7, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item32 = new Item ("Refrigerante ", 5, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item33 = new Item ("Vinho tinto", 10, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item34 = new Item ("Vinho branco", 10, "Bebidas", 13, 0,(new Receita(despensa, "" )));
		Item item35 = new Item ("Espumante ", 15, "Bebidas", 13, 0,(new Receita(despensa, "" )));
	}
}
