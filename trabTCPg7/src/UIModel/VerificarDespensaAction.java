package UIModel;

import java.util.ArrayList;
import java.util.Iterator;

import domainModel.Ingrediente;

/**
 * Classe Action VerificarDespensaAction. Extende a classe abstrata UIAction.
 * 
 * @author thnitschke
 * @version 1.1
 */
public class VerificarDespensaAction extends UIAction
{

	/**
	 * M�todo de execu��o da action de verificar a despensa.
	 */
	@Override public void execute ()
	{
		ArrayList< Ingrediente > emFalta = operationService.getIngredientesEmFalta ();

		if (emFalta.isEmpty ())
		{
			System.out.println ("\n >>--- N�o possui ingredientes em falta. ---<< \n");
		}
		else
		{
			System.out.println ("\n >>--- Est�o em falta: ---<< \n");
			for (Iterator< Ingrediente > iterator = emFalta.iterator (); iterator.hasNext ();)
			{
				Ingrediente ingrediente = (Ingrediente) iterator.next ();
				System.out.println ("\t - " + ingrediente.getNome () + ";");
			}
		}
	}
}
