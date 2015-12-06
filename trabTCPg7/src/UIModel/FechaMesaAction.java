package UIModel;

import java.util.ArrayList;
import java.util.Iterator;

import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Mesa;
import domainModel.SemTurnoAtivoException;

/**
 * Classe Action FecharMesaAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class FechaMesaAction extends UIAction
{

	/**
	 * Método de execução da action de fechar uma mesa.
	 */
	@Override public void execute ()
	{
		Funcionario funcionario = interf.getFuncionario ();
		ArrayList< Mesa > mesasAbertas = operationService.getMesasAbertas ((Garcom) funcionario);
		if (mesasAbertas.isEmpty()){
			System.out.println("\n>>--- Não existem mesas abertas. ---<< \n");
		} else{
		
			System.out.println ("Lista de mesas abertas, favor escolher mesa e inserir seu código abaixo:");
			for (Iterator< Mesa > iterator = mesasAbertas.iterator (); iterator.hasNext ();)
			{
				Mesa mesa = (Mesa) iterator.next ();
				System.out.println ("\t- Mesa: " + mesa.getCodigoMesa ());
			}

			Mesa mesaParaFechar = null;
			while (true)
			{
				String codMesa = someInput.next ();
				for (Iterator< Mesa > iterator = mesasAbertas.iterator (); iterator.hasNext ();)
				{
					Mesa mesa = (Mesa) iterator.next ();
					if (mesa.getCodigoMesa ().equalsIgnoreCase (codMesa))
						mesaParaFechar = mesa;
					else
						continue;
				}
				if (mesaParaFechar != null)
					break;
				else
				{
				System.out.println ("Nenhuma mesa existente com este código de mesa. Favor inserir novamente.");
				continue;
				}
			}

			double preco = 0;
			try
			{
				preco = operationService.getPrecoPedido (mesaParaFechar);
				System.out.println ("Preço total da conta: " + Double.toString (preco) + "\n\tCliente aceita (S/N)?");
				boolean aceitaPreco = someInput.next ().equalsIgnoreCase ("N") ? false : true;
				operationService.fechaMesa (mesaParaFechar, aceitaPreco);
			}
			catch (SemTurnoAtivoException excecao)
			{
				System.out.println (excecao.getMessage ());
			}
		}
	}
}
