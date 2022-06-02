package com.bdappmaniac.bdapp.model;

public class ModelChildHolidayItems {
   private String date;
   private String day;
   private String holiday;

   public ModelChildHolidayItems(String date, String day, String holiday) {
      this.date = date;
      this.day = day;
      this.holiday = holiday;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getDay() {
      return day;
   }

   public void setDay(String day) {
      this.day = day;
   }

   public String getHoliday() {
      return holiday;
   }

   public void setHoliday(String holiday) {
      this.holiday = holiday;
   }
}
