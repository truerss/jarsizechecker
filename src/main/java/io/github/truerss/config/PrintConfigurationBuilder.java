package io.github.truerss.config;

import static io.github.truerss.config.PrintConfiguration.*;

public class PrintConfigurationBuilder {
  private SortOrder order = defaultSortOrder;
  private int deepLevel = defaultDeepLevel;
  private boolean skipFiles = defaultSkipFiles;
  private boolean unFoldSingleNode = defaultUnfoldSingleNode;

  private PrintConfigurationBuilder() { }

  public PrintConfiguration build() {
    return new PrintConfiguration(order, deepLevel, skipFiles, unFoldSingleNode);
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

  public PrintConfigurationBuilder withUnFoldSingleNode(boolean flag) {
    this.unFoldSingleNode = flag;
    return this;
  }
}
