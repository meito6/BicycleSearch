package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Bicycles;
import com.example.demo.entity.BikeListInShop;
import com.example.demo.entity.ShopList;
import com.example.demo.entity.ShopListAll;
import com.example.demo.form.GetBikeListInShop;
import com.example.demo.form.GetForm;
import com.example.demo.form.GetShopAllForm;
import com.example.demo.form.GetShopForm;
import com.example.demo.form.UserPostForm;

public interface IBicyclesDao {
	
	//登録されているバイクを取得
	List<Bicycles> findList(GetForm form);

	//Userを登録する
	int insert(UserPostForm form);
	
	//idを指定してバイク詳細を一件取得
	Bicycles findById(int id);
	
	//bikeidを取得して店を検索
	List<ShopList> findShop(GetShopForm form);
	
	//登録されているshopを取得
	List<ShopListAll> findShopAll(GetShopAllForm form);
	
	//ショップに登録されているバイク一覧を取得
	List<BikeListInShop> findBikeInShop(GetBikeListInShop form);
}
