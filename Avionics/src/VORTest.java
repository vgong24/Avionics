import static org.junit.Assert.*;

import org.junit.Test;


public class VORTest {

	@Test
	public void testMain() {
	}

	@Test
	public void testVOR() {
	}

	@Test
	public void testDirection() {
		VOR tester = new VOR();
		int obs = 200;
		int radial = 20;
		assertEquals("direction relative to VOR", "TO", tester.direction(obs,radial));//random test
		//check left side TO
		assertEquals("direction relative to VOR", "TO", tester.direction(269,0));
		assertEquals("direction relative to VOR", "TO", tester.direction(271,2));
		//check right side
		assertEquals("direction relative to VOR", "TO", tester.direction(90,359));
		assertEquals("direction relative to VOR", "TO", tester.direction(90,181));
		//check middle
		assertEquals("direction relative to VOR", "TO", tester.direction(0,269));
		assertEquals("direction relative to VOR", "TO", tester.direction(0,91));
		//check bottom
		assertEquals("direction relative to VOR", "TO", tester.direction(180,271));
		assertEquals("direction relative to VOR", "TO", tester.direction(180,89));
		
		//check left side FROM
		assertEquals("direction relative to VOR", "FROM", tester.direction(270,359));
		assertEquals("direction relative to VOR", "FROM", tester.direction(270,181));
		//check right side
		assertEquals("direction relative to VOR", "FROM", tester.direction(90,1));
		assertEquals("direction relative to VOR", "FROM", tester.direction(90,179));
		//check middle
		assertEquals("direction relative to VOR", "FROM", tester.direction(0,271));
		assertEquals("direction relative to VOR", "FROM", tester.direction(0,89));
		//check bottom
		assertEquals("direction relative to VOR", "FROM", tester.direction(180,269));
		assertEquals("direction relative to VOR", "FROM", tester.direction(180,91));
		
		//check left OFF
		assertEquals("direction relative to VOR", "OFF", tester.direction(270,0));
		assertEquals("direction relative to VOR", "OFF", tester.direction(270,180));
		//check right
		assertEquals("direction relative to VOR", "OFF", tester.direction(90,0));
		assertEquals("direction relative to VOR", "OFF", tester.direction(90,180));
		//check middle
		assertEquals("direction relative to VOR", "OFF", tester.direction(0,270));
		assertEquals("direction relative to VOR", "OFF", tester.direction(0,90));
		//check bottom
		assertEquals("direction relative to VOR", "OFF", tester.direction(180,270));
		assertEquals("direction relative to VOR", "OFF", tester.direction(180,90));
	}

}
