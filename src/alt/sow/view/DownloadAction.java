package alt.sow.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164098024923044973L;
	private InputStream fileInputStream;
	private String fileName;
	private Map<String, Object> session;

	@Override
	public String execute() {
		try {
			fileInputStream = new FileInputStream(new File(getFileName()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public String preview() {
		return tcr();
	}

	public String previewStudent() {
		return std();
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public String std() {
		String file = (String) session.get("examDoclink");
		setFileName(file);
		return execute();
	}

	public String tcr() {
		String file = (String) session.get("tchrDoclink");
		setFileName(file);
		return execute();
	}

}
