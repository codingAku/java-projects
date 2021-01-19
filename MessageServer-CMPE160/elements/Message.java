
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;
import java.util.*;
import boxes.*;
/**
 * Main communication object of the program.
 * Implements the Comparable<T> interface of Java.
 */
public class Message implements Comparable<Message>{
	/**
	 * number of messages created in the program. 
	 */
	public static int numOfMessages = 0;          
	/**
	 * ID number of messages. It is unique to all messages and given by the program.
	 */
    private int id;
    /**
     * text part of a message which is a String.
     */
    public String body;
    /**
     * User who sent this message.
     */
    private User sender;
    /**
     * User whom this message was sent to.
     */
    private User receiver;
    /**
     * time of when the message was sent to server.
     */
    private int timeStampSent;
    /**
     * time of when the message was read by User's Inbox.
     */
    private int timeStampRead; 
    /**
     * time of when the message was received by User's Inbox.
     */
    private int timeStampReceived;
    /**
     * Constructor of Message. 
     * Increments the number of messages by one and sets it as the message's ID number.
     * @param sender User who sent the message.
     * @param receiver User whom the message was sent.
     * @param body String part of the message.
     * @param server Server who the message will be received by.
     * @param time current time.
     */
    public Message(User sender, User receiver, String body, Server server, int time) {
    	this.id=numOfMessages++;
    	this.sender = sender;
    	this.receiver = receiver;
    	this.body=body;
    	this.setTimeStampSent(time);
    	
   
   
    }
    /**
     * Compares the messages by the length of their body.
     * @param o Message which this will be compared with.
     * @returns a negative number if this message's body is shorter, a positive number if it is longer and zero if it is equal to other message's body length. 
     */
    public int compareTo(Message o) {
    	return this.body.length() - o.body.length();
    }
    /**
     * @Override of equals method.
     * @param o any object to be checked if they are equal with this Message.
     * @returns true if this objects have the same ID number.
     */
    public boolean equals(Object o) {
    	Message x = (Message) o;
    	return x.id == this.id ;
    }
    /**
     * @Override of toString method.
     * @returns a String containing the information of this message's sender, receiver, body, received time and read time.
     */
    public String toString() {
    	String read = "";
    	String received="";
    	if(this.timeStampRead!=0) {
    		read = ""+this.timeStampRead;
    	}
    	if(this.timeStampReceived != 0) {
    		received = ""+this.timeStampReceived;
    	}
    	return "\tFrom: " + this.sender.getId() + " To: " + this.receiver.getId() + "\n\tReceived: "+received+ " Read: "+read+"\n\t"+this.body+"\n";
    	
    }
    /**
     * 
     * @param timeStampSent time to be set time of sent.
     */
	public void setTimeStampSent(int timeStampSent) {
		this.timeStampSent = timeStampSent;
	}
	/**
	 * 
	 * @param timeStampRead time to be set time of read.
	 */
	public void setTimeStampRead(int timeStampRead) {
		this.timeStampRead = timeStampRead;
	}
	/**
	 * @param timeStampReceived time to be set time of received.
	 */
	public void setTimeStampReceived(int timeStampReceived) {
		this.timeStampReceived = timeStampReceived;
	}
	/**
	 * 
	 * @returns User who this message was sent from.
	 */
	public User getSender() {
		return sender;
	}
	/**
	 * 
	 * @returns Whom this message was sent to.
	 */
	public User getReceiver() {
		return receiver;
	}
	/**
	 * 
	 * @returns the ID number of this message.
	 */
	public int getID() {
		return id;
	}
	/**
	 * 
	 * @returns the String body of this message.
	 */
	public String getBody() {
		return body;
	}

	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

