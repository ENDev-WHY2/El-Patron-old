package org.endev.elpatron.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.endev.elpatron.Main;
import org.jetbrains.annotations.NotNull;

public class Help extends ListenerAdapter
{
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (Main.check(event)) return;
        String[] args = Main.args(event);

        if (args[0].equalsIgnoreCase("help"))
        {
            event.getChannel().sendMessage("Go fuck yourself.").queue();
        }
    }
}
