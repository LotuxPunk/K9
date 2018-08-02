package com.vandendaelen.k9.entities.ai;

import com.vandendaelen.k9.entities.EntityK9;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;

public class EntityAiMining extends EntityAIBase {

    private int depth;
    private int distance;
    private EntityK9 k9;
    private PathNavigate pathFinder;

    @Override
    public boolean shouldExecute() {
        return false;
    }
}
