package com.example.demo.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.SearchForm;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;

import entity.EmployeeEntity;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	// データベースよりフォーラム(掲示板)の一覧を取得
	public List<EmployeeEntity> findList(SearchForm form) {
    return dao.findList(form);
}

	// データベースに値を登録
	public void insert(EmployeeModel employee) {
		repository.save(employee);
	}
}

