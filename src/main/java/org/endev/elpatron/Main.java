package org.endev.elpatron;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main
{
    public static JDA jda = null;

    public static void main(String[] args) throws LoginException, IOException
    {
        Token token = new Token();

        jda = JDABuilder.createDefault(token.getToken())
                .setActivity(Activity.playing("ENGOvo gril..."))
                .build();
    }

    public static boolean check(GuildMessageReceivedEvent event)
    {
        return event.getAuthor().isBot() || event.isWebhookMessage() || !(event.getMessage().getContentRaw().startsWith(Config.PREFIX));
    }

    public static String[] args(GuildMessageReceivedEvent event)
    {
        return event.getMessage().getContentRaw().substring(Config.PREFIX.length()).split(" ");
    }
}
