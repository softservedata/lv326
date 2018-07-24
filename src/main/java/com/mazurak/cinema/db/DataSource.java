package com.mazurak.cinema.db;

import java.sql.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public final class DataSource {

		private Driver driver;
		private String protocol; 
		private String productName; 
		private String connectionDetails;
		private String userName;
		private String password;
}
