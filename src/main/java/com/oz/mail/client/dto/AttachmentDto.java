package com.oz.mail.client.dto;

import java.io.InputStream;
import java.util.Arrays;

/**
 * Contiene datos referentes a un archivo adjunto.
 * 
 * @author <a href="mailto:sock.sqt@gmail.com">Samuel Quintana</a>
 * @since 15/04/2013, 16:46:16
 */
public class AttachmentDto implements java.io.Serializable {

	/** Identificador unico de la clase para serializacion. */
	private static final long serialVersionUID = -8037500931042885728L;

	/** Nombre del archivo adjunto. */
	private String fileName;
	/** Tipo de archivo. */
	private String contentType;
	/** Arreglo de bytes del archivo adjunto. */
	private byte[] bytesArray;
	/** InputStream del recurso. */
	private InputStream inputStream;
	/** Tipo de archivo. */
	private AttachmentType attachmentType = AttachmentType.ATTACHED;

	/** Crea una nueva instancia de la clase. */
	public AttachmentDto() {
		super();
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return this.inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the bytesArray
	 */
	public byte[] getBytesArray() {
		return this.bytesArray;
	}

	/**
	 * @param bytesArray the bytesArray to set
	 */
	public void setBytesArray(byte[] bytesArray) {
		this.bytesArray = bytesArray;
	}

	/**
	 * @return the attachmentType
	 */
	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	/**
	 * @param attachmentType the attachmentType to set
	 */
	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("AttachmentDto [fileName=").append(fileName).append(", contentType=").append(contentType)
	            .append(", bytesArray=").append(Arrays.toString(bytesArray)).append(", inputStream=").append(inputStream)
	            .append(", attachmentType=").append(attachmentType).append("]");
	    return builder.toString();
    }

}
