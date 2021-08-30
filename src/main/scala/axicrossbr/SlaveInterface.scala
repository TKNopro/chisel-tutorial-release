package axifabric

import chisel3._
import chisel3.util._

class SlaveInterface extends Bundle{
  val aw  = Decoupled(new AChannel)
  val w   = Decoupled(new DChannel)
  val b   = Decoupled(new RChannel)
  val ar  = Decoupled(Flipped(new AChannel))
  val r   = Decoupled(Flipped(new DChannel)) 
}