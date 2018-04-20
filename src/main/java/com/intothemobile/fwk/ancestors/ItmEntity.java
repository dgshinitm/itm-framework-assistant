package com.intothemobile.fwk.ancestors;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.intothemobile.fwk.values.RowStatus;

/**
 * Data entity 표현을 위한 공통 요소의 field들을 정의하고 있는 class.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 * @see ItmValue
 */
public class ItmEntity extends ItmValue {
	private static final long serialVersionUID = -2846657558459589060L;
	
	private String rowStatus = RowStatus.ACTIVE.toString();

	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+9")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createDate;
	private String createBy = "system";
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+9")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date modifiedDate;
	private String modifiedBy = "system";
	
	/**
	 * Entity 객체의 상태를 가져온다.
	 * <blockquote>
	 * <code>ROW_STATUS</code> field와 대응함.
	 * </blockquote>
	 * @return Entity 객체의 상태
	 */
	public String getRowStatus() {
		return rowStatus;
	}
	
	/**
	 * Entity 객체의 상태를 저장한다.
	 * <blockquote>
	 * <code>ROW_STATUS</code> field와 대응함.
	 * </blockquote>
	 * {@link com.intothemobile.fwk.values.RowStatus}에서 정의된
	 * enumeration 사용을 권장한다.
	 * @param rowStatus Entity 객체의 상태.
	 * @see RowStatus
	 */
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	
	/**
	 * Entity 객체의 상태를 저장한다.
	 * <blockquote>
	 * <code>ROW_STATUS</code> field와 대응함.
	 * </blockquote>
	 * @param rowStatus Entity 객체의 상태.
	 */
	public void setRowStatus(RowStatus rowStatus) {
		this.rowStatus = rowStatus.toString();
	}
	
	/**
	 * 생성일을 가져온다.
	 * <blockquote>
	 * <code>CREATE_DATE</code> field와 대응함.
	 * </blockquote>
	 * @return 생성일
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * 생성일을 저장한다.
	 * <blockquote>
	 * <code>CREATE_DATE</code> field와 대응함.
	 * </blockquote>
	 * @param createDate 생성일
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 생성자를 가져온다.
	 * <blockquote>
	 * <code>CREATE_BY</code> field와 대응함.
	 * </blockquote>
	 * @return 생성자
	 */
	public String getCreateBy() {
		return createBy;
	}
	
	/**
	 * 생성자를 저장한다.
	 * <blockquote>
	 * <code>CREATE_BY</code> field와 대응함.
	 * </blockquote>
	 * @param createBy 생성자
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 수정일을 가져온다.
	 * <blockquote>
	 * <code>MODIFIED_DATE</code> field와 대응함.
	 * </blockquote>
	 * @return 수정일
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * 수정일을 저장한다.
	 * <blockquote>
	 * <code>MODIFIED_DATE</code> field와 대응함.
	 * </blockquote>
	 * @param modifiedDate 수정일
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * 수정자를 가져온다.
	 * <blockquote>
	 * <code>MODIFIED_BY</code> field와 대응함.
	 * </blockquote>
	 * @return 수정자
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * 수정자를 저장한다.
	 * <blockquote>
	 * <code>MODIFIED_BY</code> field와 대응함.
	 * </blockquote>
	 * @param modifiedBy 수정자
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Deprecated
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+9")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date inputDatetime;
	@Deprecated
	private String inputResp = "system";
	@Deprecated
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+9")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateDatetime;
	@Deprecated
	private String updateResp = "system";
	
	@Deprecated
	public Date getInputDatetime() {
		return inputDatetime;
	}
	@Deprecated
	public void setInputDatetime(Date inputDatetime) {
		this.inputDatetime = inputDatetime;
	}
	@Deprecated
	public String getInputResp() {
		return inputResp;
	}
	@Deprecated
	public void setInputResp(String inputResp) {
		this.inputResp = inputResp;
	}
	@Deprecated
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	@Deprecated
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	@Deprecated
	public String getUpdateResp() {
		return updateResp;
	}
	@Deprecated
	public void setUpdateResp(String updateResp) {
		this.updateResp = updateResp;
	}
}
