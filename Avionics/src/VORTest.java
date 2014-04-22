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
	public void testRadioDegree() {
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
	
	//test needleDirection
	//Testing position of plane near, far, left, and right of obs line
	/**
	 * obs direction: South, North, slightly northwest, east, west
	 * plane position: +/- 10 degrees from obs, on obs and opposite of obs, perpendicular to obs
	 */
	@Test
	public void testNeedleDirection(){
		SimulatedRadio radio = new SimulatedRadio(45, true);
		VOR tester = new VOR(90, radio);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(180,0);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(180, 180);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(180, 5);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(180, 355);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(180, 175);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(180, 185);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		
		tester.setOR(0,0);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(0,180);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(0, 5);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(0, 355);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(0, 175);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(0, 185);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		
		tester.setOR(355,355);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(355,175);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(355, 6);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(355, 345);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(355, 170);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(355, 185);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		
		
		tester.setOR(90,90);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(90,270);
		assertEquals("direction of needle", "Middle", tester.needleDirection());
		tester.setOR(90, 100);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(90, 80);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(90, 260);
		assertEquals("direction of needle", "Left", tester.needleDirection());
		tester.setOR(90, 275);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(90, 0);
		assertEquals("direction of needle", "Right", tester.needleDirection());
		tester.setOR(90,180);
		assertEquals("direction of needle", "Left", tester.needleDirection());

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetNeedle(){
		SimulatedRadio radio = new SimulatedRadio(90, true);
		VOR tester = new VOR(90, radio);
		tester.needleDirection();
		//assertEquals(0.0, 0.0, tester.needleDirection());
		
		tester.setOR(180,0);
	}

	

}
