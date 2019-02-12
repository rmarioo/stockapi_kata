package stockapi

data class Portfolio(val map: Map<String, Int>)

data class TransactionResult(val amount: Int, val portfolio: Portfolio)

typealias Transaction = (Portfolio) -> TransactionResult

interface StockApi
{
    fun get(stockName: String): Transaction

    fun sell(stockName: String, quantity: Int): Transaction

    fun buy(stockName: String, amount: Int): Transaction

    fun transfer(fromName: String, toName: String): Transaction  = {

        portfolio ->

        val quantity = get(fromName)(portfolio).amount
        val (revenues, portfolio1) = sell(fromName, quantity)(portfolio)
        val (quantityPurchased, portfolio2) = buy(toName, revenues)(portfolio1)

        TransactionResult(quantityPurchased,portfolio2)
    }

}


