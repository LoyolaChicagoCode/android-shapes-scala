package edu.luc.etl.cs313.scala.shapes
package ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Color


import model._

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
class Draw(canvas: Canvas, paint: Paint) {

  paint.setStyle(Style.STROKE)

  // TODO entirely your job (except Circle) DONE!

  def apply(s: Shape): Unit = s match {
    case Circle(radius) =>
              canvas.drawCircle(0, 0, radius, paint)
    case Rectangle(width, height) =>
              canvas.drawRect(0, 0, width, height, paint)
    case Polygon(points @ _*) =>
              def g(p:Point) = Seq[Point](p,p)
              var arr = points.flatMap(g)
              arr = arr.drop(1)
              arr = arr++List(arr.head)
              def f(p:Point) = Seq[Float](p.x,p.y)
              var arr1 = arr.flatMap(f)
              canvas.drawLines(arr1.toArray[Float],paint)
    case Fill(shape) =>
              paint.setStyle(Style.FILL_AND_STROKE)
              this.apply(shape)
              paint.setStyle(Style.STROKE)
    case Location(width, height, shape) =>
              canvas.translate(width, height)
              this.apply(shape)
              canvas.translate(-width, -height)
    case Outline(shape) =>
              paint.setStyle(Style.STROKE)
              this.apply(shape)
              paint.setStyle(Style.STROKE)
    case Stroke(color, shape) =>
              paint.setColor(color)
              this.apply(shape)
              paint.setColor(Color.BLACK)
    case Group(shapeList) =>
              shapeList.map(shape => this.apply(shape))
    case _ =>
  }
}
