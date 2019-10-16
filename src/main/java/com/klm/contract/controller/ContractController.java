package com.klm.contract.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klm.contract.service.ContractService;

@RestController
public class ContractController {

	@Autowired
	private ContractService contractService;

	// @Scheduled(cron = "${cronExpression}")
	@RequestMapping("/contractExpiredTrigger")
	public String triggerContractExpired() throws Exception {
		contractService.loadContractDetails();
		return "Successfully Completed Job at " + LocalDateTime.now().toString();
	}

}
