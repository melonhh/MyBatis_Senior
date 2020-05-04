package cn.melon;

import cn.melon.dao.UserMapper;
import cn.melon.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("mybatisConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
    }

    @Test
    public void test1() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Page<User> page = PageHelper.startPage(1,5);
            List<User> users = userMapper.selectAllUser();
            for(User user: users) {
                System.out.println(user);
                System.out.println(page.getPageNum());
            }

            page = PageHelper.startPage(2, 5);
            users = userMapper.selectAllUser();
            for(User user: users) {
                System.out.println(user);
                System.out.println(page.getPageNum());
            }
//            PageInfo<User> info = new PageInfo<>(page);
//            System.out.println(info.toString());
//            System.out.println(page.toString());
        }
    }
}
