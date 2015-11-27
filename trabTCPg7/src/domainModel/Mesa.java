package domainModel;

import java.sql.Time;

public class Mesa
{
	private Setor setor;
	private int capacidade;
	private boolean limpa;
	private boolean ocupada;
	private Time reserva;
	private Pedido pedido;
	private double gorjetaLimpeza;
	
	public Setor getSetor()
	{
		return setor;
	}
	public int getCapacidade()
	{
		return capacidade;
	}
	public boolean isLimpa ()
	{
		;
	}
	public boolean isOcupada ()
	{
		;
	}
	public void ocupa ()
	{
		;
	}
	public void desocupa ()
	{
		;
	}
	public void setReserva(Time reserva)
	{
		this.reserva = reserva;
	}
	public Time getReserva()
	{
		return reserva;
	}
	public boolean isDisponivel()
	{
		;
	}
	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
	}
	public Pedido getPedido()
	{
		return pedido;
	}
	public void setGorjetaLimpeza(double gorjetaLimpeza)
	{
		this.gorjetaLimpeza = gorjetaLimpeza;
	}
	public double getGorjetaLimpeza()
	{
		return gorjetaLimpeza;
	}
}
