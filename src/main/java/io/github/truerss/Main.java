package io.github.truerss;

import io.github.truerss.config.ConfigurationAndPath;
import io.github.truerss.config.PrintConfiguration;
import io.github.truerss.parsers.AppParser;
import io.github.truerss.structure.DirTreePrinter;
import io.github.truerss.structure.DirTreeReader;

import java.io.File;
import java.io.IOException;

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
    validateConfiguration(configurationAndPath.configuration());
    fileCheck(fileName);

    var dirTree = DirTreeReader.read(fileName, configurationAndPath.configuration().deepLevel());
    var config = configurationAndPath.configuration();
    var printer = new DirTreePrinter(dirTree, config);
    printer.print(new File(fileName).length());
  }

  private static void validateConfiguration(PrintConfiguration configuration) {
    if (configuration.deepLevel() <= 0) {
      exit("Invalid --deep option, positive number is required");
    }
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
