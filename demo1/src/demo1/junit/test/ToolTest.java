package demo1.junit.test;

import org.junit.Assert;
import org.junit.Test;

public class ToolTest {
	
	@Test
	public void testGetMax() {
		int max = Tool.getMax();
//		if (max == 19) {
//			System.out.println("×î´óÖµ =  " + max);
//		} else {
//			throw new NullPointerException();
//		}
		Assert.assertSame(15, max);
	}
}
