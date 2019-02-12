package stockapi

class InMemoryStockApi(val prizes: Map<String, Int>) : StockApi
{

    override fun buy(stockName: String, amount: Int): Transaction  ={

        portfolio ->
        val numberPurchased = amount / prizes.getValue(stockName)

        val toMutableMap = portfolio.map.toMutableMap()
        toMutableMap.put(stockName,portfolio.map.getValue(stockName) + numberPurchased)

        TransactionResult(numberPurchased, Portfolio(toMutableMap))

    }

    override fun sell(stockName: String, quantity: Int): Transaction = {

        portfolio ->
        val revenues = quantity * prizes.getValue(stockName)

        val toMutableMap = portfolio.map.toMutableMap()
        toMutableMap.put(stockName,portfolio.map.getValue(stockName) -  quantity)

        TransactionResult(revenues, Portfolio(toMutableMap))

    }

    override fun get(stockName: String ): Transaction = {
        portfolio ->
        TransactionResult(portfolio.map.getValue(stockName),portfolio)
    }

}