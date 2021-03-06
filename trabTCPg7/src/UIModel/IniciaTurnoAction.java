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
		operationService.criaTurnoAtivo ();

		if (!operationService.verificaMesasLiberadas ())
		{
			System.out.println ("\n >>--- Todas mesas do restaurante n�o est�o liberadas. Verifique-as novamente. ---<< \n");
			return;
		}
		else
		{
			ArrayList< Garcom > listaGarcons = operationService.getGarcons ();
			ArrayList< Setor > listaSetores = operationService.getSetores ();
			HashMap< Garcom, Setor > disposicao = new HashMap< Garcom, Setor > ();

			System.out.println (">> Favor selecionar Gar�om para cada Setor: ");
			for (Iterator< Setor > iterator = listaSetores.iterator (); iterator.hasNext ();)
			{
				Setor setor = (Setor) iterator.next ();
				System.out.println (">> Setor " + setor.getNome () + ":");
				for (Iterator< Garcom > iterator2 = listaGarcons.iterator (); iterator2.hasNext ();)
				{
					Garcom garcom = (Garcom) iterator2.next ();
					System.out.println ("\tGar�om : " + garcom.getID () + ".");
				}

				System.out.print ("Insira o c�digo do Gar�om para este Setor: ");
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

			operationService.setGarconsSetor (disposicao);
			System.out.println ("\n >>--- Turno iniciado com sucesso! ---<< \n");
		}
	}
}
