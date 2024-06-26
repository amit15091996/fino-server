package com.fino.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSearchDto {
	private String year;
	private String month;
	private String fromDate;
	private String toDate;

}
