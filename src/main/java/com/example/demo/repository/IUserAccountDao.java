package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.User;

public interface IUserAccountDao {

	//Userを取得
		Optional<User> findUser(String username);
}
