package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.Group.MySprite

class Bullets(x: Float, y: Float): MySprite {
    private val texture = Texture("img/bullet.png")
    private val sprite = Sprite(texture)

    init {
        sprite.setCenter(x, y)
    }

    override fun render(batch: SpriteBatch) {
        sprite.draw(batch)
    }

    override fun update(remove: (MySprite) -> Unit) {
        sprite.y += 5
        if (sprite.y > Gdx.graphics.height) {
            remove(this)
        }
    }
}
