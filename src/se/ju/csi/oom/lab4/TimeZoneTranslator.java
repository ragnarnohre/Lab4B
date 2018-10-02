package se.ju.csi.oom.lab4;

import java.util.Arrays;
import java.util.HashSet;


public class TimeZoneTranslator {

	static DateTime shiftTimeZone(DateTime inputDate, int fromOffset, int toOffset) {
		
		
		int year = inputDate.getYear();
		int month = inputDate.getMonth();
		int day = inputDate.getDay();
		int hour = inputDate.getHour();
		
		hour = hour - fromOffset + toOffset;
		
		if (hour>=24) {
			hour -= 24;
			day += 1;
		}
		if (hour<0) {
			hour += 24;
			day -= 1;
		}
		
		if (day==32) {
			day = 1;
			month += 1;
		}
		if (day==0) {
			day = 31;
			month -= 1;
		}
		
		if (month==13) {
			month = 1;
			year += 1;
		}
		if (month==0) {
			month = 12;
			year -= 1;
		}
		
		
		DateTime targetDateTime = new DateTime(year,month,day, hour, inputDate.getMinute(),inputDate.getSecond());
		
		return targetDateTime;
	}
	
	static Event shiftEventTimeZone(Event inputEvent, TimeZone fromZone, TimeZone toZone) {
		DateTime shiftedStart = shiftTimeZone(inputEvent.getStartDate(), fromZone.getOffset(), toZone.getOffset());
		DateTime shiftedEnd = shiftTimeZone(inputEvent.getEndDate(), fromZone.getOffset(), toZone.getOffset());
		Event targetEvent = new Event(inputEvent.getLabel(), shiftedStart, shiftedEnd, inputEvent.getAttendees(), inputEvent.getLocation());
		return targetEvent;
	}
	
	public static void main(String [ ] args) {
		DateTime LectureStart = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime LectureEnd = new DateTime(2018, 8, 27, 9, 45, 0);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218",57.7785672,14.1614833,20.0);
		
		Event firstOomLecture = new Event("OOM 2018 Lecture 1",
				LectureStart,
				LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)),
				HC218);
		
		System.out.println(String.format("============\nOriginal event\n============\n%s", firstOomLecture.toString()));
		System.out.println();
		System.out.println(String.format("========================\nEvent shifted to Boston time\n========================\n%s", shiftEventTimeZone(firstOomLecture, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.US_EASTERN).toString()));
	}
}
