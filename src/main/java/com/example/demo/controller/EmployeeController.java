package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SearchForm;
import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;

import entity.EmployeeEntity;

@Controller
public class EmployeeController {

    // 特定のアノテーションを付与したクラスのインスタンスを使用可能にする(依存性注入・DI) 
	@Autowired
	EmployeeService service;

	// トップページ(フォームがある画面)
    // HTTPリクエストの"GET"を受け付けるメソッドに付与するアノテーション
	@GetMapping("/")
	public String employee(Model model) {
		model.addAttribute("serachForm", new EmployeeModel());
		return "employeeList";
	}

	// データベースへの登録
    // HTTPリクエストの"POST"を受け付けるメソッドに付与するアノテーション
	@PostMapping("/create")
	public String saveEmployee(@ModelAttribute EmployeeModel employee, Model model) {
        // serviceクラスのinsertメソッドを呼び出し、DBに入力値を登録
		service.insert(employee);
		model.addAttribute("employee", employee);
		return "result";
	}

	@GetMapping
  public String employeeList(
      @ModelAttribute SearchForm form,
      Model model
  ) {
      List<EmployeeEntity> list = service.findList(form);
      model.addAttribute("list", list);
      model.addAttribute("getForm", form);
      return "list";
  }
	
}
