package com.example.demo;

import com.example.demo.dao.NasabahDao;
import com.example.demo.dao.TransaksiDao;
import com.example.demo.dto.NasabahTransaksiDto;
import com.example.demo.dto.TabunganReport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	public NasabahDao nasabahDao;

	@Autowired
	public TransaksiDao transaksiDao;

	@Test
	@Ignore
	public void contextLoads() {
	}


	@Test
	@Ignore
	public void testJoin() {
		List<NasabahTransaksiDto> list = new ArrayList<>();
		try {
			list = nasabahDao.listTransaksiNasabah();
			Assert.assertNotNull(list);
		} catch (Exception e) {
		}
	}

    @Test
	@Ignore
    public void testJoinTabungan(){
        List<TabunganReport> list = new ArrayList<>();
        try {
        	Date s = new Date(2019-03-03);
        	Date e = new Date(2019-03-12);
            list = nasabahDao.listTabungan(1L,s,e);
            Assert.assertNotNull(list);
        }catch (Exception e){
        }
    }
}
