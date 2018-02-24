package lineus


object LineUsApp extends App {

  val socket = new LineUsSocket

  private val lineUs = new LineUs(
    in = socket.receive,
    out = socket.send
  )

  lineUs.execute(
    GCode.render(GCode.draw(GCode.rect(800, 1100, 800, 2100)): _*): _*
  )

}