package a1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllTest {

	public void testEquals() {
		//	Obvious true
		Vector3D a = new Vector3D(1.0, 1.0, 1.0);
		Vector3D b = new Vector3D(1.0, 1.0, 1.0);
		assertTrue(a.equals(b));
		//	Obvious false (test each component)
		Vector3D c = new Vector3D(2.0, 1.0, 1.0);
		Vector3D d = new Vector3D(1.0, 2.0, 1.0);
		Vector3D e = new Vector3D(1.0, 1.0, 2.0);
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(e));
		//	False, because the tolerance states difference must be no greater than .001 
		Vector3D f = new Vector3D(1.001, 1.0, 1.0);
		Vector3D g = new Vector3D(0.999, 1.0, 1.0);
		assertFalse(f.equals(g));
		//	True, because the difference is less than 0.001
		Vector3D h = new Vector3D(1.0005, 1.0, 1.0);
		assertTrue(a.equals(h));
	}
	
	public void testScale(){
		//Identity
		double id = 1;
		Vector3D a = new Vector3D(1.0, 1.0, 1.0);
		Vector3D idAResult = a.scale(1);
		Vector3D idAExpected = new Vector3D(1.0, 1.0, 1.0);
		assertTrue(idAResult.equals(idAExpected));
		
	}
	
	@Test
	public void test() {
		testEquals();
		testScale();
	}

}
