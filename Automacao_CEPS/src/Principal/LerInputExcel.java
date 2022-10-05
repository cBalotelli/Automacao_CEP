package Principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LerInputExcel {

	public static ArrayList<String> lista = new ArrayList<String>();

	public void lerExcel() throws BiffException, IOException {

		Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\caique.luz\\Desktop\\Automação de CEPS\\Lista de CEPS.xls"));
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		String as2 = "";
		Cell a2 = null;
		for (int i = 1; i < linhas; i++) {
		
			a2 = sheet.getCell(0, i);
			as2 = a2.getContents();
			if (as2 != "") {
				lista.add(as2);
			}

		} 
		// System.out.println(lista);
		workbook.close();

	}
}
