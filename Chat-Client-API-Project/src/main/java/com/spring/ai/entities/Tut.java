package com.spring.ai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor	
@NoArgsConstructor
@ToString
public class Tut {
	
	private String title;
	private String content;
	private String createdYear;
	private String createAt;

}
