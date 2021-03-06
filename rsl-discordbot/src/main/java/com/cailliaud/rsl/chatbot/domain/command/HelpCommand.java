package com.cailliaud.rsl.chatbot.domain.command;

import com.cailliaud.rsl.chatbot.domain.RslCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;

public class HelpCommand implements ICommandAnswer {
    @Override
    public void publishAnswer(MessageReceivedEvent event, Object... args) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.RED)
                .setAuthor("AlucareBot", "https://github.com/cailliaud/RSLBot")
                .setTitle("Raid Shadow Legends Bot : Guide des commandes")
                .setDescription("Ensemble des commandes utilisables pour le bot Raid Shadow Legends.")
                .setFooter("Discord ChatBot développé par Cailliaud, modifié par Alucare pour le discord Alucare.fr, merci à BUZZ pour la liste des champions");

        Arrays.stream(RslCommand.values()).filter(rslCommand -> !rslCommand.isHidden()).forEach(
                cmd -> builder.addField(cmd.getKey(), cmd.getDescription() + "\n" + cmd.getExample(), false)
        );

        MessageEmbed msgEmbedded = builder.build();
        event.getAuthor().openPrivateChannel().queue(channel -> channel.sendMessage(msgEmbedded).queue());

    }
}
