package com.hyundairoad.hyundairoad.config;

import com.hyundairoad.hyundairoad.typehandler.BooleanTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = sessionFactory.getObject().getConfiguration();
        configuration.getTypeHandlerRegistry().register(LocalDateTimeTypeHandler.class); // TypeHandler 등록
        configuration.getTypeHandlerRegistry().register(BooleanTypeHandler.class);
        return sessionFactory.getObject();
    }
}
