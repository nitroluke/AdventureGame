package esof322.a4.concreteLevelFactories;

import esof322.a4.CreationFactory;
import esof322.a4.Room;
import esof322.a4.Door;
import esof322.a4.Key;
import esof322.a4.Oil;
import esof322.a4.Treasure;

/**
 * Created by lwelna on 11/26/15.
 */
public class Level1Factory implements CreationFactory {

    @Override
    public Room createRoom(String desc) {

        return new Room(desc);
    }

    @Override
    public Door createDoor(Room from, Room into, Key key) {

        return new Door(from, into, key, true);
    }

    @Override
    public Key createKey(String desc) {
        return new Key(desc);
    }

    @Override
    public Treasure createTreasure(String desc) {

        return new Treasure(desc);
    }

    public Oil createOil(String desc) {
        return new Oil(desc);
    }
}
