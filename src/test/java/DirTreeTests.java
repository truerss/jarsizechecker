
import io.github.truerss.structure.DirRepr;
import io.github.truerss.structure.DirTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DirTreeTests {

  @Test
  public void test() {
    var tree = new DirTree();
    var topDir = "a";
    var subDir1 = "a/b";
    var subDir2 = "a/c";
    var subDir11 = "a/b/c";
    var subDir12 = "a/b/d";
    var subDir13 = "a/b/e";
    tree.add(new DirRepr(topDir, 0L, false));
    tree.add(new DirRepr(subDir1, 0L, false));
    tree.add(new DirRepr(subDir2, 3L, false));
    tree.add(new DirRepr(subDir11, 10L, false));
    tree.add(new DirRepr(subDir12, 3L, false));
    tree.add(new DirRepr(subDir13, 30L, false));

    var root = tree.getRoot();

    assertEquals(tree.getSize(), 46L);
    assertEquals(root.findChild(subDir1).getDirSize(), 43L);
    assertEquals(root.findChild(subDir2).getDirSize(), 3L);
    assertEquals(root.findChild(subDir13).getDirSize(), 30L);
    assertNull(root.findChild("a/e"));

    assertEquals(tree.getMaxPath(), subDir11);

    assertEquals(root.findChild(subDir1).flatChildrenCount(), 3);
  }

}
