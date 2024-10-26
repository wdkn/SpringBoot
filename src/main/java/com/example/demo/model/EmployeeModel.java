package com.example.demo.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

// getter(), setter()を自動生成するアノテーション
@Data
// JPAのエンティティであることを示すアノテーション
@Entity
// エンティティに対応するテーブル名を指定。 "forums"部分はPostgreSQLで作成したテーブル名
@Table(name = "employee", schema="public")
public class EmployeeModel {

    // エンティティの主キーを指定。今回であれば "id" カラムになる
	@Id
    // オートインクリメント。主キー "id" の値を自動採番する
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    // カラムに名前を付与
	@Column(name = "id")
	private String id;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "age")
	private Integer age;
	
	@Column(name = "birthday")
	private Date birthday;
}
