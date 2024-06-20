package com.projeto.sistema.view;

import com.projeto.sistema.ManagePlaceApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ManagePlaceApplicationTests {
	@Mock
	ApplicationContext applicationContext;

	@Test
	void setApplicationContext() throws Exception {
		ManagePlaceApplication.main(new String[] {});
	}

}
