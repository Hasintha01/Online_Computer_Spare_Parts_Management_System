package Admin.model;

public class Request {
	private String Req_id;
	private String user_id;
	private String Req_title;
	private String Req_description;
	private String Req_status;
	private String Req_date;

	public String getReq_id() {
		return Req_id;
	}

	public void setReq_id(String req_id) {
		Req_id = req_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReq_title() {
		return Req_title;
	}

	public void setReq_title(String req_title) {
		Req_title = req_title;
	}

	public String getReq_description() {
		return Req_description;
	}

	public void setReq_description(String req_description) {
		Req_description = req_description;
	}

	public String getReq_status() {
		return Req_status;
	}

	public void setReq_status(String req_status) {
		Req_status = req_status;
	}

	public String getReq_date() {
		return Req_date;
	}

	public void setReq_date(String req_date) {
		Req_date = req_date;
	}

	// default constructor
	public Request() {
	}
// parameterized one

	public Request(String req_id, String user_id, String req_title, String req_description, String req_status,
			String req_date) {
		Req_id = req_id;
		this.user_id = user_id;
		Req_title = req_title;
		Req_description = req_description;
		Req_status = req_status;
		Req_date = req_date;
	}

}
