package net.crocodil.delab.items;

import com.google.common.collect.Sets;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.neoforged.neoforge.common.ItemAbilities.SWORD_DIG;


public class DelabItemAbilites {
    public static final Set<ItemAbility> DEFAULT_DAGGER_ACTIONS;
    private static Set<ItemAbility> of(ItemAbility... actions) {
        return (Set) Stream.of(actions).collect(Collectors.toCollection(Sets::newIdentityHashSet));
    }

    static {
        DEFAULT_DAGGER_ACTIONS = of(SWORD_DIG);
    }
}
