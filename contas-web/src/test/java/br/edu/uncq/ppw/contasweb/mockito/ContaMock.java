package br.edu.uncq.ppw.contasweb.mockito;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class ContaMock {

	public class Calc {
		public int somar(int n1, int n2) {
			return n1 + n2;
		}
	}

	@Test
	public void deveSomarNumero() {
		Calc calc = Mockito.mock(Calc.class);
		Mockito.when(calc.somar(8, 2)).thenReturn(10);
		int res = calc.somar(8, 2);
		assertEquals(10, res);
	}
	
	@Test
	public void deveExecutarUmaVez(){
		Calc calc = Mockito.mock(Calc.class);
		Mockito.when(calc.somar(8, 2)).thenReturn(10);
		int res = calc.somar(8, 2);
		Mockito.verify(calc, Mockito.times(1)).somar(8, 2);
		assertEquals(10, res);
	}

}
