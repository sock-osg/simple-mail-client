package com.oz.mail.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.oz.mail.client.dto.AttachmentDto;
import com.oz.mail.client.dto.User;
import com.oz.mail.client.service.RegistrationService;

/**
 * Servicio de envios de correos.
 * @author <a href="mailto:sock.sqt@gmail.com">Samuel Quintana</a>
 * @since Jun 6, 2013, 12:19:01 AM
 */
public class SimpleRegistrationServiceImpl implements RegistrationService {

	/** Permite escribir en el archivo de log. */
	private static final Logger LOG = LoggerFactory.getLogger(SimpleRegistrationServiceImpl.class);

	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	@Override
	public void register(User user, List<AttachmentDto> attachedFiles) {
		sendConfirmationEmail(user, attachedFiles);
	}

	private void sendConfirmationEmail(final User user, final List<AttachmentDto> attachedFiles) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMultipart mimeMultipart = new MimeMultipart("related");

				mimeMessage.setRecipients(Message.RecipientType.TO, user.getEmailAddress().getAddress());
				mimeMessage.setFrom(user.getEmailAddress());
				mimeMessage.setSubject("Mensaje de prueba");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				String textBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				        "velocity/registration-confirmation.vm", model);

				// Texto
				BodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setContent(textBody, "text/html");
				mimeMultipart.addBodyPart(textBodyPart);

				for (AttachmentDto attachmentDto : attachedFiles) {
					MimeBodyPart bodyPart = new MimeBodyPart();
					bodyPart.setFileName(attachmentDto.getFileName());

					switch (attachmentDto.getAttachmentType()) {
						case ATTACHED:
							bodyPart.setHeader("Content-Type", attachmentDto.getContentType());
							bodyPart.setDisposition(Part.ATTACHMENT);
							break;
						case INLINE:
							bodyPart.setHeader("Content-ID", "<" + attachmentDto.getFileName() + ">");
							break;
						default:
							LOG.warn("!! Tipo de archivo adjunto no soportado.");
							break;
					}

					DataSource dataSource = new ByteArrayDataSource(attachmentDto.getInputStream(),
					        attachmentDto.getContentType());
					bodyPart.setDataHandler(new DataHandler(dataSource));
					
					mimeMultipart.addBodyPart(bodyPart);
				}

				mimeMessage.setContent(mimeMultipart);
			}
		};
		this.mailSender.send(preparator);
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}
