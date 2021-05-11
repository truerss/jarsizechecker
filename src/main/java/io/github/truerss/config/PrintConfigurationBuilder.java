package io.github.truerss.config;

public class PrintConfigurationBuilder {
  private static SortOrder defaultSortOrder = SortOrder.DESC;
  private static int defaultDeepLevel = 2;
  private static boolean defaultSkipFiles = true;
  private static boolean defaultUnfoldSingleChildren = false;

  private SortOrder order = defaultSortOrder;
  private int deepLevel = defaultDeepLevel;
  private boolean skipFiles = defaultSkipFiles;
  private boolean unFoldSingleChildren = defaultUnfoldSingleChildren;

  private PrintConfigurationBuilder() {

  }

  public PrintConfiguration build() {
    return new PrintConfiguration(order, deepLevel, skipFiles, unFoldSingleChildren);
  }

  public static PrintConfigurationBuilder empty() {
    return new PrintConfigurationBuilder();
  }

  public PrintConfigurationBuilder withSortOrder(SortOrder sortOrder) {
    this.order = sortOrder;
    return this;
  }

  public PrintConfigurationBuilder withDeepLevel(int dl) {
    this.deepLevel = dl;
    return this;
  }

  public PrintConfigurationBuilder withSkipFiles(boolean flag) {
    this.skipFiles = flag;
    return this;
  }

  public PrintConfigurationBuilder withUnFoldSingleChildren(boolean flag) {
    this.unFoldSingleChildren = flag;
    return this;
  }


}
