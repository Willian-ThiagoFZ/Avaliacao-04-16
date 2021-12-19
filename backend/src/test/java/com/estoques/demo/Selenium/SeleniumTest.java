package com.estoques.demo.Selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {

    private WebDriver driver;

    private String retornaNomeAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "aFornecedorSeleniumTestes" + numeroInteiroAleatorio_0_a_1000;
    }

    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Willian\\Documents\\Projetos\\Avaliacao\\Avaliacao-04-16\\backend\\src\\test\\java\\com\\estoques\\demo\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }

    /*TESTE PARA ABRIR NAVEGADOR E VERIFICAR SE O SITE ABRE*/

    @Test
    @Order(1)
    public void ListarFornecedores() throws InterruptedException {
        driver.get("http://localhost:8081/");
        Thread.sleep(3000);
        assertTrue("Título da Página difere do esperado", driver.getTitle().contentEquals("Estoques"));
    }

    /*TESTE PARA CADASTRAR UM NOVOO FORNECEDOR */

    @Test
    @Order(2)
    public void RegisterFornecedores() throws InterruptedException {
        driver.get("http://localhost:8081/fornecedores");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div/main/div/div/div/div/div/header/div/button/span")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/div/div/div/div/div/div[1]/div/input")).sendKeys(retornaNomeAleatorio());
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div[3]/button[2]/span")).click();
            Thread.sleep(2000);
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA DELETAR UM FORNCEDOR*/

    @Test
    @Order(3)
    public void DeleteFornecedores() throws InterruptedException {
        driver.get("http://localhost:8081/fornecedores");
        Thread.sleep(3000);
        try{
            driver.findElement(By.xpath("/html/body/div/div/main/div/div/div/div/div/div[1]/table/tbody/tr[1]/td[2]/button[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/button[2]/span")).click();
            Thread.sleep(3000);
        }
        finally {
            driver.quit();
        }
    }

}
