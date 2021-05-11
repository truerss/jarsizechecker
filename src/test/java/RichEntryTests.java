
import io.github.truerss.util.RichEntry;
import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RichEntryTests {

  @Test
  public void nameTests() {
    var name = "a/b/c/Main.class";
    var entry = new RichEntry(new ZipEntry(name));

    assertEquals(entry.name(2), "a/b");
    assertEquals(entry.name(10), name);
  }

}
