package domainModel;

/**
 * Classe para encapsular sal�rio fixo e porcetagem de participa��o dos lucros
 * de cada tipo de Funcionario. A cria��o e controle destas classes � feito pelo
 * Database durante a inicializa��o do sistema.
 * 
 * @author thnitschke
 * @version 1.0
 */
public class Salario
{
	private Double salario;
	private Double porcentagem;

	public Salario (double salario, double porcentagem)
	{
		this.salario = new Double (salario);
		this.porcentagem = new Double (porcentagem);
	}

	public Double getSalario ()
	{
		return salario;
	}

	public Double getPorcentagem ()
	{
		return porcentagem;
	}
}
