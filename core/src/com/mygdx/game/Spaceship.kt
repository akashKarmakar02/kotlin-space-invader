package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.Group.SpriteGroup

class Spaceship(
    private var x: Float,
    private val y: Float,
    private val maxHealth: Int
) {
    private val texture = Texture("img/spaceship.png")
    private val sprite = Sprite(texture)
    private val healthBar = ShapeRenderer()
    private var remainingHealth: Int
    private var lastShotTime: Long = 0
    private val coolDown: Long = 500

    init {
        sprite.setCenter(x, y)
        remainingHealth = maxHealth
    }

    fun render(batch: SpriteBatch) {
        this.sprite.draw(batch)
    }

    fun move() {
        val speed = 8

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && sprite.x >= 0f) {
            x -= speed
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && sprite.x + sprite.width <= Gdx.graphics.width) {
            x += speed
        }

        sprite.setCenter(x, y)
    }

    fun handleAttack(batch: SpriteBatch, bulletGroup: SpriteGroup<Bullets>) {
        val currentTime = System.currentTimeMillis()
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && currentTime - lastShotTime > coolDown) {
            val bullets = Bullets(this.x, this.y + this.sprite.height / 2)
            bulletGroup.add(bullets)
            lastShotTime = currentTime
        }
    }


    fun drawHealthBar() {
        healthBar.color = Color.RED

        healthBar.begin(ShapeRenderer.ShapeType.Filled)
        healthBar.rect(sprite.x, sprite.y - 15, sprite.width, 15f)

        healthBar.color = Color.GREEN
        healthBar.rect(sprite.x, sprite.y - 15, sprite.width * remainingHealth / maxHealth, 15f)
        healthBar.end()
    }
}