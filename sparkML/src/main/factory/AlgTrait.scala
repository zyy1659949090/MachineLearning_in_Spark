package main.factory

import main.util.{MainHolder, SparkEnv}

/**
 * Created by zhy on 2015/8/2 0002.
 */

/**
 * 推荐算法输入数据
 */
trait InputRecData extends Serializable {
  protected val sc = SparkEnv.sc
  protected val ratings = MainHolder.getDataHolder().getData
  MainHolder.getDataHolder().getDataDesc

  //分割数据集为训练集、验证集、测试集
  protected val RDD = ratings.randomSplit(Array(0.7, 0.2, 0.1))
  protected val trainData = RDD(0).persist
  protected val validateData = RDD(1).persist
  protected val testData = RDD(2).persist
  protected val numValidation = validateData.count
  protected val numTest = testData.count
}

/**
 * LR输入数据
 */
trait InputLRData extends Serializable {
  protected val sc = SparkEnv.sc
  protected val data = MainHolder.getDataHolder().getLRData
  MainHolder.getDataHolder().getDataDesc

  protected val RDD = data.randomSplit(Array(0.8, 0.2))
  protected val trainData = RDD(0).persist
  protected val testData = RDD(1).persist

}

/**
 * 算法度量方式
 */
trait RMSE extends Serializable {
  protected var RMSE: Double = Double.MaxValue

  /**
   * @return 算法对于指定数据集推荐结果的均方根误差(RMSE)
   */
  def getRMSE = println("测试集的RMSE为 " + RMSE + "\n----------测试完毕----------")
}
