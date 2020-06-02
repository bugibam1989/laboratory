package com.laboratory.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String index;
	private String user_id;
	private String password;
	private String user_level;
	private String user_name;
	private String user_mac;
}
