package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.SimplyTimers;
import com.github.WatermanMC.SimplyLibrary.api.TimerResult;import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SimplyTimersImpl implements SimplyTimers {
    private final Main plugin;
    private final Map<String, BukkitTask> activeTimers = new HashMap<>();
    private final Map<String, TimerResult> canceledStates = new HashMap<>();
    private final Map<String, TimerResult> finishedTimers = new HashMap<>();

    SimplyTimersImpl(Main plugin) {
        this.plugin = plugin;
    }

    private TimerResult start(@NotNull String timerName,
                              long ticks,
                              boolean repeat,
                              Consumer<BukkitTask> action) {
        if (activeTimers.containsKey(timerName)) return TimerResult.RUNNING;
        if (ticks < 0) return TimerResult.INVALID_TIME;

        final BukkitTask[] taskHolder = new BukkitTask[1];

        Runnable runnable = () -> {
            action.accept(taskHolder[0]);

            if (!repeat) {
                activeTimers.remove(timerName);
                finishedTimers.put(timerName, TimerResult.FINISHED);
            }
        };

        if (repeat) {
            taskHolder[0] = Bukkit.getScheduler().runTaskTimer(plugin, runnable, ticks, ticks);
        } else {
            taskHolder[0] = Bukkit.getScheduler().runTaskLater(plugin, runnable, ticks);
        }

        activeTimers.put(timerName, taskHolder[0]);
        return TimerResult.STARTED;
    }

    @Override
    public TimerResult ticks(@NotNull String timerName,
                             long ticks,
                             boolean repeat,
                             Consumer<BukkitTask> action) {
        return start(timerName, ticks, repeat, action);
    }

    @Override
    public TimerResult seconds(@NotNull String timerName,
                               long seconds,
                               boolean repeat,
                               Consumer<BukkitTask> action) {
        return ticks(timerName, seconds * 20L , repeat, action);
    }

    @Override
    public TimerResult minutes(@NotNull String timerName,
                               long minutes,
                               boolean repeat,
                               Consumer<BukkitTask> action) {
        return seconds(timerName, minutes * 60L , repeat, action);
    }

    @Override
    public TimerResult hours(@NotNull String timerName,
                             long hours,
                             boolean repeat,
                             Consumer<BukkitTask> action) {
        return minutes(timerName, hours * 60L , repeat, action);
    }

    @Override
    public TimerResult days(@NotNull String timerName,
                            long days,
                            boolean repeat,
                            Consumer<BukkitTask> action) {
        return hours(timerName, days * 24L , repeat, action);
    }

    @Override
    public TimerResult getTimerStatus(@NotNull String timerName) {
        if (activeTimers.containsKey(timerName)) {
            return TimerResult.RUNNING;
        } else if (canceledStates.containsKey(timerName)) {
            return TimerResult.CANCELLED;
        } else if (finishedTimers.containsKey(timerName)) {
            return TimerResult.FINISHED;
        } else return TimerResult.NOT_FOUND;
    }

    @Override
    public TimerResult cancel(@NotNull String timerName) {
        BukkitTask task = activeTimers.remove(timerName);
        if (task == null) return TimerResult.NOT_FOUND;

        task.cancel();
        canceledStates.put(timerName, TimerResult.CANCELLED);
        return TimerResult.CANCELLED;
    }

    void clearAll() {
        activeTimers.values().forEach(BukkitTask::cancel);
        activeTimers.clear();
        canceledStates.clear();
        finishedTimers.clear();
    }
}