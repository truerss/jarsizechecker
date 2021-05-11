
import io.github.truerss.util.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTests {

  @Test
  public void parseIntTests() {
    assertEquals(StringUtils.parseInt("123"), 123);
    assertEquals(StringUtils.parseInt("0"), 0);
    assertEquals(StringUtils.parseInt("asd"), -1);
    assertEquals(StringUtils.parseInt("-10"), -10);
  }

}
