package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class Blocks extends GameObject {

    DelayedRemovalArray<Block> blockList;

    public Blocks() {
        blockList = new DelayedRemovalArray<Block>();
        init();
    }

    public void init(){
        blockList.clear();

        for (int i = 0; i < Constants.BLOCKS_ROWS; i++){
            for (int j = 0; j < Constants.BLOCKS_PER_ROW; j++) {
                Vector2 pos = new Vector2(Constants.BLOCK_W * j, Constants.WORLD_H - Constants.BLOCK_H * i);

                blockList.add(new Block(pos));
            }
        }
    }

    public void render(ShapeRenderer renderer){
        for (Block block: blockList){
            block.render(renderer);
        }
    }
}
