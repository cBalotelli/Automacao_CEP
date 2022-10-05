package Principal;

import java.io.IOException;

import org.apache.poi.xslf.usermodel.ListAutoNumber;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jxl.read.biff.BiffException;

public class Inicio {

	public static void main(String[] args) throws BiffException, IOException, InterruptedException {
		LerInputExcel lerInput = new LerInputExcel();
		lerInput.lerExcel();
	
	
		
		
		
		Acessar_Chrome_v2 chrome = new Acessar_Chrome_v2(); 
		chrome.AcessarCorreios();
	
		
		
			criaExcel cExcel = new criaExcel();
			cExcel.gravarPlanilha();
	
			
		//InserirLinha inserir = new InserirLinha();
		//inserir.inserirLinha();
		
	}

}
