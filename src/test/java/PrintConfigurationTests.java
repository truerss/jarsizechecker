
import io.github.truerss.config.PrintConfigurationBuilder;
import io.github.truerss.config.SortOrder;
import io.github.truerss.structure.DirRepr;
import io.github.truerss.structure.DirTree;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PrintConfigurationTests {

  @Test
  public void applyTests() {
    var xs = List.of(
        gen(100, true),
        gen(1, true),
        gen(1000),
        gen(10)
    );

    var b = PrintConfigurationBuilder.empty()
        .withSortOrder(SortOrder.ASC)
        .withSkipFiles(false);

    var r1 = b.build().apply(xs);
    assertIterableEquals(fetch(r1), List.of(1L, 10L, 100L, 1000L));

    var r2 = b.withSkipFiles(true).build().apply(xs);
    assertIterableEquals(fetch(r2), List.of(10L, 1000L));

    var r3 = b.withSkipFiles(false)
        .withSortOrder(SortOrder.DESC)
        .build().apply(xs);
    assertIterableEquals(fetch(r3), List.of(1000L, 100L, 10L, 1L));
  }

  private static List<Long> fetch(List<DirTree.DirNode> tmp) {
    return tmp.stream().map(DirTree.DirNode::getDirSize).collect(Collectors.toList());
  }

  private static DirTree.DirNode gen(long size) {
    return gen(size, false);
  }

  private static DirTree.DirNode gen(long size, boolean isFile) {
    var r = new DirRepr("name-" + size, size, isFile);
    return new DirTree.DirNode(r.name(), r);
  }
}
