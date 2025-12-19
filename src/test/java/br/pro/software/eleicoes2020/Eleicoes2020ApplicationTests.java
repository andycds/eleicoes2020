package br.pro.software.eleicoes2020;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

//@SpringBootTest
class Eleicoes2020ApplicationTests {

	@Test
	void contextLoads() {
		String[] s = "a@b.co;b@c.com,t@c.co r@oi.co;;a@a.co".split("[,;\\s+]");
		Arrays.stream(s).forEach(System.out::println);
	}

}
