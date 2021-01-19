
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;
import java.util.*;
import boxes.*;
/**
 * Users of the program. All users have a list of friends. They can add each other to their lists.
 * If two users are friends they can  send messages to each other.
 * All users have a Inbox where the messages sent to them from their friends are kept.
 * All users also have a Outbox where the messages they sent are kept.
 *
 */
public class User{
	/**
	 * ID number of the Users. All of them are unique and given by the program.
	 */
	private int id;
	/**
	 * Inbox of the user where the messages sent to them from their friends are kept.
	 */
    private Inbox inbox;
    /**
     * Outbox of the user where the messages they sent are kept in a queue.
     */
    private Outbox outbox;
    /**
     * A list which contains the Users who are friends with this. 
     */
    private ArrayList<User> friends;
    /**
     * Constructor of the User.
     * Also creates the inbox and outbox of the User.
     * @param id ID number of the User.
     */
    public User(int id) {
    	this.setId(id);
    	friends = new ArrayList<User>();
    	inbox = new Inbox(this);
    	outbox = new Outbox(this);
    }
    /**
     * If the User who is given as a parameter is not already in the friends list of this user, adds it. 
     * Also adds this user to the other User's friends' list.
     * @param other Other user to be added to friends list.
     */
    public void addFriend(User other) {
    	if(!this.isFriendsWith(other)) {
    	this.friends.add(other);
    	other.friends.add(this);
    	}
    }
    /**
     * If the User who is given as a parameter is a friend of this user, removes it from this User's friends list.
     * @param other Other user to be removed from this User's friends list.
     * Also removes this user from the other User's friends' list.
     */
    public void removeFriend(User other) {
    	if(this.isFriendsWith(other)) {
    		this.friends.remove(other);
    		other.friends.remove(this);
    	}
    }
    /**
     * 
     * @param other Other User to be checked if they are in this User's friends list.
     * @returns true if Other user is in the friends list of this one.
     */
    public boolean isFriendsWith(User other) {
    	return this.friends.contains(other); 
    }
    /**
     * Creates a new message and sends it to Server and adds it to its own Outbox's sent Queue.
     * @param receiver User whom this message was sent.
     * @param body text body of the Message.
     * @param time current time.
     * @param server Server who the message will be sent.
     */
    public void sendMessage(User receiver, String body, int time, Server server) {
    	Message x = new Message(this, receiver, body, server, time);
    	server.getMsgs().add(x);
    	this.outbox.getSent().add(x);
    	server.setCurrentSize(server.getCurrentSize()+body.length());
    }
/**
 * 
 * @returns ID number of the User.
 */
	public int getId() {
		return id;
	}
/**
 * 
 * @param id ID number to be set.
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * 
 * @returns Inbox of this User.
 */
	public Inbox getInbox() {
		return inbox;
	}
/**
 * 
 * @returns Outbox of this User.
 */
	public Outbox getOutbox() {
		return outbox;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

