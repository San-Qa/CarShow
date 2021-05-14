package com.example.demo.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserTest {
	
	@LocalServerPort
	private int port;
	
	private RemoteWebDriver driver;
	
	private WebDriverWait wait;

	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		this.wait = new WebDriverWait(driver, 5);
	}

	@Test
	void test() {
		this.driver.get("http://localhost:" + port);
		
		WebElement title = this.driver.findElement(By.xpath("/html/body/h1"));
		
		assertThat(title.getText()).isEqualTo("Car Showroom");
	}
	
	@Test
	void testCreate() {
		this.driver.get("http://localhost:" + port);
		
		WebElement add=driver.findElement(By.xpath("//button[contains(@class, 'btn success open-modal')]"));
		add.click();
		
		WebElement name=driver.findElement(By.name("name"));
		name.sendKeys("BMW");
		
		WebElement model=driver.findElement(By.name("model"));
		model.sendKeys("Z4");
		
		WebElement save = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/form/button"));
		save.click();
		
		Assertions.assertTrue(driver.getTitle().equals("Crud"));
		
	}
	
	@Test
	void testUpdate() {
		this.driver.get("http://localhost:" + port);
		
		WebElement edit=driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr/td[4]/a[2]/i"));
		edit.click();
		
		WebElement name=driver.findElement(By.name("name"));
		name.sendKeys("BMWZ");
		
		WebElement model=driver.findElement(By.name("model"));
		model.sendKeys("Z4S");
		
		WebElement save = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/form/button"));
		save.click();
		
		Assertions.assertTrue(driver.getTitle().equals("Crud"));
	}
	
	
	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
