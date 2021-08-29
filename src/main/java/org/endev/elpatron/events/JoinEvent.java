package org.endev.elpatron.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class JoinEvent extends ListenerAdapter {

    private final String[] welcomeMessages = { // TODO Add here some welcome messages.
        "Vítej [member], doufáme že se ti tu bde líbit."
    };
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member m = e.getMember();

        Random r = new Random();
        r.nextInt(welcomeMessages.length);
    }
}
