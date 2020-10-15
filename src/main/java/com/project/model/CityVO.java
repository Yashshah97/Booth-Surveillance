package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity


@Table(name="city")

public class CityVO
	{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="cityId")
		private int cityId;
		
		@Column(name="cityName")
		private String cityName;
		
		@Column(name="cityDescription")
		private String cityDescription;
		
		@Column
		private boolean status=true;
		
		
		
		

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public int getCityId() {
			return cityId;
		}

		public void setCityId(int cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getCityDescription() {
			return cityDescription;
		}

		public void setCityDescription(String cityDescription) {
			this.cityDescription = cityDescription;
		}
		
		
		
				
	}
