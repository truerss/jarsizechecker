
import io.github.truerss.config.PrintConfigurationBuilder;
import io.github.truerss.config.SortOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrintConfigurationBuilderTests {

  @Test
  public void configTests() {
    var empty = PrintConfigurationBuilder.empty();
    empty.withDeepLevel(3)
        .withSortOrder(SortOrder.ASC)
        .withSkipFiles(false)
        .withUnFoldSingleNode(true);

    var p = empty.build();

    assertEquals(p.deepLevel(), 3);
    assertEquals(p.order(), SortOrder.ASC);
    assertFalse(p.skipFiles());
    assertTrue(p.unFoldSingleNode());

  }
}
