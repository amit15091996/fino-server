package com.fino.helpers;

public class AuthorizationHelpers {
	
	public static final String ADMIN_AUTH="hasAnyAuthority('ADMIN')";
	public static final String MANAGER_AUTH="hasAnyAuthority('ADMIN','MANAGER')";
	public static final String USER_AUTH="hasAnyAuthority('ADMIN','MANAGER','USER')";
	public static final String CLIENT_AUTH="hasAnyAuthority('ADMIN','MANAGER','CLIENT')";

}


//@PreAuthorize("hasAnyAuthority('permission1','permission2')")