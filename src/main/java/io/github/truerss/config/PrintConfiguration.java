package io.github.truerss.config;

import io.github.truerss.structure.DirTree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record PrintConfiguration(SortOrder order,
                                 int deepLevel,
                                 boolean skipFiles,
                                 boolean unFoldSingleChildren
                                 ) {

  public List<DirTree.DirNode> apply(List<DirTree.DirNode> xs) {
    Collections.sort(xs, Comparator.comparingLong(DirTree.DirNode::getDirSize));
    if (SortOrder.DESC == order) {
      Collections.reverse(xs);
    }
    if (skipFiles) {
      xs = xs.stream().filter(x -> !x.isFile).collect(Collectors.toList());
    }
    return xs;
  }
}
