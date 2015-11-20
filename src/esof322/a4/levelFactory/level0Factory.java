package esof322.a4.levelFactory;

import esof322.a4.creationFactory;
import esof322.a4.Door;
import esof322.a4.Room;
import esof322.a4.Key;
import esof322.a4.Treasure;
import esof322.a4.Player;

/**
 * Created by lwelna on 11/19/15.
 */
public class level0Factory implements creationFactory {

    @Override
    public Room createRoom(String desc) {
        return new Room(desc);
    }

    @Override
    public Door createDoor(Room from, Room into, Key key) {
        return new Door(from, into, key);
    }

    @Override
    public Key createKey(String desc) {
        return new Key(desc);
    }

    @Override
    public Treasure createTreasure(String desc) {
        return new Treasure(desc);
    }

    @Override
    public Player createPlayer() {
        return new Player();
    }

}
