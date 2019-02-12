package stockapi

data class Portfolio(val map: Map<String, Int>)

data class TransactionResult(val amount: Int, val portfolio: Portfolio)

interface StockApi
{
    fun get(stockName: String, portfolio: Portfolio): Int

    fun sell(stockName: String, quantity: Int, portfolio: Portfolio): TransactionResult

    fun buy(stockName: String, amount: Int, portfolio: Portfolio): TransactionResult

}


