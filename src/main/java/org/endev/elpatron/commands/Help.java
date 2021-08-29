package org.endev.elpatron.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.endev.elpatron.CommandsListener;
import org.endev.elpatron.Main;
import org.jetbrains.annotations.NotNull;

public class Help extends CommandsListener
{
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (Main.check(event)) return;
        setEvent(event);

        if (isCommand("help")) {
            StringBuilder message = new StringBuilder("Available commands:\n");

            for (CommandsListener i : Main.Listeners.COMMANDS) {
                message.append("**" + i.getName() + "** - *");
                message.append(i.getHelp() + "* \n");
            }

            sendMessageToChannel(message.toString());
        }
    }

    @Override
    public String getHelp(){
        return "Shows you commands";
    }
}
