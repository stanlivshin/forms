package com.usbzoso.forms.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoneCP {

	@Autowired
	DataSource ds;

	public void testBoneCP() throws SQLException {
		Connection connection = ds.getConnection();
		System.out.println(connection); // do something with the connection here..
	}

}
