package domainModel;

/**
 * Classe para encapsular salário fixo e porcetagem de participação dos lucros
 * de cada tipo de Funcionario. A criação e controle destas classes é feito pelo
 * Database durante a inicialização do sistema.
 * 
 * @author thnitschke
 * @version 1.0
 */
public class Salario
{
	private Double salario;
	private Double porcentagem;

	Salario (double salario, double porcentagem)
	{
		this.salario = new Double (salario);
		this.porcentagem = new Double (porcentagem);
	}

	Double getSalario ()
	{
		return salario;
	}

	Double getPorcentagem ()
	{
		return porcentagem;
	}
}
