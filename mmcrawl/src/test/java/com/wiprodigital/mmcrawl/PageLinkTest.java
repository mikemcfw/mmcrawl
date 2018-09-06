package com.wiprodigital.mmcrawl;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageLinkTest {
	
	private static final String baseURL = "https://wiprodigital.com";

	@Test
	public void shouldCreateValidPageLink() {
		String ref = baseURL + "/dummyRef.html";
		
		PageLink linkRef = new PageLink(baseURL, ref);
		
		assertEquals("invalid base", baseURL, linkRef.getBase());
		assertEquals("invalide reference", ref, linkRef.getPage());
		assertEquals("invalid internal indicator", true, linkRef.isInternalContent());
		assertEquals("invalid static indicator", false, linkRef.isStaticContent());
		assertEquals("invalid response code", 0, linkRef.getResponseCode());
	}
	
	@Test
	public void shouldIdStatics() {
		String ref = baseURL + "/dummyRef.";
		String[] extns = {"bmp", "gif", "jpg", "png", "js", "css"};
		
		for (int i = 0; i < extns.length; i++) {
			PageLink linkRef = new PageLink(baseURL, ref + extns[i]);
			assertEquals("invalid static indicator [" + extns[i] + "]", true, linkRef.isStaticContent());
		}
	}
	
	@Test
	public void shouldIdExternals() {
		String ref = "http://www.elsewhere.com/dummyRef.html";
		
		PageLink linkRef = new PageLink(baseURL, ref);
		
		assertEquals("invalid internal indicator", false, linkRef.isInternalContent());
	}
	
	@Test
	public void shouldSetHttpCode() {
		String ref = baseURL + "/dummyRef.html";
		
		PageLink linkRef = new PageLink(baseURL, ref);
		
		linkRef.setResponseCode(404);
		
		assertEquals("invalid response code", 404, linkRef.getResponseCode());
	}
	
}
