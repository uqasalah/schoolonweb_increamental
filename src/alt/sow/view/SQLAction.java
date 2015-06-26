package alt.sow.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.DriverManager;
import java.sql.SQLException;

import alt.sow.controller.GenericManager;
import alt.sow.domain.User;

import com.ibatis.common.jdbc.ScriptRunner;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class SQLAction extends ActionSupport {
	private static final long serialVersionUID = 1278406359685380215L;
	private GenericManager<User> manager = new GenericManager<User>();
	//
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	//
	private String driver;
	private String url;
	private String username;
	private String password;
	private String passkey;

	//
	public String execute() {
		// Create MySql Connection
		// driver "com.mysql.jdbc.Driver"
		// "jdbc:mysql://localhost:3306/database"
		if ("salahn".equalsIgnoreCase(getPasskey())) {
			try {
				Class.forName(getDriver());
				Connection con = (Connection) DriverManager.getConnection(
						getUrl(), getUsername(), getPassword());
				Statement stmt = null;
				// Initialize object for ScripRunner
				ScriptRunner sr = new ScriptRunner(con, false, false);

				// Give the input file to Reader
				Reader reader = new BufferedReader(new FileReader(
						getFileUpload()));

				// Exctute script
				sr.runScript(reader);
				addActionMessage("Success");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				addActionError(e1.toString());
			}
		} else {
			addActionError("Invalid passkey");
		}
		return SUCCESS;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasskey() {
		return passkey;
	}

	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

}
