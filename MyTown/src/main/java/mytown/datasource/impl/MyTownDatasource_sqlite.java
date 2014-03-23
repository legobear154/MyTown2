package mytown.datasource.impl;

import java.sql.DriverManager;

import mytown.Constants;
import mytown.datasource.types.MyTownDatasource_SQL;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

// TODO Test
// TODO Add comments
// TODO Profit?

/**
 * SQLite Datasource connector
 * 
 * @author Joe Goett
 */
public class MyTownDatasource_sqlite extends MyTownDatasource_SQL {
	private String dbPath;

	@Override
	public boolean connect() throws Exception {
		if (conn != null)
			return true;

		Class.forName("org.sqlite.JDBC");

		conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		if (conn == null) {
			return false; // TODO Log error?
		}

		autoIncrement = ""; // SQLite auto increment is a little different than MySQL

		// Update DB
		setUpdates();
		doUpdates();

		return true;
	}

	@Override
	protected void doConfig(Configuration config) {
		super.doConfig(config);

		Property prop;

		prop = config.get(configCat, "Path", Constants.CONFIG_FOLDER + "data.db");
		prop.comment = "The database file path. Used by SQLite";
		dbPath = prop.getString();

		// TODO Finish Config
	}
}