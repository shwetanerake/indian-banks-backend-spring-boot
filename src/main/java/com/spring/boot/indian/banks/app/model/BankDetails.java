package com.spring.boot.indian.banks.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Immutable
@Entity
@Table(name = "bank_branches")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
	
	private long count;

	@Column(name = "bank_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("bank_id")
	private long bankId;

	@Column(name = "bank_name")
	@JsonProperty("bank_name")
	private String name;

	@Column(name = "ifsc")
	@JsonProperty("ifsc")
	private String ifsc;

	@Column(name = "branch")
	@JsonProperty("branch")
	private String branch;

	@Column(name = "address")
	@JsonProperty("address")
	private String address;

	@Column(name = "city")
	@JsonProperty("city")
	private String city;

	@Column(name = "district")
	@JsonProperty("district")
	private String district;

	@Column(name = "state")
	@JsonProperty("state")
	private String state;
	
	/*public BankDetails() {
		
	}*/
	
	public BankDetails(Object[] columns) {
		// TODO Auto-generated constructor stub
		 this.count = (columns[0] != null)?((java.math.BigInteger)columns[0]).longValue():0;
         this.ifsc = (String) columns[1];
         this.bankId = ((java.math.BigInteger) columns[2]).longValue();
         this.branch = (String) columns[3];
         this.address = (String) columns[4];
         this.city = (String) columns[5];
         this.district = (String) columns[6];
         this.state = (String) columns[7];
         this.name = (String) columns[8];
	}

	
	@Override
	public String toString() {
		return "BankDetails [count=" + count + ", bankId=" + bankId + ", name=" + name + ", ifsc=" + ifsc + ", branch="
				+ branch + ", address=" + address + ", city=" + city + ", district=" + district + ", state=" + state
				+ "]";
	}

	@Transient
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
