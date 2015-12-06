package UIModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import domainModel.Garcom;
import domainModel.Setor;

/**
 * Classe Action IniciaTurnoAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class IniciaTurnoAction extends UIAction
{

	/**
	 * M�todo de execu��o da action para iniciar turno.
	 */
	@Override public void execute ()
	{
		if (!operationService.verificaMesasLiberadas ())
		{
			System.out.println ("Todas mesas do restaurante n�o est�o liberadas. Verifique-as novamente.");
			return;
		}
		else
		{
			ArrayList< Garcom > listaGarcons = operationService.getGarcons ();
			ArrayList< Setor > listaSetores = operationService.getSetores ();
			HashMap< Garcom, Setor > disposicao = new HashMap< Garcom, Setor > ();

			System.out.println ("Favor selecionar Gar�om para cada Setor:");
			for (Iterator< Setor > iterator = listaSetores.iterator (); iterator.hasNext ();)
			{
				Setor setor = (Setor) iterator.next ();
				System.out.println ("\nSetor " + setor.getNome () + ":\n");
				for (Iterator< Garcom > iterator2 = listaGarcons.iterator (); iterator2.hasNext ();)
				{
					Garcom garcom = (Garcom) iterator2.next ();
					System.out.println ("\tGar�om : " + garcom.getID () + ".");
				}

				System.out.print ("\nInsira o c�digo do Gar�om para este Setor: ");
				String codGarcom = someInput.next ();
				for (Iterator< Garcom > iterator2 = listaGarcons.iterator (); iterator2.hasNext ();)
				{
					Garcom garcom = (Garcom) iterator2.next ();
					if (garcom.getID ().equalsIgnoreCase (codGarcom))
					{
						disposicao.put (garcom, setor);
						break;
					}
					else
						continue;
				}
			}

			operationService.criaTurnoAtivo ();
			operationService.setGarconsSetor (disposicao);
			System.out.println ("Turno iniciado com sucesso!:");
		}
	}
}
