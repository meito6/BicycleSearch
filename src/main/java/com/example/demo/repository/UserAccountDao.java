package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class UserAccountDao implements IUserAccountDao{
	
private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserAccountDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<User> findUser(String username){
		
		String sql = "SELECT username, password "
				+ "FROM users "
				+ "WHERE username = :username";
		//パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("username", username);
		
		User user = new User();
		//一件取得
		try {
			Map<String, Object> result = jdbcTemplate.queryForMap(sql, param);
			user.setUsername((String)result.get("username"));
			user.setPassword((String)result.get("password"));
		}catch(EmptyResultDataAccessException e){
			Optional<User> userOpl = Optional.ofNullable(user);
			return userOpl;
		}
		
		//ラップする
		Optional <User> userOpl = Optional.ofNullable(user);
	    return userOpl;
	}
}
