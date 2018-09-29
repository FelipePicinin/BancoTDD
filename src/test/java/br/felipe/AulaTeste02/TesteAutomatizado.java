package br.felipe.AulaTeste02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomatizado {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "/Users/Picinin-Vaio/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://192.168.4.103:8080/correntista/novo");
		
		
		WebElement campoDeTexto = driver.findElement(By.name("nome"));
		campoDeTexto.sendKeys("Felipe");
				
		WebElement campoDeTexto2 = driver.findElement(By.name("email"));
		campoDeTexto2.sendKeys("felipepicinin10@gmail.com");
				
		WebElement botaoSalvar = driver.findElement(By.id("button1"));
        botaoSalvar.click();
        
        
	}

}
