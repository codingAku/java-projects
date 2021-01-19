
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package boxes;
import elements.*;
/**
 * storage object of messages.
 * Abstract class. No box object should be created.
 */
public abstract class Box{
	/**
	 * User who owns this box.
	 */
	private User owner;
	public Box(User owner) {
		this.owner=owner;
	}
	/**
	 * 
	 * @returns the owner of this box.
	 */
	public User getOwner() {
		return owner;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

