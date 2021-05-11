package io.github.truerss.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public record RichEntry(ZipEntry entry) {
  public String name(int deep) {
    var arr = nameSplit(deep);
    var currentName = arr[0]; // head
    if (arr.length >= deep) {
      var tmp = String.join("/", arr);
      if (!tmp.contains(".")) { // not a file
        currentName = tmp;
      }
      return currentName;
    }
    return String.join("/", arr); // too deep
  }

  private String[] nameSplit(int deep) {
    var name = entry.getName();
    var arr = name.split("/");
    if (arr.length > deep) {
      return Arrays.copyOfRange(arr, 0, deep);
    }

    return arr;
  }

  public long size(ZipInputStream zis) throws IOException {
    if (entry.getSize() < 0) { // file
      int length;
      int actualSize = 0;
      var buf = new byte[1024];
      while ((length = zis.read(buf)) > 0) {
        actualSize = actualSize + length;
      }
      return actualSize;
    }

    return 0;
  }
}
