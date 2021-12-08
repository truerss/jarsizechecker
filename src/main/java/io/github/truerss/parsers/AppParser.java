package io.github.truerss.parsers;

import io.github.truerss.config.ConfigurationAndPath;
import io.github.truerss.config.PrintConfigurationBuilder;
import io.github.truerss.util.SortOrderUtils;
import io.github.truerss.util.StringUtils;
import org.apache.commons.cli.*;

import java.util.function.Function;

public class AppParser {
  private final Options currentOptions;
  private static final String jarArg = "jar";
  private static final String orderArg = "order";
  private static final String includeFilesArg = "include";
  private static final String deepArg = "deep";
  private static final String unFoldSingleNodeArg = "unfold";

  private static final String usage = "java -jar jarsizechecker.jar -j /path/to/file.jar";

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
    var foldOpt =  new Option("u", unFoldSingleNodeArg, false,
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
    var builder = PrintConfigurationBuilder.empty();
    var jarPath = "";
    try {
      var cl = parser.parse(currentOptions, args);

      if (cl.hasOption(jarArg)) {
        jarPath = cl.getOptionValue(jarArg);
      }

      parseIfElse(cl, deepArg, (value) -> builder.withDeepLevel(StringUtils.parseInt(value)));
      parseIfElse(cl, orderArg, (value) -> builder.withSortOrder(SortOrderUtils.parse(value)));
      builder
        .withSkipFiles(!cl.hasOption(AppParser.includeFilesArg))
        .withUnFoldSingleNode(cl.hasOption(unFoldSingleNodeArg));

      var conf = builder.build();
      return new ConfigurationAndPath(jarPath, conf);
    } catch (ParseException ex) {
      printHelp();
    }

    return null;
  }

  private void printHelp() {
    var formatter = new HelpFormatter();
    formatter.printHelp(usage, currentOptions);
  }

  private static void parseIfElse(CommandLine cl,
                                  String arg,
                                  Function<String, PrintConfigurationBuilder> func) {
    if (cl.hasOption(arg)) {
      func.apply(cl.getOptionValue(arg));
    }
  }
}
