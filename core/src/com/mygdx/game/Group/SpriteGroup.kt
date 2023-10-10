package com.mygdx.game.Group

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SpriteGroup<E: MySprite> {
    private val spriteGroup = ArrayList<E>()

    fun add(bullets: E) {
        spriteGroup.add(bullets)
    }

    fun update() {
        val iterator = spriteGroup.iterator()
        while (iterator.hasNext()) {
            val bullet = iterator.next()
            bullet.update {
                iterator.remove()
            }
        }
    }

    fun render(batch: SpriteBatch) {
        for (sprite in spriteGroup) {
            sprite.render(batch)
        }
    }
}