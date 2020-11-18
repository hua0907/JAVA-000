package com.hua.study.jdbc_demo.hikari;

import com.hua.study.jdbc_demo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Order(2)
@Component
public class HikariDemo implements ApplicationRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String QUERY_SQL = "select * from user_info";
    private static final String INSERT_SQL = "insert into user_info(user_name,age) values('ddd',777)";
    private static final String UPDATE_SQL = "update user_info set user_name = 'eee',age = 36 where id = 9";
    private static final String DELETE_SQL = "delete from user_info";

    private static final String BATCH_INSERT_SQL = "insert into user_info(user_name,age) values(?,?)";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(ApplicationArguments args) {
        System.out.println();
        System.out.println();
        System.out.println("---------------jdbcTemplate操作---------------------");
        query();

        deleteAll();

        insert();

        update();

        //批次
        batchInsert();
    }

    private void batchInsert() {
        System.out.println("--------------批次插入-------------");
        jdbcTemplate.batchUpdate(BATCH_INSERT_SQL, new BatchPreparedStatementSetter() {
            int size = 5;

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                for (int j = size; j > 0; j--) {
                    preparedStatement.setString(1, "batchInsert: " + j);
                    preparedStatement.setInt(2, j * 10);
                }
            }

            @Override
            public int getBatchSize() {
                return size;
            }
        });
    }

    private void query() {
        List<UserInfo> userList = jdbcTemplate.query(QUERY_SQL, new BeanPropertyRowMapper<>(UserInfo.class));
        System.out.println("当前数据总条数：" + userList.size());
        userList.forEach(u -> System.out.println("第" + u.getId() + "行数据：" +
                " user_name: " + u.getUserName() +
                " age: " + u.getAge()));
    }

    private void insert() {
        System.out.println("--------------插入一条数据-------------");
        jdbcTemplate.update(INSERT_SQL);
        query();
    }

    private void update() {
        System.out.println("--------------修改一条数据-------------");
        jdbcTemplate.update(UPDATE_SQL);
        query();
    }

    private void deleteAll() {
        System.out.println("--------------删除所有数据-------------");
        jdbcTemplate.update(DELETE_SQL);
        query();
    }


}
