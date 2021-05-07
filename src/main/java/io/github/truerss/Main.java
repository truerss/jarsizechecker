package io.github.truerss;

import io.github.truerss.config.ConfigurationAndPath;
import io.github.truerss.parsers.AppParser;
import io.github.truerss.structure.DirRepr;
import io.github.truerss.structure.DirTree;
import io.github.truerss.structure.DirTreePrinter;
import io.github.truerss.util.RichEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class Main {

  public static void main(String[] args) {
    var cmd = new AppParser();
    var tmp = cmd.parse(args);
    if (tmp != null) {
      try {
        run(tmp);
      } catch (IOException ex) {
        exit("Failed to parse jar file: " + ex.getMessage());
      }
    }
  }

  private static void run(ConfigurationAndPath configurationAndPath) throws IOException {
    var fileName = configurationAndPath.pathToJar();
    fileCheck(fileName);

    var dirTree = new DirTree();
    var deep = configurationAndPath.configuration().deepLevel();

    try (var zis = new ZipInputStream(new FileInputStream(fileName))) {

      var zipEntry = zis.getNextEntry();

      while (zipEntry != null) {
        var entry = new RichEntry(zipEntry);
        var name = entry.name(deep);
        var size = entry.size(zis);
        var isFile = !zipEntry.isDirectory();

        var repr = new DirRepr(name, size, isFile);
        dirTree.add(repr);

        zipEntry = zis.getNextEntry();
      }
      zis.closeEntry();
    }

    var config = configurationAndPath.configuration();
    var printer = new DirTreePrinter(dirTree, config);
    printer.print(new File(fileName).length());
  }


  private static void fileCheck(String name) {
    // check file permissions
    var file = new File(name);
    if (!file.exists()) {
      exit("File " + name + " does not exist");
    }
    if (file.isDirectory()) {
      exit(name + " is a directory");
    }
    if (!file.canRead()) {
      exit("Can not read the file (add permissions) " + name);
    }
  }

  public static void exit(String msg) {
    System.out.println(msg);
    System.exit(1);
  }
}
