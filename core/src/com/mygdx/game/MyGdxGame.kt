package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.game.Group.SpriteGroup
import kotlin.random.Random

class MyGdxGame : ApplicationAdapter() {
    private lateinit var backgroundImage: Texture
    private lateinit var batch: SpriteBatch
    private lateinit var spaceship: Spaceship
    private lateinit var bulletGroup: SpriteGroup<Bullets>
    private lateinit var alienGroup: SpriteGroup<Alien>
    private lateinit var alienBulletGroup: SpriteGroup<AlienBullet>
    private lateinit var font: BitmapFont
    private var lastAlienShot = System.currentTimeMillis()
    private var alienCooldown = 1000

    override fun create() {
        backgroundImage = Texture("img/bg.png")
        batch = SpriteBatch()
        spaceship = Spaceship(Gdx.graphics.width.toFloat() / 2, 100f, 5)
        bulletGroup = SpriteGroup()
        alienGroup = SpriteGroup()
        alienBulletGroup = SpriteGroup()
        font = BitmapFont()

        createAlien()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)

        font.color.set(1f, 1f, 1f, 1f)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        font.data.setScale(3.2f)


        batch.begin()
        val timeNow = System.currentTimeMillis()
        if (timeNow - lastAlienShot > alienCooldown) {
            val attackingAlien = alienGroup.sprites[Random.nextInt(alienGroup.sprites.size)]
            val alienBullet = AlienBullet(attackingAlien.sprite.x + attackingAlien.sprite.width / 2, attackingAlien.sprite.y)
            alienBulletGroup.add(alienBullet)
            lastAlienShot = timeNow
        }

        batch.draw(backgroundImage, 0f, 0f)

        spaceship.render(batch)
        spaceship.handleAttack(batch, bulletGroup)
        bulletGroup.render(batch)
        alienGroup.render(batch)
        alienBulletGroup.render(batch)

        font.color.set(1f, 1f, 1f, 1f)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        font.data.setScale(1.2f)
        font.draw(batch, "${Gdx.graphics.framesPerSecond} FPS", 50f, Gdx.graphics.height.toFloat() - 50)

        batch.end()



        spaceship.drawHealthBar()
        bulletGroup.update()
        alienGroup.update()
        spaceship.move()
        alienBulletGroup.update()
    }

    private fun createAlien() {
        for (row in 0..4) {
            for (col in 0..4) {
                val alien = Alien((100 + col * 100).toFloat(), (Gdx.graphics.height - 100 - row * 70).toFloat())
                alienGroup.add(alien)
            }
        }
    }

    override fun dispose() {
        batch.dispose()
        backgroundImage.dispose()
    }
}
