package com.sn.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class FileReader {

	public static String replaceKeyValPairs(String content,
			HashMap<String, String> map) {
		for (Iterator<Entry<String, String>> iterator = map.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			if (content.contains(entry.getKey())) {
				content = content.replace(entry.getKey(), entry.getValue());
			}
		}
		return content.toString();
	}

	private File file;
	private InputStream is;

	private OutputStream os;

	public FileReader(File file) throws FileNotFoundException {
		this(new FileInputStream(file), new FileOutputStream(file));
		this.file = file;
	}

	public FileReader(InputStream is, OutputStream os) {
		// TODO Auto-generated constructor stub
		this.is = is;
		this.os = os;
	}

	private void close(InputStream is) {
		// TODO Auto-generated method stub
		try {
			is.close();
		} catch (IOException e) {
			// handle exception
		}
	}

	private void close(InputStreamReader is) {
		// TODO Auto-generated method stub
		try {
			is.close();
		} catch (IOException e) {
			// handle exception
		}
	}

	@SuppressWarnings("unused")
	private void close(OutputStreamWriter os) {
		// TODO Auto-generated method stub
		try {
			os.close();
		} catch (IOException e) {
			// handle exception
		}
	}

	public InputStreamReader openFileInputStream() {
		InputStream fileInputStream = null;
		if (file != null) {
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			fileInputStream = is;
		}
		return new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
	}

	public OutputStreamWriter openFileOutputStream() {
		OutputStream fileOutputStream = null;
		if (file != null) {
			try {
				fileOutputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			fileOutputStream = this.os;
		}
		return new OutputStreamWriter(fileOutputStream,
				Charset.forName("UTF-8"));
	}

	public String[] parse(InputStream is, String splitString) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				Charset.forName("UTF-8")));
		StringBuilder builder = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException ex) {
			// handle exception
			ex.printStackTrace();
		} finally {
			close(is);
		}
		return builder.toString().split(splitString);
	}

	public String[] parse(String splitString) {
		InputStreamReader is = openFileInputStream();
		BufferedReader reader = new BufferedReader(is);
		StringBuilder builder = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException ex) {
			// handle exception
		} finally {
			close(is);
		}
		return builder.toString().split(splitString);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// String rtn[] = parse("");
		// return rtn[0];
		InputStreamReader is = openFileInputStream();
		BufferedReader reader = new BufferedReader(is);
		StringBuilder builder = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n"); //$NON-NLS-1$
			}
		} catch (IOException ex) {
			// handle exception
		} finally {
			close(is);
		}
		return builder.toString();
	}
}
