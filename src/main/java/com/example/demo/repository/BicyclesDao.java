package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bicycles;
import com.example.demo.entity.ShopList;
import com.example.demo.entity.ShopListAll;
import com.example.demo.form.GetForm;
import com.example.demo.form.GetShopAllForm;
import com.example.demo.form.GetShopForm;
import com.example.demo.form.UserPostForm;

@Repository
public class BicyclesDao implements IBicyclesDao {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	 
    @Autowired
    public BicyclesDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


	@Override
	public List<Bicycles> findList(GetForm form) {
				
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT bi.id, bi.bikename, bi.brandid, bi.size, bi.color, bi.value, bi.imageurl, br.brandname "
				+ "FROM bicycles AS bi INNER JOIN brand AS br ON bi.brandid = br.brandid ");
		
		//パラメータ設定用Map
		Map<String, String> param = new HashMap<>();
		//パラメータが存在した場合セット
		if(form.getBrandid() != null && form.getBrandid() != "") {
			sqlBuilder.append(" WHERE br.brandid = :brandid");
			param.put("brandid", form.getBrandid());
		}
		
		String sql = sqlBuilder.toString();
		
		//タスク一覧をMapのListで取得
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        //return用の空のListを用意
        List<Bicycles> list = new ArrayList<Bicycles>();
        
      //データをDiaryにまとめる
        for(Map<String, Object> result : resultList) {
        	Bicycles bicycles = new Bicycles();
        	bicycles.setId((int)result.get("id"));
        	bicycles.setBikename((String)result.get("bikename"));
        	bicycles.setBrandid((String)result.get("brandid"));
        	bicycles.setSize((String)result.get("size"));
        	bicycles.setColor((String)result.get("color"));
        	bicycles.setValue((int)result.get("value"));
        	bicycles.setImageurl((String)result.get("imageurl"));
        	bicycles.setBrandname((String)result.get("brandname"));
            list.add(bicycles);
        }
        return list;
	}
	
	
	//Userを登録する
	@Override
	public int insert(UserPostForm form) {
		//登録件数を格納
		int count = 0;
		String sql = "INSERT INTO users(username, password) "
				+ "VALUES(:username, :password)";
		
		//パラメータ設定用MaP
		Map<String, Object> param = new HashMap<>();
		param.put("username", form.getUsername());
		param.put("password", form.getPassword());
		count = jdbcTemplate.update(sql, param);
	    return count;
	}

	
	//バイク詳細を一件取得
	@Override
	public Bicycles findById(int id) throws IncorrectResultSizeDataAccessException{
		String sql = "SELECT bi.id, bi.bikename, bi.brandid, bi.size, bi.color, bi.value, bi.imageurl, bi.forntwheel, bi.rearwheel, bi.rims, bi.tires, "
				+ "bi.tiresize, bi.shifters, bi.forntderailleur, bi.rearderailleur, bi.crank, bi.bottombracket, bi.chain, bi.saddle, bi.seatpost, bi.stem, "
				+ "bi.frame, bi.fork, bi.handlebar, br.brandname "
				+ "FROM bicycles AS bi INNER JOIN brand AS br ON bi.brandid = br.brandid "
				+ "WHERE bi.id = :id";
		//パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		//一件取得
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, param);
		Bicycles bicycles = new Bicycles();
		bicycles.setId((int)result.get("id"));
		bicycles.setBikename((String)result.get("bikename"));
		bicycles.setBrandid((String)result.get("brandid"));
		bicycles.setSize((String)result.get("size"));
		bicycles.setColor((String)result.get("color"));
		bicycles.setValue((int)result.get("value"));
		bicycles.setImageurl((String)result.get("imageurl"));
		bicycles.setForntwheel((String)result.get("forntwheel"));
		bicycles.setRearwheel((String)result.get("rearwheel"));
		bicycles.setRims((String)result.get("rims"));
		bicycles.setTires((String)result.get("tires"));
		bicycles.setTiresize((String)result.get("tiresize"));
		bicycles.setShifters((String)result.get("shifters"));
		bicycles.setForntderailleur((String)result.get("forntderailleur"));
		bicycles.setRearderailleur((String)result.get("rearderailleur"));
		bicycles.setCrank((String)result.get("crank"));
		bicycles.setBottombracket((String)result.get("bottombracket"));
		bicycles.setChain((String)result.get("chain"));
		bicycles.setSaddle((String)result.get("saddle"));
		bicycles.setSeatpost((String)result.get("seatpost"));
		bicycles.setStem((String)result.get("stem"));
		bicycles.setFrame((String)result.get("frame"));
		bicycles.setFork((String)result.get("fork"));
		bicycles.setHandlebar((String)result.get("handlebar"));
		bicycles.setBrandname((String)result.get("brandname"));
		
		return bicycles;
	}
	
	//バイクIDからショップを検索
	@Override
	public List<ShopList> findShop(GetShopForm form) {
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT bi.id, s.shopid, s.shopname, s.bikeid "
				+ "FROM bicycles AS bi INNER JOIN shops AS s  ON bi.id = s.bikeid ");
		
		//パラメータ設定用Map
		Map<String, Integer> param = new HashMap<>();
		//パラメータが存在した場合セット
				if(form.getId() != 0) {
					sqlBuilder.append(" WHERE bi.id = :id");
					param.put("id", form.getId());
				}
		
		String sql = sqlBuilder.toString();
		
		//タスク一覧をMapのListで取得
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        //return用の空のListを用意
        List<ShopList> list = new ArrayList<ShopList>();
        
        //データをShopListにまとめる
        for(Map<String, Object> result : resultList) {
        	ShopList shopList = new ShopList();
        	shopList.setId((int)result.get("id"));
        	shopList.setShopid((int)result.get("shopid"));
        	shopList.setShopname((String)result.get("shopname"));
        	shopList.setBikeid((int)result.get("bikeid"));
            list.add(shopList);
        }
        return list;
        
	}
	
	//shop一覧を取得。
	@Override
	public List<ShopListAll> findShopAll(GetShopAllForm form){
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT s.shopid, s.shopname, s.bikeid, s.prefecture, s.address, p.prefecturename "
				+ "FROM shops AS s INNER JOIN prefectures AS p ON s.prefecture = p.prefectureid "
				+ "GROUP BY shopname ");
		
		//パラメータ設定用Map
		Map<String, String> param = new HashMap<>();
		//パラメータが存在した場合にセット
		if(form.getPrefecture() != null && form.getPrefecture() != "") {
			sqlBuilder.delete(158,179);
			sqlBuilder.append(" WHERE p.prefectureid = :prefectureid");
			sqlBuilder.append(" GROUP BY shopname");
			param.put("prefectureid", form.getPrefecture());
		}
		
		
		String sql = sqlBuilder.toString();
		
		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		//return用の空のListを用意
		List<ShopListAll> list = new ArrayList<ShopListAll>();
		
		//データをShopListAllにまとめる
		for(Map<String, Object> result : resultList) {
			ShopListAll shopListAll = new ShopListAll();
            shopListAll.setShopid((int)result.get("shopid"));
			shopListAll.setShopname((String)result.get("shopname"));
			shopListAll.setBikeid((int)result.get("bikeid"));
			shopListAll.setPrefecture((String)result.get("prefecture"));
			shopListAll.setAddress((String)result.get("address"));
			shopListAll.setPrefecturename((String)result.get("prefecturename"));
			list.add(shopListAll);
			
		}
		return list;
	}
	
}









