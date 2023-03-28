package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserPostForm;
import com.example.demo.service.PortfolioService;

@Controller
public class LoginController {
	
private final PortfolioService portfolioservice;
	
	@Autowired
	public LoginController(PortfolioService portfolioservice) {
		this.portfolioservice = portfolioservice;
	}
	
	
	@GetMapping("/loginform")
	String gerlogin() {
		return "loginform";
	}

	@PostMapping
	String postLogin() {
	    return "redirect:/portfolio/list";
	}
	 
	 /**
	  * Userを新規登録
	  * @param UserPostForm
	  * @param model
	  * @return resources/templates/loginform
	  */
	 
	 @PostMapping(path="/insert", params="insert")
	 public String insert(@Valid @ModelAttribute UserPostForm form, BindingResult result, Model model) {
		 if(result.hasErrors()) {
			 model.addAttribute("error","パラメータエラーが発生しました");
			 return "loginForm";
		 }
		 
		 try {
			 portfolioservice.insert(form);
			 model.addAttribute("UserPostForm", form);
			 return "loginForm";
		 }catch(DuplicateKeyException e) {
			 model.addAttribute("error", "ユーザーネームは既に登録されています");
	         return "insertform";
		 }
	 }
	 /**
	  * 新規登録ページに移動
	  * @return resources/templates/insertform
	  */
	 @GetMapping("/insertform")
	 String getinsertform() {
		 return "insertform";
	 }
}
