package io.github.truerss.structure;

public record DirRepr(String name, long size, boolean isFile) {

  public DirRepr incrementSize(long add) {
    return new DirRepr(name, size + add, isFile);
  }

  public static final DirRepr empty = new DirRepr("", 0L, false);
}
