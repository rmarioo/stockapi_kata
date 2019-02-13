package stockapi

import arrow.data.State
import arrow.instances.monad


interface StockApiMonad
{
    fun get(stockName: String): State<Portfolio,Int>

    fun sell(stockName: String, quantity: Int): State<Portfolio,Int>

    fun buy(stockName: String, amount: Int): State<Portfolio,Int>

    fun transfer(fromName: String, toName: String) = State().monad<Portfolio>().binding {

        val actualQuantity = get(fromName).bind()
        val revenues = sell(fromName, actualQuantity).bind()
        val purchase = buy(toName, revenues).bind()
        purchase
    }

}


