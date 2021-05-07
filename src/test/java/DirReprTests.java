import io.github.truerss.structure.DirRepr;
import io.github.truerss.structure.DirTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DirReprTests {

  @Test
  public void addSizeTests() {
    var repr = new DirRepr("test", 10L, false);
    var repr1 = repr.incrementSize(3L);

    assertEquals(repr.isFile(), repr1.isFile());
    assertEquals(repr.name(), repr1.name());
    assertEquals(repr.size(), 10L);
    assertEquals(repr1.size(), 13L);

  }

}
