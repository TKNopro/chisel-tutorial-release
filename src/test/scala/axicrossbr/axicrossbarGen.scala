package axifabric

import chisel3._
import chisel3.util._

object axifabricGen extends App {
  println("\033[32m[attention]\033[0m--start to generate the verilog")
  chisel3.Driver.execute(args, () => new axiFabric(2, 2))
}