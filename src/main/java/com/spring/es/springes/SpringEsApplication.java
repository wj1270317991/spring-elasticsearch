package com.spring.es.springes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SpringEsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(SpringEsApplication.class, args);
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			String property = run.getEnvironment().getProperty("server.port");
			System.out.println("http://"+host+":"+property+"/swagger-ui.html");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}

