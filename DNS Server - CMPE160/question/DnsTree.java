
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * DNS Tree is an inverted tree structure.
 * The DNS tree has a single domain at the top of the structure called the root domain.
 * Below the root domain are the top-level domains that divide the DNS hierarchy into segments.
 */
public class DnsTree{
	/**
	 * root domain of the DNS tree
	 */
	private DnsNode root;
	/**
	 * Constructor of the DNS tree.
	 * Creates the root domain.
	 */
	public DnsTree() {
		root = new DnsNode();
	}
	/**
	 * inserts a new node to the DNS tree with domain name and Ip address.
	 * @param domainName domain name of the record.
	 * @param ipAddress Ip address of the record.
	 */
	public void insertRecord(String domainName, String ipAddress) {
		ArrayList<String> parts = this.addToArrayList(domainName);
		int k = parts.size();
		add(this.root, parts, domainName, ipAddress, 0, k);
		
	}
	private void add(DnsNode root, ArrayList<String> parts, String domainName, String ipAddress, int i, int k) {
			if(i==k) {
				if(root.isValidDomain()) {
					root.addIpAddress(ipAddress);
				}
				return;
			}
			if(findNode(parts.get(i))==null) {
				DnsNode add = new DnsNode();
				root.getChildNodeList().put(parts.get(i), add);
				if(parts.get(i).equals(domainName) || parts.get(i).equals(domainName.substring(1))) {
				root.getChildNodeList().get(parts.get(i)).addIpAddress(ipAddress);
				return;
				}
				root = root.getChildNodeList().get(parts.get(i));
			}else {
			root = findNode(parts.get(i));
			}
			add(root, parts, domainName, ipAddress, ++i, k);
	
	}
	/**
	 * clears the set and queue of Ip addresses of the record and makes the node's boolean value of isValidDomain false. 
	 * @param domainName the domain name of the record. 
	 * @returns true if successfully removed.
	 */
	public boolean removeRecord(String domainName) {
		DnsNode x = findNode(domainName);
		if(x != null) {
		if(x.isValidDomain()) {
			x.getIpAddresses().clear();
			x.getIpQueue().clear();
			x.setValidDomain(false);
			return true;
			}
		}
		return false;
	}
		
	/**
	 * removes a specific Ip address of a record
	 * @param domainName domain name of the record.
	 * @param ipAddress Ip address to be removed.
	 * @returns true if successfully removed.
	 */
	public boolean removeRecord(String domainName, String ipAddress) {
		DnsNode x = findNode(domainName);
		if(x != null && x.isValidDomain()) {
			if(x.getIpAddresses().size()==1 && x.getIpAddresses().contains(ipAddress)) {
			return this.removeRecord(domainName);
			}else if(x.getIpAddresses().contains(ipAddress)) {
			x.getIpAddresses().remove(ipAddress);
			x.getIpQueue().remove(ipAddress);
			return true;
			}
		}
		return false;
		
	}
	/**
	 * 
	 * @returns a map which contains all the valid domains and their ip addresses.
	 */
	public Map<String, Set<String>> getAllRecords(){
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		getAllRecords(this.root, map);
		return map;
	}
	private void getAllRecords(DnsNode root, Map<String, Set<String>> map) {
		if(root==null)
			return;
		for(String x : root.getChildNodeList().keySet()) {
			if(x.equals(null)) {
				continue;
			}
			if(root.getChildNodeList().get(x).isValidDomain()) {
				map.put(x, root.getChildNodeList().get(x).getIpAddresses());
			}
				getAllRecords(root.getChildNodeList().get(x), map);
						
		}	
	}
	/**
	 * 
	 * @param domainName domain name of the node.
	 * @returns the Ip address of the domain according to the Round Robin algorithm.
	 */
	public String queryDomain(String domainName) {
		DnsNode x = findNode(domainName);
		if(x.getIpQueue().isEmpty()) return null;
		String first = x.getIpQueue().poll();
		x.getIpQueue().add(first);
		return first;
	}
		
	/**
	 * Searchs the DNS tree if a node exists with the given domain name.
	 * @param domainName domain name to be searched.
	 * @returns the node if one exists in the DNS tree with the given domain name.
	 */
	public DnsNode findNode(String domainName) {
		DnsNode[] arra = new DnsNode[1];
		findNode(domainName, this.root, arra);
		return arra[0];
	}
	private void findNode(String domainName, DnsNode root, DnsNode[] arra) {
		if(arra[0] != null)
			return;
		if(root.getChildNodeList().isEmpty()) {
			arra[0]=null;
			return;
		}
	
		for(String x : root.getChildNodeList().keySet()) {	
			if(x.equals(domainName) || x.equals(domainName.substring(1))) {
				arra[0] = root.getChildNodeList().get(x);
				return;
			}else {
				findNode(domainName, root.getChildNodeList().get(x), arra);
			}
					
		}
	
	}
	/**
	 * Takes a domain name as a string and divides it to its subdomains. 
	 * Afterwards, adds the subdomains to an array list.
	 * For example, "mail.example.com" is stored as {mail.example.com, .example.com, .com}.
	 * This process is helpful to do search operations in DNS tree.
	 * @param domainName a string of domain name.
	 * @returns an array list which contains the subdomain parts of the domain name.
	 */
	public ArrayList<String> addToArrayList(String domainName) {
		String part = "";
		String dName = domainName;
		ArrayList<String> parts = new ArrayList<String>();
		while(dName.contains(".")) {
			part = dName.substring(dName.lastIndexOf(".")) + part;
			parts.add(part);
			dName = dName.substring(0, dName.lastIndexOf("."));
		}
		if(!dName.contains(".")) {
			parts.add(dName + part);
		}
		
		return parts;
	}
}	


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

