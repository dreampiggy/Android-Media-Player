package com.androidhive.musicplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Environment;

public class SongsManager {
	// SDCard Path
	final String MEDIA_PATH = new String(getSDPath() + "/Download");//暂时写死路径在SD卡或者自带存储的/Download里面，只认MP3格式
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	// Constructor
	public SongsManager(){
		
	}

    public String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断外置SD卡是否存在
        if(sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取外置SD卡根目录
        }
        else{
            sdDir = Environment.getRootDirectory();//获取内置SD卡根目录
        }
        return sdDir.toString();
    }

	/**
	 * Function to read all mp3 files from sdcard
	 * and store the details in ArrayList
	 * */
	public ArrayList<HashMap<String, String>> getPlayList() {
		File home = new File(MEDIA_PATH);

        System.out.println(home);

		if (home.listFiles(new FileExtensionFilter()).length > 0) {
			for (File file : home.listFiles(new FileExtensionFilter())) {
				HashMap<String, String> song = new HashMap<String, String>();
				song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
				song.put("songPath", file.getPath());
				
				// Adding each song to SongList
				songsList.add(song);
			}
		}
        else{
            HashMap<String, String> song = new HashMap<String, String>();
            song.put("songTitle","现在你没有下载歌曲噢");
            song.put("songPath","/");
            songsList.add(song);
        }
		// return songs list array
		return songsList;
	}
	
	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
