package net.chugun.h3.bench


import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters._
import scala.util.Random
import org.openjdk.jmh.annotations._

import com.uber.h3core.H3Core
import net.chugun.h3.H3

@BenchmarkMode(Array(Mode.AverageTime))
@State(Scope.Thread)
class CellToLatLngBench {

  @Param(Array("604029720564072447")) // res 6
  var tileH3: Long = _

  @transient
  var cells: Array[java.lang.Long] = _

  @transient
  var core: H3Core = _

  @Setup(Level.Trial)
  def setupData(): Unit = {
    //val parent = H3.core.cellToParent(tileH3, 5)
    println(s"Setup: ${Thread.currentThread().getName()}")
    core = H3Core.newInstance()
    cells = core.cellToChildren(tileH3, 11).asScala.toArray
  }

  @Threads(10)
  @Benchmark
  def takeOne(): Double  = {
    var max: Double = 0
    var i: Int = 0
    while (i < cells.length) {
      max = H3.core.cellToLatLng(cells(i)).lat
      i += 1
    }
    max
  }
}
