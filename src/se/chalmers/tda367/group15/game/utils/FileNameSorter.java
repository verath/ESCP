package se.chalmers.tda367.group15.game.utils;

import java.io.File;
import java.util.Comparator;

public class FileNameSorter implements Comparator<File> {

	@Override
	public int compare(File f1, File f2) {
		return f1.getPath().compareTo(f2.getPath());
	}

}