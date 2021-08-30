package axifabric

import chisel3._
import chisel3.util._

trait AxiConfig {
  ///////////////////
  //Address channel
  ///////////////////
  val idWidth     = 4
  val addrWidth   = 7
  val lenWidth    = 4
  val sizeWdith   = 2
  val burstWidth  = 2
  val lockWidth   = 2
  val cacheWidth  = 4
  val protWidth   = 3
  val qosWidth    = 3
  val regionWidth = 3
  val userWidth   = 4

  ///////////////////
  //Data channel
  ///////////////////
  val dataWidth   = 32
  val strbWidth   = 4
  val lastWidth   = 1
  ///////////////////
  //Resp channel
  ///////////////////
  val respWidth   = 2


  val CACHE_RALLOCATE   = 8.asUInt(cacheWidth.W)
  val CACHE_WALLOCATE   = 4.asUInt(cacheWidth.W)
  val CACHE_MODIFIABLE  = 2.asUInt(cacheWidth.W)
  val CACHE_BUFFERABLE  = 1.asUInt(cacheWidth.W)

  val PROT_PRIVILEDGED  = 1.asUInt(protWidth.W)
  val PROT_INSECURE     = 2.asUInt(protWidth.W) 
  val PROT_INSTRUCTION  = 4.asUInt(protWidth.W)

  val BURST_FIXED       = 0.asUInt(burstWidth.W)
  val BURST_INCR        = 1.asUInt(burstWidth.W)
  val BURST_WRAP        = 2.asUInt(burstWidth.W)

  val RESP_OKAY         = 0.asUInt(respWidth.W)
  val RESP_EXOKAY       = 1.asUInt(respWidth.W)
  val RESP_SLVERR       = 2.asUInt(respWidth.W)
  val RESP_DECERR       = 3.asUInt(respWidth.W)
}