import java.io.Serializable;

public class RequestEnum implements Serializable {
	public enum Request {
		giveData,getData,removeData,findData;
	}
	
	
	public static Request getRequest(int i) {
		if (i == 1)
			return Request.giveData;
		else if (i == 2)
			return Request.getData;
		else if (i == 3)
			return Request.removeData;	
		else 
			return Request.findData;
	}
	
}
