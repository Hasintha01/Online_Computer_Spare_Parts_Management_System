package Admin.model;

import java.util.List;

public class RequestWithImages {
	private Request request; // reuse previous request class
	private List<Integer> imageIDs;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public List<Integer> getImageList() {
		return imageIDs;
	}

	public void setImageList(List<Integer> imageIDs) {
		this.imageIDs = imageIDs;
	}

}
