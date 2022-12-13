package org.eni.encheres.back.utilitaire;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class FicheMethodeTemps {
	
	  public static Date dateToLocalDate(LocalDate localDate) {
		  return Date.valueOf(localDate);
	  }
	  public static LocalDate LocalDateToDate(Date date) {
		  return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  }
	  
	  
	  public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		  return Date.valueOf(localDateTime.toLocalDate());
	  }
	  public static LocalDateTime dateToLocalDateTimeWithResultSet(ResultSet rs,String s) {
		  LocalDateTime vretour = null;
		  try {
			vretour= rs.getTimestamp(s).toLocalDateTime();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return vretour;
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
