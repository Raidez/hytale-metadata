package dev.hytalemodding.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class MetadataCommand extends AbstractCommandCollection {

    public MetadataCommand() {
        super("metadata", "Manage metadata for entities");
        this.addAliases("meta");
        this.addSubCommand(new MetadataGetCommand());
        this.addSubCommand(new MetadataSetCommand());
        this.addSubCommand(new MetadataResetCommand());
    }
}
