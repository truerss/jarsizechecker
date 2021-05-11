package io.github.truerss.config;

public enum SortOrder {
  ASC,
  DESC;

  public static SortOrder parse(String str) {
    for (var s: SortOrder.values()) {
      if (s.name().equalsIgnoreCase(str)) {
        return s;
      }
    }
    return DESC;
  }
}
