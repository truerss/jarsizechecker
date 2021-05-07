package io.github.truerss.parsers;

import io.github.truerss.config.ConfigurationAndPath;
import io.github.truerss.config.PrintConfiguration;
import io.github.truerss.config.SortOrder;
import org.apache.commons.cli.*;

public class AppParser {
  private final Options currentOptions;
  private static final String jarArg = "jar";
  private static final String orderArg = "order";
  private static final String includeFilesArg = "include";
  private static final String deepArg = "deep";
  private static final String unFoldSingleArg = "unfold";

  private static final String usage = "java -jar jarsizechecker.jar -j /path/to/jar";

  public AppParser() {
    var options = new Options();
    var helpOpt = new Option( "h", "help", false, "print this message" );
    var jarPathOpt = new Option("j", jarArg, true,
        "Path to the jar file");
    jarPathOpt.setRequired(true);
    var orderOpt = new Option("o", orderArg, true,
        "Sort oder, default is DESC");
    orderOpt.setRequired(false);
    var skipFilesOpt = new Option("i", includeFilesArg, false,
        "Display files, default is false");
    skipFilesOpt.setRequired(false);
    var deepOpt =  new Option("d", deepArg, true,
        "Deep tree level, default is 2");
    deepOpt.setRequired(false);
    var foldOpt =  new Option("u", unFoldSingleArg, false,
        "Display trees with single leaf, default is false");
    foldOpt.setRequired(false);
    options
        .addOption(helpOpt)
        .addOption(jarPathOpt)
        .addOption(orderOpt)
        .addOption(deepOpt)
        .addOption(foldOpt)
        .addOption(skipFilesOpt);
    currentOptions = options;
  }

  public ConfigurationAndPath parse(String[] args) {
    var parser = new DefaultParser();
    var sortOrder = SortOrder.DESC;
    var deepLevel = 2;
    var skipFiles = true;
    var unfoldSingle = false;
    var jarPath = "/tmp";
    try {
      var cl = parser.parse(currentOptions, args);

      if (cl.hasOption(jarArg)) {
        jarPath = cl.getOptionValue(jarArg);
      }
      if (cl.hasOption(AppParser.includeFilesArg)) {
        skipFiles = false;
      }
      if (cl.hasOption(deepArg)) {
        var tmp = cl.getOptionValue(deepArg);
        try {
          deepLevel = Integer.parseInt(tmp);
        }
        catch (NumberFormatException ignored)
        {

        }
      }
      if (cl.hasOption(orderArg)) {
        var o = cl.getOptionValue(orderArg);
        for (var s: SortOrder.values()) {
          if (s.name().equalsIgnoreCase(o)) {
            sortOrder = s;
          }
        }
      }
      if (cl.hasOption(unFoldSingleArg)) {
        unfoldSingle = true;
      }

      var conf = new PrintConfiguration(sortOrder, deepLevel, skipFiles, unfoldSingle);
      return new ConfigurationAndPath(jarPath, conf);
    } catch (ParseException ex) {
      var formatter = new HelpFormatter();
      formatter.printHelp(usage, currentOptions);
      return null;
    }
  }
}
