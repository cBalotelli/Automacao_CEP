package Principal;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.xalan.xsltc.compiler.sym;
import org.apache.xerces.parsers.NonValidatingConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.read.biff.BiffException;

public class Acessar_Chrome_v2 {

	public static ArrayList<String> getRua = new ArrayList<String>();
	public static ArrayList<String> getBairro = new ArrayList<String>();
	public static ArrayList<String> getLocalidade = new ArrayList<>();
	public static ArrayList<String> getCep = new ArrayList<>();

	public static int contadorDeAcesso = 0;
	LerInputExcel lerLista = new LerInputExcel();
	///criaExcel excel = new criaExcel();

	public void AcessarCorreios() throws BiffException, IOException, InterruptedException {

		WebDriver driver;

		String url = "http://www.buscacep.correios.com.br/sistemas/buscacep/";

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/caique.luz/eclipse-workspace/Bibliotecas/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

		// Loop para pesquisar os CEPS de acordo com o que foi armazenado na arraylist

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

			
			
			//Condição que valida se os dados foram encontrados com sucesso ou não 
			
			
			if (validarSucesso.contains("DADOS ENCONTRADOS COM SUCESSO.")) {
				
				//Captura a Rua\\
				WebElement rua = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[1]"));
				String validarRua = rua.getText();
				getRua.add(validarRua);
				
				//Captura o bairro\\
				WebElement bairro = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[2]"));
				String validarBairro = bairro.getText();
				getBairro.add(validarBairro);
				
				//Captura Localidade\\
				WebElement localidade = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[3]"));
				String validarLocal = localidade.getText();
				getLocalidade.add(validarLocal);
				
				//Captura CEP\\
				WebElement cep = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]"));
				String validarCep = cep.getText();
				getCep.add(validarCep);
				
				//Efetua nova consulta\\
				WebElement novaConsulta = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/a"));
				novaConsulta.click();
				
			} else {
				
				
				System.out.println("CEP INVÁLIDO!");
				
				Pesquisar.click();
			}
			
		}
		
		
	driver.close();
	}
}
