package org.endev.elpatron.commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.endev.elpatron.CommandsListener;
import org.endev.elpatron.Main;
import org.jetbrains.annotations.NotNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;

public class WTLSStatus extends CommandsListener {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if (Main.check(e)) return;
        setEvent(e);

        if (isCommand(new String[] {"wtls", "gta-mp", "samp", "sa-mp", "gta-mp.cz"})) {
            e.getChannel().sendTyping().queue();
            System.out.println("Web debug:");
            webScraping();
            System.out.println("End of web debug.");

            MessageBuilder b = new MessageBuilder("**Servery gta-multiplayer.cz:**\n*Server:                                 Počet hráčů:*\n");

            for (int i = 0; i < 6; i++) {
                    if (i < 4) {
                        b.append("***Welcome to Los Santos ").append(SERVERS.get(i)).append("*** - ");
                    }
                    else if (i == 4)
                        b.append("***Liberty Unleashed*** -");
                    else {
                        b.append("***VC-MP*** -");
                    }

                    b.append(" ").append(PLAYERS.get(i)).append("\n");
            }

            e.getChannel().sendMessage(b.build()).queue();
        }
    }
    private final LinkedList<Integer> SERVERS = new LinkedList<>();
    private final LinkedList<Integer> PLAYERS = new LinkedList<>();

    private void webScraping() {
        try {
            SERVERS.clear();
            PLAYERS.clear();
            final String url = "https://www.gta-multiplayer.cz/cz/game-servers/";
            final Document document = Jsoup.connect(url).get();
            // System.out.println(document.outerHtml()); // Prints html

            for (Element row : document.select("table.TableContainer.GameServers")) {
                if (row.select("td.ServerHostname").text().equals("")){
                    continue;
                } else {
                    String names = row.select("td.ServerHostname").text()
                            .replaceAll("Welcome to Los Santos", "")
                            .replaceFirst("   ", "  1  ")
                            .replaceFirst(" ", "");
                    System.out.println(names);

                    String[] s = names.split("  ");

                    for (String i : s) {
                        SERVERS.add(Integer.parseInt(i));
                        System.out.println(i);
                    }
                }


                if (row.select("td.ServerPlayers").text().equals("")) {
                    continue;
                } else {
                    final String playersString = row.select("td.ServerPlayers").text()
                            .replaceAll("0.3.7 ", "")
                            .replaceAll("0.3.DL ", "")
                            .replaceAll("0.1 ", "")
                            .replaceAll(" 0.3z R2", "");

                    System.out.println(playersString);

                    String[] s = playersString.split(" ");

                    for (String i : s) {
                        PLAYERS.add(Integer.parseInt(i));
                        System.out.println(i);
                    }
                }
            }

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp() {
        return "Shows you status of gta-mp.cz servers.";
    }
}
