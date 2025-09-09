package model;

import java.sql.Date;

public class RequestResponse {
	private String requestsId;
	private String userId;
	private String rqTitle;
	private String rqDescription;
	private Date rqDate;
	private String rspId;
	private String rspDescription;
	private Date rspDate;

	public String getRequestsId() {
		return requestsId;
	}

	public void setRequestsId(String requestsId) {
		this.requestsId = requestsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRqTitle() {
		return rqTitle;
	}

	public void setRqTitle(String rqTitle) {
		this.rqTitle = rqTitle;
	}

	public String getRqDescription() {
		return rqDescription;
	}

	public void setRqDescription(String rqDescription) {
		this.rqDescription = rqDescription;
	}

	public Date getRqDate() {
		return rqDate;
	}

	public void setRqDate(Date rqDate) {
		this.rqDate = rqDate;
	}

	public String getRspId() {
		return rspId;
	}

	public void setRspId(String rspId) {
		this.rspId = rspId;
	}

	public String getRspDescription() {
		return rspDescription;
	}

	public void setRspDescription(String rspDescription) {
		this.rspDescription = rspDescription;
	}

	public Date getRspDate() {
		return rspDate;
	}

	public void setRspDate(Date rspDate) {
		this.rspDate = rspDate;
	}

	public RequestResponse(String requestsId, String userId, String rqTitle, String rqDescription, Date rqDate,
			String rspId, String rspDescription, Date rspDate) {
		this.requestsId = requestsId;
		this.userId = userId;
		this.rqTitle = rqTitle;
		this.rqDescription = rqDescription;
		this.rqDate = rqDate;
		this.rspId = rspId;
		this.rspDescription = rspDescription;
		this.rspDate = rspDate;
	}

	public RequestResponse() {
	}

}
