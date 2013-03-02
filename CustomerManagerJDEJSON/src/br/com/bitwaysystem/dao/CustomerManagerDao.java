package br.com.bitwaysystem.dao;

import br.com.bitwaysystem.bean.Endpoint;
import br.com.bitwaysystem.helper.EndpointManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CustomerManagerDao {

	private SQLiteDatabase db;

	public CustomerManagerDao(Context c) {
		EndpointManager clienteHelper = new EndpointManager(c);
		// db = c.openOrCreateDatabase(ClienteHelper.CLIENTEDB,
		// Context.MODE_PRIVATE, null);
		db = clienteHelper.getWritableDatabase();
	}

	// ---retrieves a particular contact---
	public Cursor getURL(Endpoint endpoint) throws SQLException {
		Cursor mCursor = db.query(true, EndpointManager.DATABASE_NAME,
				new String[] { EndpointManager.KEY_ROWID,
						EndpointManager.KEY_URL }, EndpointManager.KEY_ROWID
						+ "=" + endpoint.getId(), null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public long inserir(Endpoint endpoint) {

		ContentValues args = new ContentValues();
		args.put("id", 1);
		args.put("endpoint", endpoint.getEndpointAdress());
		return db.insert(EndpointManager.DATABASE_TABLE, null, args);
	}

	// ---updates a endpoint---
	public boolean updateContact(Endpoint endpoint) {
		ContentValues args = new ContentValues();
		args.put("id", 1);
		args.put("endpoint", endpoint.getEndpointAdress());
		return db.update(EndpointManager.DATABASE_TABLE, args,
				EndpointManager.KEY_ROWID + "=" + endpoint.getId(), null) > 0;
	}

	public void close() {
		if (db != null) {
			db.close();
		}
	}
}