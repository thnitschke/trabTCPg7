package domainModel;

/**
 * Guarda todas as informa��es relacionadas a uma mesa do restaurante. Al�m das
 * informa��es permanentes, como capacidade e setor, tamb�m armazena informa��es
 * de estado da mesa, como se est� ocupada, limpa ou se possui uma reserva
 * feita.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class Mesa
{

	private String codigoMesa; /// NEW
	private Setor setor;
	private int capacidade;
	private boolean limpa;
	private boolean ocupada;
	// private Date reserva;
	private String reserva;
	private Pedido pedido;
	private double gorjetaLimpeza;

	/**
	 * Construtor de um objeto Mesa.
	 * 
	 * @param setor
	 *                Informa o setor da mesa.
	 * @param capacidade
	 *                Informa a capacidade da mesa.
	 */
	public Mesa (Setor setor, int capacidade, String cod)
	{
		this.setor = setor;
		this.capacidade = capacidade;
		codigoMesa = cod;
		limpa = true;
		ocupada = false;
		reserva = null;
		pedido = null;
		gorjetaLimpeza = 0;
	}

	/**
	 * Retorna o setor da mesa.
	 * 
	 * @return Retorna setor.
	 */
	public Setor getSetor ()
	{
		return setor;
	}

	/**
	 * Retorna a capacidade de uma mesa.
	 * 
	 * @return Retorna a capacidade.
	 */
	public int getCapacidade ()
	{
		return capacidade;
	}

	/**
	 * Retorna o pedido de um objeto mesa.
	 * 
	 * @return Retorna o pedido.
	 */
	public Pedido getPedido ()
	{
		return pedido;
	}

	/**
	 * Retorna o atributo gorjetaLimpeza.
	 * 
	 * @return Retorna o atributo gorjetaLimpeza.
	 */
	public double getGorjetaLimpeza ()
	{
		return gorjetaLimpeza;
	}

	/**
	 * Retorna a reserva da mesa.
	 * 
	 * @return Retorna a reserva.
	 */
	public String getReserva ()
	{
		return reserva;
	}

	/**
	 * Associa um Pedido a mesa.
	 * 
	 * @param pedido
	 *                Recebe um pedido do tipo Pedido.
	 */
	public void setPedido (Pedido pedido)
	{
		this.pedido = pedido;
	}

	/**
	 * Atribui um valor para o atributo gorjetaLimpeza.
	 * 
	 * @param gorjetaLimpeza
	 *                Recebe um valor double para associar ao atributo
	 *                gorjetaLimpeza.
	 */
	public void setGorjetaLimpeza (double gorjetaLimpeza)
	{
		this.gorjetaLimpeza = gorjetaLimpeza;
	}

	/**
	 * Associa uma reserva a uma mesa.
	 * 
	 * @param reserva
	 *                Recebe um valor de tipo Time.
	 */
	public void setReserva (String reserva)
	{
		this.reserva = reserva;
	}

	/**
	 * Verifica se uma mesa est� limpa.
	 * 
	 * @return Retorna true se a mesa estiver limpa, caso contr�rio
	 *         retornar� false.
	 */
	public boolean isLimpa ()
	{
		if (limpa == true)
		{
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a mesa est� ocupada ou n�o.
	 * 
	 * @return Retorna true caso a mesa estiver ocupada, caso contr�rio
	 *         retornar� false.
	 */
	public boolean isOcupada ()
	{
		if (ocupada == true)
		{
			return true;
		}
		return false;
	}

	/**
	 * Ocupa uma mesa.
	 */
	public void ocupa ()
	{
		ocupada = true;
		limpa = true;

	}

	/**
	 * Desocupa uma mesa.
	 */
	public void desocupa ()
	{
		ocupada = false;
		limpa = false;
		pedido = null;
	}

	/**
	 * Verifica se uma mesa est� dispon�vel.
	 * 
	 * @return Retorna false se a mesa estiver indispon�vel, e true caso
	 *         contr�rio.
	 */
	public boolean isDisponivel ()
	{
		if ( (ocupada == false) && (limpa == true) && (reserva == null))
		{
			return true;
		}
		return false;
	}

	/**
	 * Retorna o c�digo da mesa.
	 * 
	 * @return Retorna o c�digo.
	 */
	public String getCodigoMesa ()
	{
		return codigoMesa;
	}

	/**
	 * Modifica o c�digo de uma mesa.
	 * 
	 * @param codigoMesa
	 *                Recebe um c�digo para modificar.
	 */
	public void setCodigoMesa (String codigoMesa)
	{
		this.codigoMesa = codigoMesa;
	}

}
