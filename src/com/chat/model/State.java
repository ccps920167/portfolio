package com.chat.model;

import java.util.List;
import java.util.Set;

public class State {
	private String type;
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;
	
	private List<String> memberlist;
	
	
	public State(String type, String user, List<String> memberlist) {
		super();
		this.type = type;
		this.user = user;
		this.memberlist = memberlist;
	}
	
	

	public List<String> getMemberlist() {
		return memberlist;
	}

	public void setMemberlist(List<String> memberlist) {
		this.memberlist = memberlist;
	}

	public State(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

}
