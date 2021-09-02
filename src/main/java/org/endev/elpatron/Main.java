package org.endev.elpatron;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.endev.elpatron.commands.Help;
import org.endev.elpatron.commands.Status;
import org.endev.elpatron.commands.WTLSStatus;
import org.endev.elpatron.events.MemberJoinLeaveEvent;

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

        Listeners.listeners();
    }

    public static boolean check(GuildMessageReceivedEvent event)
    {
        return event.getAuthor().isBot() || event.isWebhookMessage() || !(event.getMessage().getContentRaw().startsWith(Config.PREFIX));
    }

    public static String[] args(GuildMessageReceivedEvent event)
    {
        return event.getMessage().getContentRaw().substring(Config.PREFIX.length()).split(" ");
    }

    public static class Listeners {

        public static final CommandsListener[] COMMANDS = { // Here add new commands listeners.
                new Help(),
                new Status(),
                new WTLSStatus(),
        };

        public static final Object[] EVENTS = { // Here add new events listeners.
                new MemberJoinLeaveEvent(),
        };

        private static void listeners() {
            for (Object i : COMMANDS) {
                jda.addEventListener(i);
            }

            for (Object i : EVENTS) {
                jda.addEventListener(i);
            }
        }
    }
}
