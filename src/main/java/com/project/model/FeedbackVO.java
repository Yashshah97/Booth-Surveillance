package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity


@Table(name="feedback")

public class FeedbackVO
	{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="feedbackId")
		private int feedbackId;
		
		@Column(name="description")
		private String description;
		
		@Column(name="star")
		private String star;
		
		@ManyToOne LoginVO loginVO;
		
		
		
		@Column
		private boolean status=true;
		
		
		
		

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public int getFeedbackId() {
			return feedbackId;
		}

		public void setFeedbackId(int feedbackId) {
			this.feedbackId = feedbackId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStar() {
			return star;
		}

		public void setStar(String star) {
			this.star = star;
		}

		public LoginVO getLoginVO() {
			return loginVO;
		}

		public void setLoginVO(LoginVO loginVO) {
			this.loginVO = loginVO;
		}

		

		
		
		
		
				
	}
