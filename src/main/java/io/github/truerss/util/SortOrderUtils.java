package io.github.truerss.util;

import io.github.truerss.config.SortOrder;

public class SortOrderUtils {
  public static SortOrder parse(String str) {
    for (var s: SortOrder.values()) {
      if (s.name().equalsIgnoreCase(str)) {
        return s;
      }
    }
    return SortOrder.DESC;
  }
}
