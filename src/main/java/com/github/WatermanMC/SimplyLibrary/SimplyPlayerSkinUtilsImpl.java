package com.github.WatermanMC.SimplyLibrary;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.github.WatermanMC.SimplyLibrary.api.SimplyPlayerSkinUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class SimplyPlayerSkinUtilsImpl implements SimplyPlayerSkinUtils {
    private final String defaultSkinVal = "ewogICJ0aW1lc3RhbXAiIDogMTY2NjMwMjkxNzkzOCwKICAicHJvZmlsZUlkIiA6ICJjOWRlZTM4MDUzYjg0YzI5YjZlZjA5YjJlMDM5OTc0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTQVJfRGVjZW1iZXI1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMxZjQ3N2ViMWE3YmVlZTYzMWMyY2E2NGQwNmY4ZjY4ZmE5M2EzMzg2ZDA0NDUyYWIyN2Y0M2FjZGYxYjYwY2IiCiAgICB9CiAgfQp9";
    private final String defaultSkinSig = "tTCtASRIyuzlNUgSoXgUr6arxABhCR4EQ9+eHaUoO8bADljmUFoQfb6oba8zqe2gIa2mnu5KQaOPQCxcTDjgNv9aIL2smINKxy/60VE4Mgnrh5ntH+mGuDi00V3Bk2CsObFZXz1vgk2UxdQUQ41eVQYm2xBrXFEbXMSoTafWGv0FMTPFpGxGRdduTe3QTEie3GcfAMHCn/9xMMmUxZZ6UVZ+mDe8ARt9/cmK+GmqT8m3kmrz/vq+i29KV4tWvJqsKIVAXm97jVPH9XxVR3tYlheimQSFNrCU8SzNPum/ZhxNAf5Uw90+/K0eaJE59y8tS7KDV5DHrRrHHXb/ywGGklSri1YjFm9AEBk6BeH8Y3Ot/e+zfQbF3rOny2DkBAm/v28FooYd25gXB4MjUFNPj3KdveQh7DpRAvnkmBZMqJCO+Z9fdY4Dw+jmqjII88r6mukWAODvXed/x8bvv55zzNOAxtqtwBTWHIdqWFr/7pMZF26RY1Tluw+pAWGWaKMHtqlGzyOLGMxMKwXqtLNEpIYw52ETwGKaWh8h34cOoI8dhpjfjym4UOihMmazK9LC0EUEHuBlgy5b/Ae71+6UsLNIX8bJwIvN16sP6wpSTNbV6htWoS7/ehvoxdKhI6XEUqWgEoAwmquClPfWiveCV057reoKeVHB9RdTl0sW+HM=";

    @Override
    public String getPlayerSkinValue(@NotNull Player target) {
        try {
            PlayerProfile profile = target.getPlayerProfile();
            return profile.getProperties().stream()
                    .filter(prop -> prop.getName().equalsIgnoreCase("textures"))
                    .findFirst()
                    .map(ProfileProperty::getValue)
                    .orElse(defaultSkinVal);
        } catch (Exception e) {
            return defaultSkinVal;
        }
    }

    @Override
    public String getPlayerSkinSignature(@NotNull Player target) {
        try {
            PlayerProfile profile = target.getPlayerProfile();
            return profile.getProperties().stream()
                    .filter(prop -> prop.getName().equalsIgnoreCase("textures"))
                    .findFirst()
                    .map(ProfileProperty::getSignature)
                    .orElse(defaultSkinSig);
        } catch (Exception e) {
            return defaultSkinSig;
        }
    }

    @Override
    public void setPlayerSkin(@NotNull Plugin plugin,
                              @NotNull Player target,
                              @NotNull String signature,
                              @NotNull String value) {
        PlayerProfile profile = target.getPlayerProfile();
        profile.removeProperty("textures");

        try {
            profile.setProperty(new ProfileProperty("textures", value, signature));
            target.setPlayerProfile(profile);
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to set player skin for player '" + target.getName() + "':");
            plugin.getLogger().severe("Skin value: " + value);
            plugin.getLogger().severe("Skin Signature: " + signature);
            plugin.getLogger().severe("Using default Steve skin instead");

            profile.setProperty(new ProfileProperty("textures", defaultSkinVal, defaultSkinSig));
            target.setPlayerProfile(profile);
        }
        refreshPlayer(plugin, target);
    }

    private void refreshPlayer(@NotNull Plugin plugin, @NotNull Player target) {
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            viewer.hidePlayer(plugin, target);
            viewer.showPlayer(plugin, target);
        }
    }

    @Override
    public String getDefaultSkinValue() {
        return defaultSkinVal;
    }

    @Override
    public String getDefaultSkinSignature() {
        return defaultSkinSig;
    }
}