package at.helpch.chatchat.api.event;

import at.helpch.chatchat.api.channel.Channel;
import at.helpch.chatchat.api.user.ChatUser;
import at.helpch.chatchat.api.user.User;
import at.helpch.chatchat.api.utils.MentionType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 *     Called when a {@link ChatUser} mentions a {@link User}.
 * </p>
 * <p>
 *     Make sure to check if the user can see the target using {@link User#canSee(User)} since the event is called even
 *     when a user mentions another vanished user.
 * </p>
 */
public class MentionEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled = false;

    private @NotNull final ChatUser user;
    private @NotNull final User target;
    private @NotNull final Channel channel;

    private @NotNull final MentionType type;

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    public MentionEvent(
        final boolean async,
        @NotNull final ChatUser user,
        @NotNull final User target,
        @NotNull final Channel channel,
        @NotNull final MentionType type
    ) {
        super(async);
        this.user = user;
        this.target = target;
        this.channel = channel;
        this.type = type;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Get the user that mentioned the target.
     *
     * @return The user that mentioned the target.
     */
    public @NotNull ChatUser user() {
        return user;
    }

    /**
     * Get the user that was mentioned.
     *
     * @return The target of the mention.
     */
    public @NotNull User target() {
        return target;
    }

    /**
     * Get the channel that the mention was sent in.
     *
     * @return The channel that the mention was sent in.
     */
    public @NotNull Channel channel() {
        return channel;
    }

    /**
     * Get the type of the mention.
     *
     * @return The mention type.
     */
    public MentionType type() {
        return type;
    }
}
