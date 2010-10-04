package net.flaviusb.getopt_scala

trait getopt {
  lazy val cmd: CommandStructure = blankCommandStructure
  def main(arg: Array[String]): Unit = cmd(arg)
}
class CommandStructure {
  lazy val cmd: String => Boolean = ((x: String) => false)
  lazy val nextLevel: CommandStructure = blankCommandStructure
  def apply(arg: String) = cmd
  def apply(foo: Array[String]): Unit = {
    var arg = foo
    var not_processed = false
    while(!not_processed) {
      not_processed = true;
      arg = arg.filter(x => { val f = cmd(x); not_processed &&= f; f })
    }
    if (arg.length > 0)
      nextLevel(arg)
  }
}
object blankCommandStructure extends CommandStructure {
  override def apply(arg: Array[String]): Unit = {
    // Do nothing
  }

}
