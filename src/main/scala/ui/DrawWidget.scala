package edu.luc.etl.cs313.scala.shapes
package ui

import android.util.AttributeSet
import android.view.View
import android.content.Context
import android.graphics.{Color, Canvas, Paint}

import model._

/**
 * A custom widget for drawing some lines.
 */
class DrawWidget(context: Context) extends View(context) {

  def this(context: Context, attrs: AttributeSet, defStyle: Int) { this(context) }

  def this(context: Context, attrs: AttributeSet) { this(context) }

  override protected def onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int): Unit = {
    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
  }

  private val paint = new Paint

  override protected def onDraw(canvas: Canvas): Unit = {
    val shape = fixtures.simpleRectangle
    val b @ Location(x, y, Rectangle(w, h)) = boundingBox(shape)
    setBackgroundColor(Color.WHITE)
    canvas.translate(-x, -y)
    val draw = new Draw(canvas, paint)
    draw(b)
    draw(shape)
    canvas.translate(x, y)
  }
}
