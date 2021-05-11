package io.github.truerss.structure;

import io.github.truerss.config.PrintConfiguration;

import static io.github.truerss.util.StringUtils.humanReadableByteCountSI;

public class DirTreePrinter {
  private final DirTree dirTree;
  private final int maxLength;
  private final PrintConfiguration conf;
  private static final String space = " ";
  private static final double lengthMultiplier = 1.5;
  private static final int leftMultiplier = 2;

  public DirTreePrinter(DirTree dirTree, PrintConfiguration conf) {
    this.dirTree = dirTree;
    this.conf = conf;
    maxLength = dirTree.getMaxPath().length();
  }

  public void print(long fileSize) {
    var root = dirTree.getRoot();
    print(root, "", -1);
    var rootSize = dirTree.getSize();

    System.out.println(
        System.lineSeparator() +
        "Uncompressed size: " +
        humanReadableByteCountSI(rootSize) +
        System.lineSeparator() +
        "Compressed size: " +
        humanReadableByteCountSI(fileSize)
    );
  }

  private void print(DirTree.DirNode dirNode, String parent, int leftPadding) {
    if (dirNode == null) {
      return;
    }

    var tmp = parent;
    if (!dirNode.name.isEmpty()) {
      var name = parent + "/" + dirNode.name;
      var size = dirNode.getDirSize();
      var readableSize = humanReadableByteCountSI(size);
      var lp = leftPadding*leftMultiplier;
      var left = space.repeat(lp);
      var n = resize(maxLength, name.length(), leftPadding);
      var between = space.repeat(n);
      System.out.println(left + name + between + readableSize);
      tmp = name;
    }

    var xs = conf.apply(dirNode.getChildren());

    // unfold single node
    if (!(!conf.unFoldSingleChildren() && dirNode.flatChildrenCount() == 1)) {
      for (var c : xs) {
        print(c, tmp, leftPadding + 1);
      }
    }
  }

  private static int resize(int maxLength, int nameLength, int leftPadding) {
    var tmp = maxLength*lengthMultiplier - nameLength + leftPadding;
    if (tmp < 0) {
      tmp = 3;
    }
    return (int) tmp;
  }
}
