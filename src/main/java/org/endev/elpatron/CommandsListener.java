package org.endev.elpatron;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandsListener extends ListenerAdapter {
    private GuildMessageReceivedEvent e;

    public void setEvent(GuildMessageReceivedEvent e) {
        this.e = e;
    }
    public String getHelp() {
        return "Help not specified";
    }

    protected String[] getArgs() {
        return e.getMessage().getContentRaw().split(" ");
    }

    public String getName() {
        return getClass().getName();
    }

    protected String getDisplayName() {
        return e.getMember().getEffectiveName();
    }

    protected boolean sendMessageToChannel(String message) {
        if (message == null) return false;
        e.getChannel().sendMessage(message).queue();
        return true;
    }

    protected boolean isCommand(String command) {
        if (Main.check(e)) return false;

        if (getArgs()[0].equalsIgnoreCase(Config.PREFIX + command))
            return true;
        return false;
    }
}
