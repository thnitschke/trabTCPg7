package UIModel;

import java.util.ArrayList;
import java.util.Iterator;

import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Item;
import domainModel.Mesa;

/**
 * Classe Action AtendeMesaAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class AtendeMesaAction extends UIAction
{
	/**
	 * Método de execução da action de atender mesa.
	 */
	@Override public void execute ()
	{
		Funcionario funcionario = interf.getFuncionario ();
		ArrayList< Mesa > mesasParaAtendimento = operationService.getMesasParaAtendimento ((Garcom) funcionario);
		ArrayList< Item > itensSelecionados = new ArrayList< Item > ();
		
		if (mesasParaAtendimento == null)
		{
			return;
		}

		System.out.println ("Lista de mesas para atendimento, favor escolher mesa e inserir seu código abaixo:");
		for (Iterator< Mesa > iterator = mesasParaAtendimento.iterator (); iterator.hasNext ();)
		{
			Mesa mesa = (Mesa) iterator.next ();
			System.out.println ("\t- Mesa: " + mesa.getCodigoMesa ());
		}

		Mesa mesaParaAtender = null;
		while (true)
		{
			String codMesa = someInput.next ();
			for (Iterator< Mesa > iterator = mesasParaAtendimento.iterator (); iterator.hasNext ();)
			{
				Mesa mesa = (Mesa) iterator.next ();
				if (mesa.getCodigoMesa ().equalsIgnoreCase (codMesa))
					mesaParaAtender = mesa;
				else
					continue;
			}
			if (mesaParaAtender != null)
				break;
			else
			{
				System.out.println ("Nenhuma mesa existente com este código de mesa. Favor inserir novamente.");
				continue;
			}
		}

		System.out.println ("Cardápio:\n");
		Integer codItem = new Integer (1);
		for (Iterator< Item > iterator = operationService.getCardapio ().iterator (); iterator.hasNext (); codItem++)
		{
			Item item = (Item) iterator.next ();
			System.out.println ("\tCategoria:\n" + item.getCategoria () + "\n\t\t- CodItem " + codItem.toString () + " :\n" + item.getNome ());
		}

		System.out.println ("\tFavor selecionar itens por seus respectivos códigos abaixo (quando não houver mais itens a adicionar, digitar EXIT):");
		do
		{
			String input = someInput.next ();
			if (input.equalsIgnoreCase ("EXIT"))
				break;
			else
			{
				Integer index = new Integer (input);
				itensSelecionados.add (operationService.getCardapio ().get (--index));
			}
		}
		while (true);

		operationService.criaPedido ((Garcom) funcionario, mesaParaAtender, itensSelecionados);
	}
}
