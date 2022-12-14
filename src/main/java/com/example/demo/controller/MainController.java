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
import com.example.demo.entity.ShopList;
import com.example.demo.form.GetForm;
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
	@GetMapping
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
	 *@retrun resources/templates/bikedetail.html
	 */
	@GetMapping("/shoplist")
	public String shopList(@ModelAttribute GetShopForm form, Model model) {
		List<ShopList> list = portfolioservice.findShop(form);
		model.addAttribute("list", list);
		model.addAttribute("getShopForm", form);
		return "shoplist";
	}

}
