package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.seattlesolvers.solverslib.command.CommandBase;

class IndexerRunCommand extends CommandBase {
    private final Indexer indexer;

    public IndexerRunCommand(Indexer indexer) {
        this.indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() { indexer.start(); }

    @Override
    public void end(boolean interrupted) { indexer.stop(); }
}