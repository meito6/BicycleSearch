package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Bicycles;
import com.example.demo.entity.BikeListInShop;
import com.example.demo.entity.ShopList;
import com.example.demo.entity.ShopListAll;
import com.example.demo.form.GetBikeListInShop;
import com.example.demo.form.GetForm;
import com.example.demo.form.GetShopAllForm;
import com.example.demo.form.GetShopForm;
import com.example.demo.service.PortfolioService;

@Controller
@RequestMapping("/portfolio")
public class MainController {
	
	private final PortfolioService portfolioservice;
	
	@Autowired
	public MainController(PortfolioService portfolioservice) {
		this.portfolioservice = portfolioservice;
	}
	
	/**
	 *バイク一覧表示
	 *@param model
	 *@retrun resources/templates/list.html
	 */
	@GetMapping("/list")
	public String bicycleList(@ModelAttribute GetForm form, Model model) {
		List<Bicycles> list = portfolioservice.findList(form);
		
		model.addAttribute("list", list);
		model.addAttribute("getForm", form);
		return "list";
	}
	
	/**
	 * 一件バイク詳細を取得し、ページ遷移
	 * @param model
	 * @param id
	 * @return resources/templates/bikedetail
	 */
	@GetMapping("/{id}")
	public String bikeDetailPage(@PathVariable int id, Model model){
		//バイク詳細取得
		Optional<Bicycles> bicyclesops = Optional.ofNullable(portfolioservice.findById(id));
		
		//NULLかどうかのチェック
		if(bicyclesops.isPresent()) {
			model.addAttribute("bicycles" ,bicyclesops.get());
			return "bikedetail";
		}else {
			model.addAttribute("error","対象データは存在しません");
			return "bikedetail";
		}
	}
	
	/**
	 *shop一覧表示
	 *@param model
	 *@retrun resources/templates/shoplist.html
	 */
	@GetMapping("/shoplist")
	public String shopList(@ModelAttribute GetShopForm form, Model model) {
		List<ShopList> list = portfolioservice.findShop(form);
		model.addAttribute("list", list);
		model.addAttribute("getShopForm", form);
		return "shoplist";
	}
	
	/**
	 * Shop一覧表示
	 * @param model
	 * @return resources/templates/shoplistall
	 */
	@GetMapping("/shoplistall")
	public String shopListAll(@ModelAttribute GetShopAllForm form, Model model) {
		List<ShopListAll> list = portfolioservice.findShopAll(form);
		model.addAttribute("list", list);
		model.addAttribute("getShopAll", form);
		
		return "shoplistall";
	}
	
	/**
	 * ショップに登録されているバイク一覧を取得
	 * @param model
	 * @return resources/templates/bikeListInShop.html
	 */
	@GetMapping("/bikeListInShop")
	public String bikeListInShop(@ModelAttribute GetBikeListInShop form, Model model) {
		List<BikeListInShop> list = portfolioservice.findBikeInShop(form);
		model.addAttribute("list", list);
		model.addAttribute("getBikeListInShop", form);
		
		return "bikeListInShop";
	}
	
}
















