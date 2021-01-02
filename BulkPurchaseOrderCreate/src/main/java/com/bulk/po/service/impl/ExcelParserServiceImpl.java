package com.bulk.po.service.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.bulk.po.service.ExcelParserService;

@Component
public class ExcelParserServiceImpl implements ExcelParserService{
	
	

	@Override
	public <T> T parseExcelFromByteStream(InputStream iostream, Class<T> objClass) {

			try {
			

			T newInstance= objClass.newInstance();

			final DataFormatter formatter = new DataFormatter();
			final XSSFWorkbook workbook = new XSSFWorkbook(iostream);
			final XSSFSheet firstSheet = workbook.getSheetAt(0);
			final Iterator<Row> iterator = firstSheet.iterator();
			final Map<Integer, String> columnHeadingMap = new HashMap<>();
			String currentColumnHeading = "";

			while (iterator.hasNext()) {
				
				final Row currentRow = iterator.next();
				final int rowNum = currentRow.getRowNum();
//				if(rowNum == 0) {
//					continue; //first row contains only file name //rowNum=1
//				}
				 if (rowNum == 0) {

					final Iterator<Cell> cellIterator = currentRow.cellIterator();
					while (cellIterator.hasNext()) {
						final Cell cell = cellIterator.next();
						if (null != cell.getStringCellValue()
								&& !cell.getStringCellValue().replaceAll("\\s+", "").equals("")) {
							columnHeadingMap.put(cell.getColumnIndex(), cell.getStringCellValue());
						}
					}
				}
				else {
					final Iterator<Cell> cellIterator = currentRow.cellIterator();
					while (cellIterator.hasNext()) {
						final Cell cell = cellIterator.next();
						final int columnIndex = cell.getColumnIndex();
						System.out.println("columnIndex ["+columnIndex+"]");
						currentColumnHeading = columnHeadingMap.get(columnIndex);//columnIndex is key. map.get(key) returns the value
						formatter.formatCellValue(cell);
						Field f=null; 
						if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							f= newInstance.getClass().getDeclaredField(currentColumnHeading);//siteProjectsId
							
							System.out.println("value of field"+f.getName());
							f.setAccessible(true);
							f.set(newInstance,formatter.formatCellValue(cell));//Into which class object should I set the value for the field siteProjectsId
							System.out.print("-------"+currentColumnHeading+"-------["+cell+"]");
						}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							f= newInstance.getClass().getDeclaredField(currentColumnHeading);
							f.setAccessible(true);
							System.out.print("-------"+currentColumnHeading+"-------["+formatter.formatCellValue(cell)+"]");
							f.setInt(newInstance, Integer.parseInt(formatter.formatCellValue(cell)));
						}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
							f= newInstance.getClass().getDeclaredField(currentColumnHeading);
							f.setAccessible(true);
							f.set(newInstance, formatter.formatCellValue(cell));
						}
					}
				
					System.out.println("details"+newInstance.toString());
				
				}
			
			}
			return newInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;

	}

}
