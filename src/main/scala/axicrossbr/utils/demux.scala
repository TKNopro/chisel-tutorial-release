package axifabric.utils

import chisel3._
import chisel3.util._

class demux[T <: Data, U <: Data](inPort: T, outPort: U, width: Int) extends RawModule {
  val io = IO(new Bundle{
    val sel   = Input(UInt(width.W))
    val muxIn = inPort
    val muxOut= Vec(width, outPort)  
  })

  for(i <- 0 until width) {
    when(io.sel === i.U) {
      io.muxOut(i) <>  io.muxIn
    }
  }
}
