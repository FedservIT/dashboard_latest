package com.fedserv.pulse_dashboard;

import com.fedserv.pulse_dashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PulseDashboardApplication {
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

    public static void main(String[] args) {
        SpringApplication.run(PulseDashboardApplication.class, args);


        NewsService news = new NewsService();



        try {
            news.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FinanceService finance = new FinanceService();
        try {
            finance.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SportsService sports = new SportsService();
        try {
            sports.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WorldNews worldnews = new WorldNews();
        try {
            worldnews.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MarketService markets = new MarketService();
        try {
            markets.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        NewsServiceTopStories toinews = new NewsServiceTopStories();
        try {
            toinews.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
