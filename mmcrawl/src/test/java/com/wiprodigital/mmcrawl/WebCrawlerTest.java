package com.wiprodigital.mmcrawl;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class WebCrawlerTest {

	private static String baseURL = "src/test/resources/testpage.html";
	
	@Test
	public void shouldConstruct() {
		@SuppressWarnings("unused")
		WebCrawler crawler = new WebCrawler(baseURL);
	}
	
	@Test
	public void shouldParseValid() throws IOException {
		String expected = "[PageLink [base=src/test/resources/testpage.html, reference=src/test/resources/testpage.html, internalContent=true, staticContent=false, responseCode=0], PageLink [base=src/test/resources/testpage.html, reference=https://www.google.co.uk/, internalContent=false, staticContent=false, responseCode=0], PageLink [base=src/test/resources/testpage.html, reference=https://www.google.co.uk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png, internalContent=false, staticContent=true, responseCode=0]]";
				
		WebCrawler crawler = new WebCrawler(baseURL);
		
		crawler.crawl();
		
		List<PageLink> results = crawler.getParseResults();
		
		assertEquals("invalid parse results", expected, results.toString());
		
	}
	
	
	@Test (expected = IOException.class)
	public void shouldThrowExcOnInvalidInput() throws IOException{
		WebCrawler crawler = new WebCrawler("invalidinput");
		crawler.crawl();	
	}
}
