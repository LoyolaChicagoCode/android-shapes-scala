package edu.luc.etl.cs313.scala.shapes
package ui

import org.mockito.Matchers._
import org.mockito.Matchers.{eq => matchEq}
import org.mockito.Mockito.inOrder
import org.scalatest.mock.MockitoSugar
import org.scalatest.FlatSpec

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style

import model._
import fixtures._

/**
 * Mock-based test for the Draw visitor.
 * The subject-under-test (SUT), Draw, has two dependencies,
 * which show up as constructor arguments:
 * a canvas and a paint object. Because Draw cannot function without its
 * dependencies, we wonder how to unit-test Draw in isolation. The solution
 * is to use mock objects during testing instead of the real dependencies.
 * We can control the behavior of the mock objects and verify the
 * expected interactions of the SUT with the mock dependencies.
 */
class DrawTest extends FlatSpec with MockitoSugar {

  /** Sets up the test support. This replaces @Before/@After. */
  def support() = new {
    val canvas = mock[Canvas]
    val paint = mock[Paint]
    val order = inOrder(canvas, paint)
    val draw = new Draw(canvas, paint)
  }

  "The draw behavior" should "work for a simple circle" in {
    val s = support
    import s._
    draw(simpleLocation)
    order.verify(canvas).translate(70, 30)
    order.verify(canvas).drawRect(0, 0, 80, 120, paint)
    order.verify(canvas).translate(-70, -30)
  }

  it should "work for a simple location" in {
    val s = support
    import s._
    draw(simpleLocation)
    order.verify(canvas).translate(70, 30)
    order.verify(canvas).drawRect(0, 0, 80, 120, paint)
    order.verify(canvas).translate(-70, -30)
  }

  it should "work for a simple group" in {
    val s = support
    import s._
    draw(simpleGroup)
    order.verify(canvas).translate(200, 100)
    order.verify(canvas).drawCircle(0, 0, 50, paint)
    order.verify(canvas).translate(-200, -100)
    order.verify(canvas).translate(400, 300)
    order.verify(canvas).drawRect(0, 0, 100, 50, paint)
    order.verify(canvas).translate(-400, -300)
  }

  it should "work for a complex group" in {
    val s = support
    import s._
    draw(complexGroup)
    order.verify(canvas).translate(50, 100)
    order.verify(canvas).drawCircle(0, 0, 20, paint)
    order.verify(canvas).drawRect(0, 0, 100, 200, paint)
    order.verify(canvas).translate(150, 50)
    order.verify(paint).setColor(Color.RED)
    order.verify(paint).setStyle(Style.FILL_AND_STROKE)
    order.verify(canvas).drawRect(0, 0, 50, 30, paint)
    order.verify(paint).setStyle(Style.STROKE)
    order.verify(canvas).drawRect(0, 0, 300, 60, paint)
    order.verify(paint).setStyle(any(classOf[Style]))
    order.verify(paint).setColor(Color.CYAN)
    order.verify(canvas).drawLines(any(classOf[Array[Float]]), matchEq(paint))
    order.verify(paint).setColor(anyInt())
    order.verify(canvas).translate(100, 200)
    order.verify(paint).setColor(Color.MAGENTA)
    order.verify(paint).setStyle(Style.STROKE)
    order.verify(canvas).drawCircle(0, 0, 50, paint)
    order.verify(paint).setStyle(any(classOf[Style]))
    order.verify(paint).setColor(anyInt())
    order.verify(canvas).translate(-100, -200)
    order.verify(paint).setStyle(any(classOf[Style]))
    order.verify(paint).setColor(anyInt())
    order.verify(canvas).translate(-150, -50)
    order.verify(canvas).translate(-50, -100)
  }
}
