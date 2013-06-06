/**
 * 
 */
package com.oz.mail.client.service;

import java.util.List;

import com.oz.mail.client.dto.AttachmentDto;
import com.oz.mail.client.dto.User;

/**
 * 
 * @author <a href="mailto:sock.sqt@gmail.com">Samuel Quintana</a>
 * @since Jun 5, 2013, 10:07:54 PM
 */
public interface RegistrationService {

	void register(User user, List<AttachmentDto> attachedFiles);

}
