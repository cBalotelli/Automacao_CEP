package Principal;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.read.biff.BiffException;

public class Acessar_Chrome {

	public static ArrayList<String> resultadoFinal = new ArrayList<String>();
	
	public static int contadorDeAcesso = 0;
	LerInputExcel lerLista = new LerInputExcel();
	criaExcel excel = new criaExcel();

	public void AcessarCorreios() throws BiffException, IOException, InterruptedException {

		WebDriver driver;

		String url = "http://www.buscacep.correios.com.br/sistemas/buscacep/";

		System.setProperty("webdriver.chrome.driver","C:/Users/caique.luz/eclipse-workspace/Bibliotecas/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

		// int i = 0;
		for (contadorDeAcesso = 0; contadorDeAcesso < LerInputExcel.lista.size(); contadorDeAcesso++) {
			WebElement campoBusca = driver.findElement(By.xpath("//*[@id=\"Geral\"]/div/div/span[2]/label/input"));
			campoBusca.sendKeys(LerInputExcel.lista.get(contadorDeAcesso));
			Thread.sleep(1000);
			WebElement btnBusca = driver.findElement(By.xpath("//*[@id=\"Geral\"]/div/div/div[6]/input"));
			btnBusca.click();
			Thread.sleep(1000);

			WebElement txtBusca = driver.findElement(By.className("ctrlcontent"));
			WebElement txtBusca2 = driver.findElement(By.className("ctrlcontent"));
			WebElement Pesquisar = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[1]/span/ul/li[1]/a"));
			String validarSucesso = txtBusca.getText();
			String validarErro = txtBusca2.getText();

			if (validarSucesso.contains("DADOS ENCONTRADOS COM SUCESSO.")) {

				WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]"));
				String elementValue = element.getText();

				if (elementValue != "") {

					resultadoFinal.add(elementValue);

				}

				System.out.println(resultadoFinal.get(contadorDeAcesso));

				WebElement novaConsulta = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/a"));
				novaConsulta.click();

			} else if (validarErro.contains("DADOS NAO ENCONTRADOS")) {

				System.out.println("CEP INVÁLIDO!");
				Pesquisar.click();
			}
		}
		InserirLinha inserir = new InserirLinha();
		inserir.inserirLinha();
		
		Thread.sleep(500);
		driver.close();

	}
}
