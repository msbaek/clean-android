package com.github.msbaek.rxessentials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
	@SerializedName("items")
	@Expose
	private List<User> users;
}
