package Admin.model;

public class Response {
	private String rsp_id;
	private String rq_id;
	private String rsp_description;
	private String rsp_date;

	public String getRsp_id() {
		return rsp_id;
	}

	public void setRsp_id(String rsp_id) {
		this.rsp_id = rsp_id;
	}

	public String getRq_id() {
		return rq_id;
	}

	public void setRq_id(String rq_id) {
		this.rq_id = rq_id;
	}

	public String getRsp_description() {
		return rsp_description;
	}

	public void setRsp_description(String rsp_description) {
		this.rsp_description = rsp_description;
	}

	public String getRsp_date() {
		return rsp_date;
	}

	public void setRsp_date(String rsp_date) {
		this.rsp_date = rsp_date;
	}

//parameterized one
	public Response(String rsp_id, String rq_id, String rsp_description, String rsp_date) {
		this.rsp_id = rsp_id;
		this.rq_id = rq_id;
		this.rsp_description = rsp_description;
		this.rsp_date = rsp_date;
	}

//default one
	public Response() {
	}

	public void rsp_id_generate(int idcount) {
		this.rsp_id = "RSP-0" + idcount;
	}
}
