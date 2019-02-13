package stockapi

import arrow.core.Tuple2
import arrow.data.State

class InMemoryStockApiMonad(val prizes: Map<String, Int>) : StockApiMonad {
    override fun sell(stockName: String, quantity: Int): State<Portfolio, Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buy(stockName: String, amount: Int): State<Portfolio, Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(stockName: String): State<Portfolio, Int> {

        val get: Transaction = TransactionResult()
    }

}