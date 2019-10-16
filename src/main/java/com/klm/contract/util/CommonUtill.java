package com.klm.contract.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.klm.contract.pojo.Contract;

@Component
public class CommonUtill {

	@Value("${filePath}")
	String filePath;

	public void LoadContractDetails() throws Exception {
		File inputfile = new File(filePath);
		try (FileInputStream file = new FileInputStream(inputfile); 
				HSSFWorkbook workbook = new HSSFWorkbook(file);) {
			HSSFSheet sheet = workbook.getSheet("Contract_Report");
			Iterator<Row> rowIterator = sheet.iterator();
			ArrayList<Contract> contractListWithin40Range = new ArrayList<Contract>();
			ArrayList<Contract> contractListWithin20Range = new ArrayList<Contract>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				if (row.getCell(0).getStringCellValue().length() == 0) {
					break;
				}
				Contract contract = new Contract();
				contract.setContractId(row.getCell(0).getStringCellValue());
				contract.setContractEndDate(row.getCell(11).getStringCellValue());
				contract.setContractOwner(row.getCell(31).getStringCellValue());
				contract.setContractOwnerEmail(row.getCell(32).getStringCellValue());
				if (isContractEndDateWithin20Range(contract.getContractEndDate())) {
					contractListWithin20Range.add(contract);
				} else if (isContractEndDateWithin40Range(contract.getContractEndDate())) {
					contractListWithin40Range.add(contract);
				}

			}

			Map<String, ArrayList<Contract>> contractMapWithin20Range = new HashMap<String, ArrayList<Contract>>();
			Map<String, ArrayList<Contract>> contractMapWithin40Range = new HashMap<String, ArrayList<Contract>>();

			for (Contract contract : contractListWithin20Range) {
				if (contractMapWithin20Range.containsKey(contract.getContractOwnerEmail())) {
					contractMapWithin20Range.put(contract.getContractOwnerEmail(),
							addToExsistingList(contractMapWithin20Range, contract));
				} else {
					contractMapWithin20Range.put(contract.getContractOwnerEmail(), addToNewList(contract));
				}
			}

			for (Contract contract : contractListWithin40Range) {
				if (contractMapWithin40Range.containsKey(contract.getContractOwnerEmail())) {
					contractMapWithin40Range.put(contract.getContractOwnerEmail(),
							addToExsistingList(contractMapWithin40Range, contract));
				} else {
					contractMapWithin40Range.put(contract.getContractOwnerEmail(), addToNewList(contract));
				}
			}

			System.out.println(" contractMapWithin20Range : " + contractMapWithin20Range);
			System.out.println(" contractMapWithin40Range : " + contractMapWithin40Range);
		} catch (IOException | EncryptedDocumentException ex) {
			ex.printStackTrace();
		}

	}

	private ArrayList<Contract> addToNewList(Contract contract) {
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		contractList.add(contract);
		return contractList;
	}

	private ArrayList<Contract> addToExsistingList(Map<String, ArrayList<Contract>> contractMap, Contract contract) {
		ArrayList<Contract> contractList = contractMap.get(contract.getContractOwnerEmail());
		contractList.add(contract);
		return contractList;
	}

	private boolean isContractEndDateWithin20Range(String contractEndDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(contractEndDate);
			Calendar currentDateAfter40Days = Calendar.getInstance();
			currentDateAfter40Days.add(Calendar.DATE, 20);
			if (date.after(Calendar.getInstance().getTime()) && date.before(currentDateAfter40Days.getTime())) {
				return true;
			} else {
				return false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean isContractEndDateWithin40Range(String contractEndDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(contractEndDate);
			Calendar currentDateAfter40Days = Calendar.getInstance();
			currentDateAfter40Days.add(Calendar.DATE, 40);
			if (date.after(Calendar.getInstance().getTime()) && date.before(currentDateAfter40Days.getTime())) {
				return true;
			} else {
				return false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

	}

}
