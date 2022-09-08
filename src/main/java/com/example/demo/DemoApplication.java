package com.example.demo;

import com.example.demo.model.Body;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.model.RedirectUrls;
import com.example.demo.security.Crypto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(DemoApplication.class, args);

		//test
		ObjectMapper objectMapper = new ObjectMapper();

		Logger logger = LoggerFactory.getLogger(DemoApplication.class);

		String secret =  "SAIPPUAKAUPPIAS";
		Map<String,String> headers = new LinkedHashMap<>();
		headers.put("checkout-account", "375917");
		headers.put("checkout-algorithm", "sha256");
		headers.put("checkout-method", "POST");
		headers.put("checkout-nonce", "564635208570151");
		headers.put("checkout-timestamp", "2018-07-06T10:01:31.904Z");

		var item = Item.builder()
				.unitPrice(1525)
				.units(1)
				.vatPercentage(24)
				.productCode("#1234")
				.deliveryDate("2018-09-01")
				.build();

		var customer = new Customer("test.customer@example.com");

		var redirectUrls = new RedirectUrls("https://ecom.example.com/cart/success", "https://ecom.example.com/cart/cancel");

		var b = Body.builder()
				.stamp("unique-identifier-for-merchant")
				.reference("3759170")
				.amount(1525)
				.currency("EUR")
				.language("FI")
				.items(Arrays.asList(item))
				.customer(customer)
				.redirectUrls(redirectUrls)
				.build();

		var body = objectMapper.writeValueAsString(b);
		var encData = Crypto.CalculateHmac(secret,headers,body);
		logger.info("Encrypted data: " + encData);
	}

}
