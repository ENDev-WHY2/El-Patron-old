package org.endev.elpatron.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.endev.elpatron.CommandsListener;
import org.endev.elpatron.Main;

public class Status extends CommandsListener {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        setEvent(event);
        if(isCommand("status")) {
            sendMessageToChannel("I am online. :)");
        }
    }

    @Override
    public String getHelp() {
        return "Shows status of bot";
    }
}
