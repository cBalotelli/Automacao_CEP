package Principal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.examples.CreateCell;

import jxl.read.biff.BiffException;

public class criaExcel {
		
		Acessar_Chrome_v2 chrome = new Acessar_Chrome_v2();
		
		
		public static final String fileName = "C:\\Users\\caique.luz\\Desktop\\Automação de CEPS\\Resultado.xls";
		
		public void gravarPlanilha() throws IOException, BiffException {
			
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet saidaCeps = workbook.createSheet("Resultado");
			// Cria a linha do cabeçalho
			Row cabecalho = saidaCeps.createRow((short)0);
			
			// Cria os titulos do cabeçalho
			
			//Definindo o estilo das células//
			
			
			
			// Definindo nomes do cabeçalhos//
			String rua		= "Logradouro/Nome";
			String bairro	= "Bairro/Distrito:";
			String local	= "Localidade/UF";
			String cep 		= "CEP";
			
			int tamanho  = rua.length();
			int tamanho2 = bairro.length();
			int tamanho3 = local.length();
			int tamanho4 = cep.length();
			
			
		
			
			//Definindo o Tamanho das colunas//
			saidaCeps.setColumnWidth(0, tamanho  * 1500);
			saidaCeps.setColumnWidth(1, tamanho2 * 600);
			saidaCeps.setColumnWidth(2, tamanho3 * 950);
			saidaCeps.setColumnWidth(3, tamanho4 * 900);
			
			//Criando as celulas com os nomes do cabeçalho//
			cabecalho.createCell(0).setCellValue(rua);
			cabecalho.createCell(1).setCellValue(bairro);
			cabecalho.createCell(2).setCellValue(local);
			cabecalho.createCell(3).setCellValue(cep);
			
			CellStyle textStyle = workbook.createCellStyle();
			textStyle.setAlignment(CellStyle.ALIGN_CENTER);
			textStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
			
			//Insere os dados da array no excel\\
			
			
			int cont = 0;
			
			for (int i = 0; i < Acessar_Chrome_v2.getRua.size(); i++) {
				
				Row dados = saidaCeps.createRow(i+1);
				
				dados.createCell(0).setCellValue(Acessar_Chrome_v2.getRua.get(i).toString());
				dados.createCell(1).setCellValue(Acessar_Chrome_v2.getBairro.get(i).toString());
				dados.createCell(2).setCellValue(Acessar_Chrome_v2.getLocalidade.get(i).toString());
				dados.createCell(3).setCellValue(Acessar_Chrome_v2.getCep.get(i).toString());
				
			}
			
			
		
			FileOutputStream saida = new FileOutputStream(new File(criaExcel.fileName));
			workbook.write(saida);
			saida.close();
	
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("Arquivo Não criado!");
		
	
		}
}
}
