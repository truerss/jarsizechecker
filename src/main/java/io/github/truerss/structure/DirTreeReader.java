package io.github.truerss.structure;

import io.github.truerss.util.RichEntry;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class DirTreeReader {
  public static DirTree read(String fileName, int deepLevel) throws IOException {
    var dirTree = new DirTree();

    try (var zis = new ZipInputStream(new FileInputStream(fileName))) {

      var zipEntry = zis.getNextEntry();

      while (zipEntry != null) {
        var entry = new RichEntry(zipEntry);
        var name = entry.name(deepLevel);
        var size = entry.size(zis);
        var isFile = !zipEntry.isDirectory();

        var repr = new DirRepr(name, size, isFile);
        dirTree.add(repr);

        zipEntry = zis.getNextEntry();
      }
      zis.closeEntry();
    }

    return dirTree;
  }
}
