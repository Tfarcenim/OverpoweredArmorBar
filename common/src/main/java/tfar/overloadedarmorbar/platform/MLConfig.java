package tfar.overloadedarmorbar.platform;

import java.util.List;

public interface MLConfig {

    boolean alwaysShowArmorBar();
    boolean showEmptyArmorIcons();
    List<? extends String> colorValues();

}
