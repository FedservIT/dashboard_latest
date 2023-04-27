package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/newsfeed")
@RestController
@EnableScheduling
public class DemofirebaseController {
	
	@Autowired
	NewsService news;
	@Autowired
	FinanceService finance;
	@Autowired
	MarketService markets;
	@Autowired
	SportsService sports;
	@Autowired
	WorldNews worldnews;
	@Autowired
	BankingFinancial Bankingdata;
	@Autowired
	CEO_MessageBankingFinancial ceo;
	@Autowired
	NewsServiceTopStories newstopstories;
	@Autowired
	HinduNewsService hinnews;

	

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")  // 8.00 am and 12.00 am
	@PostMapping("/topstories")
	public ResponseEntity<?> addnews() throws Exception {
		return new ResponseEntity<>(news.getOutput(), HttpStatus.OK);
	}

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/finance")
	public ResponseEntity<?> addfinance() throws Exception {
		return new ResponseEntity<>(finance.getOutput(), HttpStatus.OK);
	}

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/markets")
	public ResponseEntity<?> addMarkets() throws Exception {
		return new ResponseEntity<>(markets.getOutput(), HttpStatus.OK);
	}

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/sports")
	public ResponseEntity<?> addSports() throws Exception {
		return new ResponseEntity<>(sports.getOutput(), HttpStatus.OK);
	}

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/worldnews")
	public ResponseEntity<?> worldnews() throws Exception {
		return new ResponseEntity<>(worldnews.getOutput(), HttpStatus.OK);
	}

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/newsscroll")
	public ResponseEntity<?> Banking() throws Exception {
		return new ResponseEntity<>(Bankingdata.getOutput(), HttpStatus.OK);
	}
	
	// @PostMapping("/ceomeseg")
	// public ResponseEntity<?> ceomeseg() throws Exception {
	// 	return new ResponseEntity<>(ceo.getOutput(), HttpStatus.OK);
	// }

	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/topstoriesnewsTOI")
	public ResponseEntity<?> newsservice() throws Exception {
		return new ResponseEntity<>(newstopstories.getOutput(), HttpStatus.OK);
	}
	
	// @Scheduled(cron="0 1 8,12 * * *")
	// @Scheduled(cron="0 0 8,0 * * *")
	@PostMapping("/topstorieshindu")
	public ResponseEntity<?> newsservicehindu() throws Exception {
		return new ResponseEntity<>(hinnews.getOutput(), HttpStatus.OK);
	}
	
}
