package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, String> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
 
    @Autowired
    public DiaryDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public List<Diary> findList() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name "
                + "FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd "
                + "WHERE c.group_cd = '01'");
 
        String sql = sqlBuilder.toString();
 
        // パラメータ設定用Map
        Map<String, String> param = new HashMap<>();
        //タスク一覧をMapのListで取得
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        //return用の空のListを用意
        List<Diary> list = new ArrayList<Diary>();
 
        //データをDiaryにまとめる
        for(Map<String, Object> result : resultList) {
            Diary diary = new Diary();
            diary.setId((int)result.get("id"));
            diary.setCategory((String)result.get("category"));
            diary.setTitle((String)result.get("title"));
            diary.setContent((String)result.get("content"));
            diary.setDate((String)result.get("date"));
            diary.setUpdate_datetime((Timestamp)result.get("update_datetime"));
            diary.setName((String)result.get("name"));
            list.add(diary);
        }
        return list;
    }
}