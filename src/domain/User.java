package domain;

import java.util.LinkedList;

public class User {
	private String username;
	private String password;
	private String realname;
	private LinkedList<String> list;
	private String root;

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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public LinkedList<String> getList() {
		return list;
	}

	public void setList(LinkedList<String> list) {
		this.list = list;
	}
}
