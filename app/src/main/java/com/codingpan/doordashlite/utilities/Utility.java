package com.codingpan.doordashlite.utilities;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.codingpan.doordashlite.utilities.MyWebService.LOG_TAG;

public class Utility {

    public static void cacheJson(Context context, String fileName, InputStream inputStream) throws IOException {
		// File version
//		boolean deleteCache = context.deleteFile(fileName);
//		Log.v(LOG_TAG, "myJsonCache" + " deleted " + deleteCache);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] data = new byte[4096];

		int bytesRead = 0;
		while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
			baos.write(data, 0, bytesRead);
		}
		FileOutputStream outputStream = null;
		outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		baos.writeTo(outputStream);
		outputStream.close();
	}

	public static String readJsonCache(Context context, String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inputStream = context.openFileInput(fileName);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString;

				while ((receiveString = bufferedReader.readLine()) != null) {
					sb.append(receiveString);
				}

				inputStream.close();
			}
		} catch (FileNotFoundException e) {
			Log.d(LOG_TAG, "cache not found");
			e.printStackTrace();
		}

    	return sb.toString();
	}
}
