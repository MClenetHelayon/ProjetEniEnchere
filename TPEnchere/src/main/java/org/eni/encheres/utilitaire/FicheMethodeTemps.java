package org.eni.encheres.utilitaire;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class FicheMethodeTemps {
	  public static Date asDate(LocalDate localDate) {
		  return Date.valueOf(LocalDate.of(localDate.getYear(),localDate.getMonth(),localDate.getDayOfMonth()));
	  }
	  public static LocalDate asLocalDate(Date date) {
		  return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  }
	  public static LocalTime asLocalTime(Time time) {
		  return time.toLocalTime();
	  }
	  public static Time asTime(LocalTime localTime) {
		 return Time.valueOf( localTime );
	  }
	  public static LocalDate stringToLocalDate(String date) {
		  return LocalDate.parse(date);
	  }
	  public static LocalTime stringToLocalTime(String time) {
		  return LocalTime.parse(time);
	  }
}
