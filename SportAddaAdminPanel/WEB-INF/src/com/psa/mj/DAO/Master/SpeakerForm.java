package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


public class SpeakerForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	
	private String		speakerId;
	private String		speakerName;
	private String		description;
	private FormFile	photo;	
	private String		status;
	private String		industryid[];
	
	public String[] getIndustryid() {
		return industryid;
	}
	public void setIndustryid(String[] industryid) {
		this.industryid = industryid;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		speakerId		=	null;	
		industryid		=	null;
		speakerName		=	null;
		description		=	null;
		photo			=	null;	
		status			=	null;
	}
	public String getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FormFile getPhoto() {
		return photo;
	}
	public void setPhoto(FormFile photo) {
		this.photo = photo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
