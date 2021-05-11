package io.github.truerss.structure;

public record DirRepr(String name, long size, boolean isFile) {

  public DirRepr withNewSize(long newSize) {
    return new DirRepr(name, size + newSize, isFile);
  }

  public static final DirRepr empty = new DirRepr("", 0L, false);
}
