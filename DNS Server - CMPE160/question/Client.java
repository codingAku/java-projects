
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * Client object which can issue a DNS request or DNS address lookup, providing a domain name such as “example.com”.
 * The request is received by DNS, which is responsible for finding the correct IP address for that domain name.
 */
public class Client {
	/**
	 * CachedContent object which stores the domain names and their IP address. 
	 * This object is used for storing information of DNS addresses so that it can be later accessed much more quickly without sending a request to DNS again. 
	 */
	private class CachedContent implements Comparable<CachedContent>{
		/**
		 * domainName is the address where Clients can access the website.
		 */
	private String domainName;
	/**
	 * ipAddress is a label which is used to identify one or more devices on network. it is a unique identifier for domain names.
	 */
	private String ipAddress;
	/**
	 * hitNo is the number which specifies how many times this cache was requested.
	 */
	int hitNo;
	/**
	 * Constructor of CachedContent.
	 * @param name domain name
	 * @param address IP address
	 */
	public CachedContent(String name, String address) {
		domainName = name;
		ipAddress = address;
		hitNo=1;
	}
	/**
	 * Compares two CachedContent objects based on their number of how many times they were requested.
	 * @param other CachedContent to be compared with this.
	 * @returns a negative number if the other one has a less number of request. 
	 */
	public int compareTo(CachedContent other) {
		return this.hitNo - other.hitNo;
	}
	/**
	 * 
	 * @returns the domain name.
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * 
	 * @returns the IP address.
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	}
	
	/**
	 * DNS tree where the client requests IP addresses.
	 */
	private DnsTree root;
	/**
	 * IP address of the Client.
	 */
	private String ipAddress;
	/**
	 * Cache List which stores a limited number of cache.
	 *  When a website is requested, if cacheList has its information, it is returned without sending the request to DNS tree.
	 */
	private CachedContent[] cacheList;
	
	/**
	 * Constructor of Client object.
	 * Creates a cache list of size 10.
	 * @param ipAddress IP address of the Client.
	 * @param root the DNS tree.
	 */
	public Client(String ipAddress, DnsTree root) {
		this.ipAddress = ipAddress;
		this.root = root;
		this.cacheList = new CachedContent[10];
	}
	/**
	 * 
	 * @returns cache list.
	 */
	public CachedContent[] getCacheList() {
		return cacheList;
	}
	/**
	 * Sends a request to access a website using its IP address.
	 * When a request is sent, the method first checks if the requested address is present in the cache list. If it is, returns its IP address.
	 * If the website is not on the cache list, then the request is sent to DNS. If the domain name is valid in DNS tree, the method returns the IP address of it.
	 * A website may have more than one IP address, in that case, Ip addresses are returned according to the Round Robin algorithm.
	 * Before returning the Ip adress, the method adds this website to the cache list. 
	 * @param domainName domain name of the website.
	 * @returns the IP address of the website. 
	 */
	public String sendRequest(String domainName) {
		for(int i=0; i<cacheList.length; i++) {
			if(cacheList[i]==null) {
				continue;
			}
			if(cacheList[i].domainName.equals(domainName)) {
				cacheList[i].hitNo++;
				return (cacheList[i].ipAddress);
		}
	}
		String iP = root.queryDomain(domainName);
		if(iP != null)
		addToCache(domainName, iP);
		return iP;
	}
	/**
	 * Adds the cache list a new cache.
	 * @param domainName domain name of the cache.
	 * @param ipAddress ip address of the cache.
	 */
	public void addToCache(String domainName, String ipAddress) {
		boolean isEmpty = false;
		int index = 0;
		for(int i =0; i<cacheList.length; i++) {
		if(cacheList[i]==null) {
			isEmpty = true;
			index = i;
			break;
		}
		}
		if(!isEmpty) {
			removeFromCache();
			cacheList[cacheList.length-1] = new CachedContent(domainName, ipAddress);
		}
		else {
			cacheList[index] = new CachedContent(domainName, ipAddress);
		}
		
	
		
	}
	/**
	 * removes the least requested cache from the cache list.
	 */
	private void removeFromCache() {
		Arrays.sort(cacheList);
		cacheList[0] = null;
		for(int k = 1; k<cacheList.length; k++) {
			cacheList[k-1] = cacheList[k];     
		}
		cacheList[cacheList.length-1] = null;
	}
	
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

