package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.Group.MySprite
import kotlin.math.abs
import kotlin.random.Random

class Alien(x: Float, y: Float): MySprite {

    private val texture = Texture("img/alien${Random.nextInt(1, 6)}.png")
    private val sprite = Sprite(texture)
    private var moveDirection = 1
    private var moveCount = 0

    init {
        sprite.setCenter(x, y)
    }

    override fun update(remove: (MySprite) -> Unit) {
        sprite.x += moveDirection
        moveCount++
        if (abs(moveCount) > 75) {
            moveDirection *= -1
            moveCount *= moveDirection
        }
    }

    override fun render(batch: SpriteBatch) {
        sprite.draw(batch)
    }
}