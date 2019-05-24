package ht.statictrafficmanagement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ipTest {

	@Test
	public void aaa() {
		List<Byte> ipList= new ArrayList<Byte>();
		Byte[] result;
		String ip = "1";
		char temp = (char)Integer.parseInt(ip);
		System.out.println(temp);
	}

}
