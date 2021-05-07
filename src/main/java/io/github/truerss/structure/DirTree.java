package io.github.truerss.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DirTree {
  private final DirNode root;
  private String maxPath = "";

  public DirTree() {
    var tmp = DirRepr.empty;
    root = new DirNode(tmp.name(), DirRepr.empty);
  }

  public void add(DirRepr repr) {
    var str = repr.name();
    if (str.length() > maxPath.length()) {
      maxPath = str;
    }

    var current = root;
    var tokenizer = new StringTokenizer(str, "/");
    while (tokenizer.hasMoreElements()) {
      str = (String) tokenizer.nextElement();
      var child = current.getChild(str);
      if (child == null) {
        current.children.add(new DirNode(str, repr));
        child = current.getChild(str);
      } else {
        child.addSize(repr.size());
      }
      current = child;
    }
  }

  public DirNode getRoot() {
    return root;
  }

  public String getMaxPath() {
    return maxPath;
  }

  public long getSize() {
    return root
        .children
        .stream()
        .mapToLong(DirNode::getDirSize)
        .sum();
  }

  public static class DirNode {
    public final String name;
    private DirRepr repr;
    public final boolean isFile;

    private List<DirNode> children = new ArrayList<>();

    public DirNode(String name, DirRepr repr) {
      this.name = name;
      this.repr = repr;
      this.isFile = repr.isFile();
    }

    public void addSize(long add) {
      this.repr = this.repr.incrementSize(add);
    }

    public List<DirNode> getChildren() {
      return children;
    }

    public int flatChildrenCount() {
      var count = 0;

      if (children.isEmpty()) {
        return count;
      }

      for (var c : children) {
        count = count + 1 + c.flatChildrenCount();
      }
      return count;
    }

    public DirNode getChild(String data) {
      return children
          .stream()
          .filter(x -> x.name.equals(data))
          .findFirst()
          .orElse(null);
    }

    public DirNode findChild(String str) {
      return findChild(str, name);
    }

    private DirNode findChild(String str, String parentName) {
      var nextName = name;

      if (!parentName.isEmpty()) { // root
        nextName = parentName + "/" + name;
      }

      if (str.equals(nextName)) {
        return this;
      }

      for (var c: children) {
        var r = c.findChild(str, nextName);
        if (r != null) {
          return r;
        }
      }
      return null;
    }

    public long getDirSize() {
      return repr.size();
    }
  }
}
