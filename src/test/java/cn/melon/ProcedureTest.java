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
import java.util.List;

public class ProcedureTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("mybatisConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
    }

    /**
     * 调用存储过程
     */
    @Test
    public void test1() {
//        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//            List<User> users = userMapper.selectSomeUser(1,4);
//            System.out.println(users);
//        }
    }
}
