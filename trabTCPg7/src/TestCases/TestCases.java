package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

import domainModel.Atendente;
import domainModel.AuxiliarCozinha;
import domainModel.Cozinheiro;
import domainModel.Funcionario;
import domainModel.Garcom;
import domainModel.Gerente;
import domainModel.Mesa;
import domainModel.Setor;

public class TestCases {

	Garcom g = new Garcom("G000");
	Cozinheiro c = new Cozinheiro("C000");
	Gerente gr = new Gerente ("GR000");
	AuxiliarCozinha ac = new AuxiliarCozinha ("AC000");
	Atendente at = new Atendente ("AT000");
	
	Setor s = new Setor("Azul");
	Setor s2 = new Setor("Laranja");
	Mesa m1 = new Mesa(s, 2, "M01");
	Mesa m2 = new Mesa(s2, 2, "M01");
	
	ArrayList<Funcionario> list = new ArrayList<>(); 
	ArrayList<Setor> setor = new ArrayList<>();
	
	
	@Test
	public void testAddFuncionarios() {
		assertEquals(list.add(g), true);
		assertEquals(list.add(c), true);
		assertEquals(list.add(gr), true);
		assertEquals(list.add(ac), true);
		assertEquals(list.add(at), true);
	}
	
	@Test
	public void testAddMesasNosSetores() {
		s.addMesa(m1);
		s2.addMesa(m2);
		assertEquals(s.getMesas().size(),1);
		assertEquals(s2.getMesas().size(),1);
		
	}
	

	@Test
	public void testAddSetores() {
		assertEquals(setor.add(s),true);
		assertEquals(setor.add(s),true);
	}

}
