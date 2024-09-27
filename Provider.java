package com.database;

public interface Provider {
	/***
	 * SERVER CONNECTION PARAMETER'S HERE
	 */
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String DATABASE_NAME = "time_table_gen";
	public static String HOST_NAME = "jdbc:mysql://localhost/";
	public static String SERVER_USER_NAME = "root";
	public static String SERVER_PASSWORD = "root";
        public static String NEW_CONNECTON_STRING = HOST_NAME+DATABASE_NAME+"user="+SERVER_USER_NAME+"&password="+SERVER_PASSWORD;
	
}
