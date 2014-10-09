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

  // TODO entirely your job (except Circle)

  def apply(s: Shape): Unit = s match {
    case Circle(radius) =>
              canvas.drawCircle(0, 0, radius, paint)
    case Rectangle(width, height) =>
              canvas.drawRect(0, 0, width, height, paint)
    case Polygon(p1, p2, p3, p4) =>
              val points = Seq[Point](p1,p2,p2,p3,p3,p4,p4,p1)
              def g(p:Point) = Seq[Float](p.x,p.y)
              var arr = points.flatMap(g)
             canvas.drawLines(arr.toArray[Float],paint)
             //Array[Float] (p1.x, p1.y, p2.x, p2.y, p2.x, p2.y, p3.x, p3.y, p3.x, p3.y, p4.x, p4.y, p1.x, p1.y, p4.x, p4.y), paint)
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
              shapeList.map(x => this.apply(x))
              /*for (shape:Shape <- shapeList) {
                this.apply(shape)
              }*/

    case _ =>
  }
}
