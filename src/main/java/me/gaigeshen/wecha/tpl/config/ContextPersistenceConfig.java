package me.gaigeshen.wecha.tpl.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author gaigeshen
 */
@Configuration
@EnableTransactionManagement
public class ContextPersistenceConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("me.gaigeshen.wecha.tpl.mapper");
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		
		return configurer;
	}

	@Bean
	public SqlSession sqlSession(SqlSessionFactory factory) {

		return new SqlSessionTemplate(factory);

	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ResourcePatternResolver rpr) throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

		factory.setDataSource(dataSource);
		factory.setTypeAliasesPackage("me.gaigeshen.wecha.tpl.model");

		factory.setMapperLocations(rpr.getResources("classpath:me/gaigeshen/wecha/tpl/mapper/*.xml"));

		return factory;
	}

	@Bean(destroyMethod="close")
	public DataSource dataSource() {

		// C3P0
		return new ComboPooledDataSource();

	}
}
