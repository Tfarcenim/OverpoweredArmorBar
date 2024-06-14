package tfar.overpoweredarmorbar;


import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import tfar.overloadedarmorbar.platform.MLConfig;

import java.util.List;

public class Configs implements MLConfig {

    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @Override
    public boolean alwaysShowArmorBar() {
        return Client.alwaysShowArmorBar.get();
    }

    @Override
    public boolean showEmptyArmorIcons() {
        return Client.showEmptyArmorIcons.get();
    }

    @Override
    public List<? extends String> colorValues() {
        return Client.colorValues.get();
    }


    public static class Client {
        public static ForgeConfigSpec.BooleanValue alwaysShowArmorBar;
        public static ForgeConfigSpec.BooleanValue showEmptyArmorIcons;
        public static ForgeConfigSpec.ConfigValue<List<? extends String>> colorValues;

        Client(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            colorValues = builder
                     .comment("Colors must be specified in #RRGGBB format")
                    .translation("text.overloadedarmorbar.config.colorvalue")
                   .defineList("color values", Lists.newArrayList("#FFFFFF", "#FF5500", "#FFC747", "#27FFE3", "#00FF00", "#7F00FF"), String.class::isInstance);
            alwaysShowArmorBar = builder
                    .comment("Always show armor bar even if empty?")
                    .translation("text.overloadedarmorbar.config.alwaysshowarmorbar")
                    .define("Always show bar", false);
            showEmptyArmorIcons = builder
                    .comment("Show empty armor icons?")
                    .translation("text.overloadedarmorbar.config.showemptyarmoricons")
                    .define("Show empty icons", false);
            builder.pop();
        }
    }
}