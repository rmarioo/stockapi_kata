package stockapi

data class Portfolio(val map: Map<String, Int>)

data class TransactionResult(val amount: Int, val portfolio: Portfolio)

interface StockApi
{
    fun get(stockName: String, portfolio: Portfolio): Int

    fun sell(stockName: String, quantity: Int, portfolio: Portfolio): TransactionResult

    fun buy(stockName: String, amount: Int, portfolio: Portfolio): TransactionResult

    fun move(fromName: String,toName: String,portfolio: Portfolio ): TransactionResult {

        val quantity = get(fromName,portfolio)
        val (revenues, portfolio1) = sell(fromName, quantity, portfolio)
        val (quantityPurchased, portfolio2) = buy(toName, revenues, portfolio1)

        return TransactionResult(quantityPurchased,portfolio2)
    }

}


