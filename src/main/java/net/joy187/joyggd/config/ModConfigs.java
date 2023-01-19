package net.joy187.joyggd.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ModConfigs {

    public static final ForgeConfigSpec clientConfig;
    public static final ModConfigs.Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientConfig = clientConfigPair.getRight();
        CLIENT = clientConfigPair.getLeft();
    }

    public static class Client {
        public static ForgeConfigSpec.ConfigValue<Float> amplifier;
        public static ForgeConfigSpec.ConfigValue<Float> maxDamage;
        public static ForgeConfigSpec.ConfigValue<Integer> bonusSkillCoolDownTime;
        public static ForgeConfigSpec.ConfigValue<Integer> drawTimesTotal;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("client");
            {
                amplifier = builder
                        .comment("the bonusdamage you kill the monster")
                        .translation("config.client.amplifier")
                        .define("amplifier", 0.05f);

                maxDamage = builder
                        .comment("the max damage of your weapon")
                        .translation("config.client.weapon")
                        .define("maxdamage", 1024f);

                bonusSkillCoolDownTime = builder
                        .comment("the max damage of your weapon")
                        .translation("config.client.bonusSkillCoolDownTime")
                        .define("bonusSkillCoolDownTime", 0);

                drawTimesTotal = builder
                        .comment("the total time of lucky draw")
                        .translation("config.client.drawTimesTotal")
                        .define("drawTimesTotal", 130);
            }
            builder.pop();
        }
    }
}
