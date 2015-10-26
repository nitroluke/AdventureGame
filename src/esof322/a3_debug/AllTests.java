package esof322.a2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllTests {

	private Room myLoc;
	int direction = 1;
	Player player;
	String go = myLoc.exit(direction, player);
	Item i;
	private Door door;
	private Player p;
	private Room room;
	private Item item;
	//Item dropString = p.myThings[0];

	@Test
	public void testAddItem() {
		room.addItem(item);
		//assertEquals(room.contents.get(0), item);
	}

	@Test
	public void testRemoveItem() {
		room.removeItem(item);
		//assertNull(room.contents.get(0));
	}

	@Test
	public void testRoomEnter() {
		String expected = "Entered a new room";
		assertEquals(expected, room.enter(p));
	}

	@Test
	public void testExit() {

	}
	
	@Test
	public void testDoorEnter() {
		String expectedResult = "Your key works! The door creaks open,\nand slams behind you after you pass through.";
		assertEquals(expectedResult, door.enter(p));
	}

	@Test
	public void testGo() {
		assertEquals(go, player.go(direction));
	}

	@Test
	public void testPickUp() {
		//assertEquals(i, player.myThings[0]);
	}

	@Test
	public void testDrop() {
		//assertEquals(dropString, p.drop(0));
	}
}
