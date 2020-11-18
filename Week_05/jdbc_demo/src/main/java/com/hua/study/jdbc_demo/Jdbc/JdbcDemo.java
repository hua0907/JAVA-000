package com.hua.study.jdbc_demo.Jdbc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class JdbcDemo implements ApplicationRunner {
    private static final String url = "jdbc:h2:mem:dbtest";

    private static final String QUERY_SQL = "select * from user_info";
    private static final String INSERT_SQL = "insert into user_info(user_name,age) values('ccc',33)";
    private static final String UPDATE_SQL = "update user_info set user_name = 'ddd',age = 55 where id = 1";
    private static final String DELETE_SQL = "delete from user_info where id = 2";

    private static final String BATCH_INSERT_SQL = "insert into user_info(user_name,age) values(?,?)";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(url, "test", "test");

        Statement statement = conn.createStatement();

        query(statement, QUERY_SQL);

        insert(statement, INSERT_SQL);

        update(statement, UPDATE_SQL);

        delete(statement, DELETE_SQL);

        //事务批处理

        conn.setAutoCommit(false);//关闭自动提交
        PreparedStatement preparedStatement = conn.prepareStatement(BATCH_INSERT_SQL);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setString(1, "insert" + i);
            preparedStatement.setInt(2, i * 10);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        conn.commit();//手动提交

        conn.setAutoCommit(true);

        System.out.println("------事务提交后查询————");
        query(statement, QUERY_SQL);


    }

    private void query(Statement statement, String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("当前数据库记录：");
        while (resultSet.next()) {
            System.out.println("第" + resultSet.getRow() + "行是：" +
                    " id: " + resultSet.getInt("id") +
                    " username: " + resultSet.getString("user_name") +
                    " age:" + resultSet.getInt("age"));
        }
    }

    private void insert(Statement statement, String sql) throws SQLException {
        int i = statement.executeUpdate(sql);
        System.out.println("-------------insert----------------");
        System.out.println("成功插入了: " + i + "条记录");
        query(statement, QUERY_SQL);
    }

    private void update(Statement statement, String sql) throws SQLException {
        int i = statement.executeUpdate(sql);
        System.out.println("-------------update----------------");
        System.out.println("成功修改了: " + i + "条记录");
        query(statement, QUERY_SQL);
    }

    private void delete(Statement statement, String sql) throws SQLException {
        int i = statement.executeUpdate(sql);
        System.out.println("-------------delete----------------");
        System.out.println("成功删除了: " + i + "条记录");
        query(statement, QUERY_SQL);
    }
}
