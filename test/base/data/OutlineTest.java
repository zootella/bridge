package base.data;



import org.junit.Test;

import pipe.main.Main;


import base.data.Data;
import base.data.Outline;
import base.exception.MessageException;

import static org.junit.Assert.*;

public class OutlineTest {

	@Test
	public void test() throws Exception {
		
		
		String s;

		s = "";
		s += "a:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "\r\n";
		test(s);

		s = "";
		s += "a:\r\n";
		s += "b:\r\n"; // this is bad because b can't be on the same level
		s += "\r\n";
		try {
			test(s);
			fail();
		} catch (MessageException e) {}

		s = "";
		s += "a:hello\r\n";
		s += " b:a[b\r\n"; // this is bad because [ needs to be escaped to [[
		s += "\r\n";
		try {
			test(s);
			fail();
		} catch (MessageException e) {}
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += " c:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += " d:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "  d:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "   d:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "   d:\r\n";
		s += " e:\r\n";
		s += "\r\n";
		test(s);
		
		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "   d:\r\n";
		s += "  e:\r\n";
		s += "\r\n";
		test(s);

		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "   d:\r\n";
		s += "   e:\r\n";
		s += "\r\n";
		test(s);

		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += "  c:\r\n";
		s += "   d:\r\n";
		s += "    e:\r\n";
		s += "\r\n";
		test(s);


		s = "";
		s += "a:\r\n";
		s += " b:\r\n";
		s += " c:\r\n";
		s += "  d:\r\n";
		s += "  e:\r\n";
		s += "   f:\r\n";
		s += "   g:\r\n";
		s += "    h:\r\n";
		s += "    i:\r\n";
		s += "     j:\r\n";
		s += "     k:\r\n";
		s += "  l:\r\n";
		s += "   m:\r\n";
		s += "   n:\r\n";
		s += " o:\r\n";
		s += "  p:\r\n";
		s += "   q:\r\n";
		s += " r:\r\n";
		s += " s:\r\n";
		s += " t:\r\n";
		s += " u:\r\n";
		s += "  v:\r\n";
		s += "   w:\r\n";
		s += "   x:\r\n";
		s += "  y:\r\n";
		s += " z:\r\n";
		s += "\r\n";
		test(s);
		
		// see how long that one is, in data and in text
		
		Data asText = new Data(s);
		int sizeAsText = asText.size();
		Outline o = Outline.fromText(asText);
		int sizeAsData = o.data().size();
		System.out.println(sizeAsText + " " + sizeAsData); // 165 to 104
	}

	public void test(String s) throws Exception {
			
		// text > outline > data > outline > text
		Outline o = Outline.fromText(new Data(s)); // text to outline
		System.out.println(o.toString());
		Data d = o.data(); // outline to data
		Data d1 = d.copy();
		Outline o2 = new Outline(d); // data to outline
		assertFalse(d.hasData()); // some data left over
		String s2 = o2.toString(); // outline to text
		Data d2 = o2.data(); // outline to data
		assertTrue(d1.equals(d2)); // corrupted
	}
}
