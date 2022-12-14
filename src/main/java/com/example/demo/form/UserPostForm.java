package com.example.demo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPostForm {
	
	private int id;
	
	@NotNull(message = "usernameを入力してください")
	@Size(min = 1, max = 25, message = "25文字以内で入力してください")
    private String username;
	
	@NotNull(message = "passwordを入力してください")
	@Size(min = 1, max = 25, message = "25文字以内で入力してください")
    private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
