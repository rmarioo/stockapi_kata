package stockapi

import arrow.core.Tuple2
import arrow.data.State

class InMemoryStockApiMonad(val prizes: Map<String, Int>) : StockApiMonad {

    override fun buy(stockName: String, amount: Int): State<Portfolio, Int> =

            State { portfolio ->
                val numberPurchased = amount / prizes.getValue(stockName)

                val toMutableMap = portfolio.map.toMutableMap()
                toMutableMap.put(stockName, portfolio.map.getValue(stockName) + numberPurchased)

                Tuple2(Portfolio(toMutableMap), numberPurchased)

            }

    override fun sell(stockName: String, quantity: Int): State<Portfolio, Int> =

            State { portfolio ->

                val revenues = quantity * prizes.getValue(stockName)
                val toMutableMap = portfolio.map.toMutableMap()
                toMutableMap.put(stockName, portfolio.map.getValue(stockName) - quantity)

                Tuple2(Portfolio(toMutableMap), revenues)

            }

    override fun get(stockName: String): State<Portfolio, Int> =
            State { portfolio ->  Tuple2(portfolio, portfolio.map.getValue(stockName)) }
}