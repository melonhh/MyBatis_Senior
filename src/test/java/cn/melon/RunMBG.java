package cn.melon;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RunMBG {
    @Test
    public void run() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String path = this.getClass().getClassLoader().getResource("mbgConfig.xml").getPath();
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void mysql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/?user=root&password=dwj123##&useUnicode=true&characterEncoding=UTF8");
            Statement s =  conn.createStatement();
            System.out.println(conn.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
