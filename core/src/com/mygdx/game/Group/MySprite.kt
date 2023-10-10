package com.mygdx.game.Group

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface MySprite {
    fun update(remove: (MySprite) -> Unit)
    fun render(batch: SpriteBatch)
}
