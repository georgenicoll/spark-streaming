package org.monkeynuthead.spark

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.junit.runner.RunWith
import org.scalatest.{WordSpecLike, MustMatchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SetUpSparkStreaming extends WordSpec with SparkContextSpec with MustMatchers {

  "spark" must {

    "allow adding data to the context" in {
      val data = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      val parData = context.get.parallelize(data)
      parData.reduce(_ + _) must equal(55)
    }

    "start and stop a local streaming context" in {
      val ssc = new StreamingContext(context.get, Seconds(1))
      val myReceiver = new TestReceiver

      val receiverStream = ssc.receiverStream(myReceiver)
      val words = receiverStream.flatMap(_.split(" "))
      val wordCounts = words.map(word => (word, 1))
      wordCounts.print()

      ssc.start()

      myReceiver.send("George Mildred Wilfred Bert")
      myReceiver.send("Harry Wilfred George")
      myReceiver.send("George")

      ssc.awaitTermination()
    }

  }

}

class TestReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY) {

  def onStart(): Unit = {
  }

  def onStop(): Unit = {
  }

  def send(s: String): Unit = {
    store(s)
  }

}