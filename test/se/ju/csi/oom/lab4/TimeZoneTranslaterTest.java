package se.ju.csi.oom.lab4;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslaterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShiftTimeZone() {
		
		DateTime now = new DateTime(2018,10,1,14,11,0);
		DateTime nowPlus1 = TimeZoneTranslator.shiftTimeZone(now, 0, 1);
		DateTime nowPlus24 = TimeZoneTranslator.shiftTimeZone(now, 0, 24);

		
		assertEquals("time shift 0h", "2018-10-01 14:11:00", now.toString());
		assertEquals("time shift 1h", "2018-10-01 15:11:00", nowPlus1.toString());
		assertEquals("time shift 24h", "2018-10-02 14:11:00", nowPlus24.toString());

		
	}

	@Test
	public void testShiftEventTimeZone() {
		assert( true );
	}

}
