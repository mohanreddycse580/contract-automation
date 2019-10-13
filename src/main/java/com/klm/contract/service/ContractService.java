package com.klm.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klm.contract.util.CommonUtill;

@Service
public class ContractService {

	@Autowired
	private CommonUtill commonUtill;

	public void loadContractDetails() throws Exception{
		commonUtill.LoadContractDetails();
		
	}

}
