package io.github.truerss.util;

import java.nio.file.FileSystems;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class StringUtils {

  public static final String delimiter = FileSystems.getDefault().getSeparator();

  public static String humanReadableByteCountSI(long bytes) {
    if (-1000 < bytes && bytes < 1000) {
      return bytes + " B";
    }
    CharacterIterator ci = new StringCharacterIterator("kMGTPE");
    while (bytes <= -999_950 || bytes >= 999_950) {
      bytes /= 1000;
      ci.next();
    }
    return String.format("%.1f %cB", bytes / 1000.0, ci.current());
  }

  public static int parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException ignored) {
      return -1; // checked on top level
    }
  }
}
