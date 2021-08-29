package org.endev.elpatron.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.endev.elpatron.Config;

import java.util.Random;

public class MemberJoinLeaveEvent extends ListenerAdapter {

    private final String[] welcomeMessages = { // TODO Add here some welcome messages.
        "Vítej [member], doufáme že se ti tu bde líbit."
    };

    private final String[] leaveMessages = { // TODO Add here some welcome messages.
            "[member] odešel, srab."
    };

    private Random r = new Random();

    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member m = e.getMember();

        Config.WELCOME_CHANNEL.sendMessage(welcomeMessages[r.nextInt(welcomeMessages.length)].replaceFirst("[member]", m.getEffectiveName())).queue();
        e.getGuild().addRoleToMember(m, e.getGuild().getRoleById("846785163105927173"));
    }

    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        Member m = e.getMember();

        Config.WELCOME_CHANNEL.sendMessage(welcomeMessages[r.nextInt(leaveMessages.length)].replaceFirst("[member]", m.getEffectiveName())).queue();
    }
}
