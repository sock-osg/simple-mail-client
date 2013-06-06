/**
 * 
 */
package com.oz.mail.client.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.oz.mail.client.dto.AttachmentDto;
import com.oz.mail.client.dto.AttachmentType;
import com.oz.mail.client.dto.User;
import com.oz.mail.client.service.RegistrationService;

/**
 * 
 * @author <a href="mailto:sock.sqt@gmail.com">Samuel Quintana</a>
 * @since Jun 5, 2013, 9:59:57 PM
 */
@ContextConfiguration(locations = {"classpath:appctx-test.xml"})
public class RegistrationServiceTest extends AbstractTestNGSpringContextTests {
	
	@javax.annotation.Resource(name = "servRegistration")
	private RegistrationService servRegistration;

	@Test
	public void testSendMail() throws AddressException, IOException {
		User user = new User();
		user.setUserName("sockosg");
		user.setEmailAddress(new InternetAddress("sock.sqt@gmail.com"));
		
		Resource resDoc = this.applicationContext.getResource("classpath:documents/r147104.pdf");
		Resource resImg = this.applicationContext.getResource("classpath:images/ozen.png");
		
		List<AttachmentDto> listAttachments = new ArrayList<AttachmentDto>();
		
		AttachmentDto dtoAttDoc = new AttachmentDto();
		dtoAttDoc.setInputStream(resDoc.getInputStream());
		dtoAttDoc.setFileName(resDoc.getFilename());
		dtoAttDoc.setContentType("application/pdf");
		
		AttachmentDto dtoAttImg = new AttachmentDto();
		dtoAttImg.setInputStream(resImg.getInputStream());
		dtoAttImg.setFileName(resImg.getFilename());
		dtoAttImg.setContentType("image/png");
		dtoAttImg.setAttachmentType(AttachmentType.INLINE);
		
		listAttachments.add(dtoAttDoc);
		listAttachments.add(dtoAttImg);
		
		this.servRegistration.register(user, listAttachments);
	}

}
