/**
 * 
 */
package com.oz.mail.client.dto;

import javax.mail.internet.InternetAddress;

/**
 * 
 * @author <a href="mailto:sock.sqt@gmail.com">sam</a>
 * @since Jun 5, 2013, 12:52:40 AM
 */
public class User {
	
	private String userName;
	private InternetAddress emailAddress;

	/** Crea una nueva instancia de la clase. */
	public User() {
	    super();
    }

	/**
	 * @return the emailAddress
	 */
	public InternetAddress getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(InternetAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("User [userName=").append(userName).append(", emailAddress=").append(emailAddress).append("]");
	    return builder.toString();
    }

}
