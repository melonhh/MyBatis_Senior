package cn.melon;

import cn.melon.dao.UserMapper;
import cn.melon.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestDemo1 {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("mybatisConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
    }

    @Test
    public void test1() {
//        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//            User user = userMapper.selectByPrimaryKey(1);
//
//            System.out.println(user);
//        }
    }
}
