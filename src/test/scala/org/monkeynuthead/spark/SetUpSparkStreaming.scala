package org.monkeynuthead.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.junit.runner.RunWith
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SetUpSparkStreaming extends WordSpec with MustMatchers {

  "spark streaming" must {

    val Master = "local[1]"

    "can start and stop a local streaming context" in {
      val conf = new SparkConf().setAppName(getClass.getName()).setMaster(Master)
      val context = new StreamingContext(conf, Milliseconds(1))

      fail("Need an input and output stream")

      context.start()
      context.awaitTermination()
    }

  }

}
