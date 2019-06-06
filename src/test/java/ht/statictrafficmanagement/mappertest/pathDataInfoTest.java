package ht.statictrafficmanagement.mappertest;



import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ht.statictrafficmanagement.base.entity.PathDataInfo;
import ht.statictrafficmanagement.base.mapper.PathDataInfoMapper;
import ht.statictrafficmanagement.base.vo.PathDB;

@RunWith(SpringRunner.class)
@SpringBootTest
public class pathDataInfoTest {

	@Autowired
	PathDataInfoMapper mapper;
	
	@Test
	public void aaa() {
		PathDB p = new PathDB();
		Integer[] arr = new Integer[] {1,2,3};
		p.setPathID(2);
		p.setNodeList(Arrays.toString(arr).replaceAll(" ", ""));
		Integer result = mapper.insert(p);
		System.err.println(result);
	}
	

}
