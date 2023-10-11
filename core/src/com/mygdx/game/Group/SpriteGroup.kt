package com.mygdx.game.Group

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SpriteGroup<E: MySprite> {
    val sprites = ArrayList<E>()

    fun add(bullets: E) {
        sprites.add(bullets)
    }

    fun update() {
        val iterator = sprites.iterator()
        while (iterator.hasNext()) {
            val bullet = iterator.next()
            bullet.update {
                iterator.remove()
            }
        }
    }

    fun render(batch: SpriteBatch) {
        for (sprite in sprites) {
            sprite.render(batch)
        }
    }
}