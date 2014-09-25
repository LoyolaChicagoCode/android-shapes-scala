package edu.luc.etl.cs313.scala.shapes.model

import android.graphics.Color

object fixtures {

  val simpleCircle = Circle(50)

  val simpleRectangle = Rectangle(80, 120)

  val simplePolygon = new Polygon(
    new Point(50, 50),
    new Point(60, 100),
    new Point(100, 110),
    new Point(120, 60)
  )

  val simpleLocation = new Location(70, 30, new Rectangle(80, 120))

  val simpleFill = new Fill(new Rectangle(80, 120))

  val simpleOutline = new Outline(new Rectangle(80, 120));

  val simpleStroke = new Stroke(Color.RED, new Rectangle(80, 120));

  val simpleGroup = new Group(
    new Location(200, 100, new Circle(50)),
    new Location(400, 300, new Rectangle(100, 50))
  )

  val complexGroup = new Location(50, 100,
    new Group(
      new Circle(20),
      new Rectangle(100, 200),
      new Location(150, 50,
        new Stroke(Color.RED,
          new Fill(
            new Group(
              new Rectangle(50, 30),
              new Outline(new Rectangle(300, 60)),
              new Stroke(Color.CYAN,
                new Polygon(
                  new Point(50, 50),
                  new Point(60, 100),
                  new Point(100, 110),
                  new Point(120, 60)
                )
              ),
              new Location(100, 200,
                new Stroke(Color.MAGENTA,
                  new Outline(new Circle(50))
                )
              )
            )
          )
        )
      )
    )
  )
}