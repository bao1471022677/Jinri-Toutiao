package com.wangguansheng.cms.service.impl;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangguansheng.cms.domain.Links;
import com.wangguansheng.cms.service.LinksService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class LinksServiceImplTest {

	@Resource
	private LinksService linksService;
	
	@Test
	public void testInsert() {
		Links links = new Links();
		links.setUrl("https://www.toutiao.com/");
		links.setText("Í·Ìõ");
		linksService.insert(links);
	}

	@Test
	public void testSelects() {
		
	}

}
