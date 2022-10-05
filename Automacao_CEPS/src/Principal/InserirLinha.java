package Principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.hslf.record.Sound;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InserirLinha {

	Acessar_Chrome chrome = new Acessar_Chrome();
	public boolean inserirLinha() throws IOException, BiffException {
			

			FileInputStream file = new FileInputStream(new File("C:\\Users\\caique.luz\\Desktop\\Automação de CEPS\\Resultado.xls"));
			
			try{
				
					HSSFWorkbook workbook = new HSSFWorkbook(file);
					HSSFSheet result = workbook.getSheetAt(0);
					String dados = Acessar_Chrome.resultadoFinal.toString();
					int contador = Acessar_Chrome.contadorDeAcesso;
					
					for(int i =0; i < contador; i++) {
					HSSFRow row = result.createRow(1+i);
		            HSSFCell cell = row.createCell(0);
					
		            cell.setCellValue(dados);
		    
					}
		      
		         
		            FileOutputStream stream = new FileOutputStream("C:\\Users\\caique.luz\\Desktop\\Automação de CEPS\\Resultado.xls");
		            workbook.write(stream);
		            stream.close();
		         
		        }catch(Exception e){
		        }
			return false;
			
		
}
}