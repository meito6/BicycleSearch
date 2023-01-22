package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Bicycles;
import com.example.demo.entity.ShopList;
import com.example.demo.entity.ShopListAll;
import com.example.demo.form.GetForm;
import com.example.demo.form.GetShopAllForm;
import com.example.demo.form.GetShopForm;
import com.example.demo.form.UserPostForm;
import com.example.demo.repository.IBicyclesDao;

@Service
@Transactional
public class PortfolioService {

	private final IBicyclesDao dao;
	
	@Autowired
	public PortfolioService(IBicyclesDao dao) {
		this.dao = dao;
	}
	
	public List<Bicycles> findList(GetForm form){
		return dao.findList(form);
	}
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	public int insert(UserPostForm form) {
		
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		
		return dao.insert(form);
	}
	
	public Bicycles findById(int id) {
		try {
			return dao.findById(id);
		}catch(IncorrectResultSizeDataAccessException e){
			return null;
		}
	}
	
	public List<ShopList> findShop(GetShopForm form){
		return dao.findShop(form);
	}
	
	public List<ShopListAll> findShopAll(GetShopAllForm form){
		return dao.findShopAll(form);
	}
}
