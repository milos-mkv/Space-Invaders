package com.spaceinvaders.game.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {

    public boolean isEnemyBullet;

    public BulletComponent(boolean isEnemyBullet) {
        this.isEnemyBullet = isEnemyBullet;
    }

}
