package stockapi

class InMemoryStockApi(val prizes: Map<String, Int>) : StockApi
{

    override fun buy(stockName: String, amount: Int, portfolio: Portfolio): TransactionResult {

        val numberPurchased = amount / prizes.getValue(stockName)

        val toMutableMap = portfolio.map.toMutableMap()
        toMutableMap.put(stockName,portfolio.map.getValue(stockName) + numberPurchased)

        return TransactionResult(numberPurchased, Portfolio(toMutableMap))

    }

    override fun sell(stockName: String, quantity: Int, portfolio: Portfolio): TransactionResult {

        val revenues = quantity * prizes.getValue(stockName)

        val toMutableMap = portfolio.map.toMutableMap()
        toMutableMap.put(stockName,portfolio.map.getValue(stockName) -  quantity)

        return TransactionResult(revenues, Portfolio(toMutableMap))

    }

    override fun get(stockName: String, portfolio: Portfolio): TransactionResult {
        return TransactionResult(portfolio.map.getValue(stockName),portfolio)
    }

}