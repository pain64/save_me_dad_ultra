package com.catinthedark.savemedad.units

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.catinthedark.savemedad.common.Attacks._
import com.catinthedark.savemedad.{CooldownIndicator, Assets, Shared}
import com.catinthedark.savemedad.lib.{Renderable, RenderTask, Layer, ComputeUnit}
import com.catinthedark.savemedad.lib.Magic._

/**
 * Created by over on 02.01.15.
 */
class View(shared: Shared) extends ComputeUnit{

  val roomAndHUD = new Layer {
    val batch = new SpriteBatch

    override def render(delta: Float): Unit = {
      batch.managed { self =>
        self.draw(Assets.Textures.room, 0, 0)
        data.indicatorCol.render(delta, self)
        data.indicatorRow.render(delta, self)
      }
    }
  }

  case class Data(val indicatorRow: CooldownIndicator = RenderFactory.cooldownAnimation(Row),
                  val indicatorCol: CooldownIndicator = RenderFactory.cooldownAnimation(Col))

  val data = new Data

  override def onActivate(): Unit = {}

  override def onExit(): Unit = {}


  def onShoot(attack: Attacks) = {
    data.indicatorRow.animate();
    data.indicatorCol.animate();
  }



  override def run(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    roomAndHUD.render(delta)
  }
}
