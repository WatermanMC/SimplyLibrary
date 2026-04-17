package com.github.WatermanMC.SimplyLibrary.api;

import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A timer interface for easier running a task in a timer
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyTimers {
    /**
     * Runs timer in Minecraft ticks (20 ticks per second)
     *
     * @param timerName Timer name for tracking
     * @param ticks The amount of ticks that timer will be run
     * @param repeat True if the timer should repeat
     * @param action Action after the timer finishes
     */
    TimerResult ticks(@NotNull String timerName,
                       long ticks,
                       boolean repeat,
                       Consumer<BukkitTask> action);
    /**
     * Runs timer in seconds
     *
     * @param timerName Timer name for tracking
     * @param seconds The amount of seconds that timer will be run
     * @param repeat True if the timer should repeat
     * @param action Action after the timer finishes
     */
    TimerResult seconds(@NotNull String timerName,
                        long seconds,
                        boolean repeat,
                        Consumer<BukkitTask> action);
    /**
     * Runs timer in minutes
     *
     * @param timerName Timer name for tracking
     * @param minutes The amount of minutes that timer will be run
     * @param repeat True if the timer should repeat
     * @param action Action after the timer finishes
     */
    TimerResult minutes(@NotNull String timerName,
                        long minutes,
                        boolean repeat,
                        Consumer<BukkitTask> action);
    /**
     * Runs timer in hours
     *
     * @param timerName Timer name for tracking
     * @param hours The amount of hours that timer will be run
     * @param repeat True if the timer should repeat
     * @param action Action after the timer finishes
     */
    TimerResult hours(@NotNull String timerName,
                      long hours,
                      boolean repeat,
                      Consumer<BukkitTask> action);
    /**
     * Runs timer in days
     *
     * @param timerName Timer name for tracking
     * @param days The amount of days that timer will be run
     * @param repeat True if the timer should repeat
     * @param action Action after the timer finishes
     */
    TimerResult days(@NotNull String timerName,
                     long days,
                     boolean repeat,
                     Consumer<BukkitTask> action);
    /**
     * Gets timer status
     *
     * @param timerName The timer name
     */
    TimerResult getTimerStatus(@NotNull String timerName);

    /**
     * Cancels a timer
     *
     * @param timerName The timer that will get canceled
     * @return {@link TimerResult} result
     */
    TimerResult cancel(@NotNull String timerName);
}
