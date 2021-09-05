package com.talkabout.sqlsession;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SqlSession {

	   private Logger log = LoggerFactory.getLogger(this.getClass());  
	   @Autowired
	   //@Qualifier("UnderscoreToCamelCase")
	   //@Qualifier("Underscore") 
	   SqlSessionFactory sqlSessionFactory;
	   
	   @Test
	   void test() {
	      log.error(sqlSessionFactory.toString());
	      assertNotNull(sqlSessionFactory);
	   }

}
