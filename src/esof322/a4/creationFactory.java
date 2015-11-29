package esof322.a4;

/**
 * Created by lwelna on 11/17/15.
 */
public interface CreationFactory {

    Room createRoom(String desc);

    Door createDoor(Room r1, Room r2, Key key);

    Key createKey(String desc);

    Treasure createTreasure(String desc);

}
